/**
 * @author by keray
 * date:2019/10/6 18:02
 */
import * as React from "react";
import {FormComponentProps} from "antd/lib/form";
import {connect, SubscriptionAPI} from "dva";
import Page, {TablePageRedux, TablePageState} from "../../../Page";
import {Button, Form, Icon, Input, message, Modal, Pagination, Select, Table, Tabs, Upload} from "antd";
import "./index.css"
import IComp, {TableFormProps} from "../../../IComp";
import {UploadFile} from "antd/lib/upload/interface";

const {Column} = Table;
const {Option} = Select;

export interface GoodModel {
    id?: number,
    name?: string,
    sellPoint?: string,
    price?: number,
    num?: number,
    limitNum?: number,
    image?: string,
    status?: number
}

class GoodForm extends IComp<GoodModel, any, TableFormProps<GoodModel>, {
    imageModel: UploadFile,
    saveLoading: boolean
}> {
    constructor(props: TableFormProps<GoodModel>) {
        super(props, '/good', "good");
    }

    state = {
        saveLoading: false,
        imageModel: null
    };

    componentDidMount() {
        this.loadFormData();
    }


    watch = {
        'model': (model) => {
            this.loadFormData();
        }
    }

    handleSubmit = (e: any) => {
        let success = () => {
            this.setState({
                saveLoading: true
            });
            let result;
            if (this.props.formType === "add") {
                result = this.save(
                    {
                        ...this.props.form.getFieldsValue()
                    }
                );
            }
            if (this.props.formType === "edit") {
                result = this.update(
                    {
                        ...this.props.form.getFieldsValue(),
                        id: this.props.model.id
                    }
                );
            }
            result.then(r => {
                this.props.formSu && this.props.formSu(this.props.form.getFieldsValue(), r);
            }).catch(e => {
                this.props.formFai && this.props.formFai(this.props.form.getFieldsValue(), e);
            }).finally(() => {
                this.setState({
                    saveLoading: false
                })
            })
        };
        this.props.form.validateFields((errors, values) => {
            if (!errors) {
                success();
            }
        });
    };
    public normFile = e => {
        let file = e.file;
        if (file.response && file.response.status && file.response.status === 'done') {
            this.setState({
                imageModel: {
                    uid: file.uid,
                    name: file.response.name,
                    status: file.response.status,
                    url: file.response.url
                }
            });
        } else {
            this.setState({
                imageModel: file
            });
        }
    };

    private loadFormData() {
        if (!this.props.model) {
            this.props.form.resetFields();
            this.setState({
                imageModel: null
            });
            return;
        }
        this.props.form.setFieldsValue({
            ...this.props.model,
            imageList: [
                {
                    uid: this.props.model.id,
                    name: this.props.model.image,
                    status: 'done',
                    url: this.props.model.image
                }
            ]
        });
        if (this.props.model.image) {
            this.setState({
                imageModel: {
                    uid: this.props.model.id,
                    name: this.props.model.name,
                    status: 'done',
                    url: this.props.model.image
                }
            })
        }
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const state = this.state;
        const props = this.props;
        const uploadButton = (
            <div>
                <Icon type="plus"/>
                <div className="ant-upload-text">Upload</div>
            </div>
        );
        return (
            <Form labelCol={{span: 5}} wrapperCol={{span: 12}} onSubmit={this.handleSubmit}>
                <Form.Item label="商品名">
                    {getFieldDecorator('name', {
                        rules: [
                            {required: true, message: '请输入商品名',}
                        ],
                    })(<Input disabled={props.formType === 'see'}/>)}
                </Form.Item>
                <Form.Item label="价格">
                    {getFieldDecorator('price', {
                        rules: [
                            {required: true, message: '请输入商品价格',}
                        ],
                    })(<Input type="number" disabled={props.formType === 'see'}/>)}
                </Form.Item>
                <Form.Item label="状态">
                    {getFieldDecorator('status', {
                        rules: [
                            {required: true, message: '选择状态',}
                        ],
                    })(
                        <Select
                            placeholder="选择状态"
                            optionFilterProp="children"
                            disabled={props.formType === 'see'}
                        >
                            <Option value={1}>上架</Option>
                            <Option value={2}>下架</Option>
                            <Option value={3}>过期</Option>
                        </Select>
                    )}
                </Form.Item>
                <Form.Item label="商品图片">
                    {getFieldDecorator('image', {
                        rules: [
                            {required: true, message: '上传商品图片',}
                        ],
                    })(
                        <Upload
                            disabled={props.formType === 'see'}
                            action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                            listType="picture-card"
                            onChange={this.normFile}
                            fileList={state.imageModel ? [state.imageModel] : []}
                        >
                            {props.formType === 'see' ? null : uploadButton}
                        </Upload>
                    )}
                </Form.Item>
                <Form.Item wrapperCol={{span: 12, offset: 5}}>
                    <div className='full-width xy-center react'>
                        <Button type="primary" htmlType="submit" loading={this.state.saveLoading}
                                disabled={props.formType === 'see'}>
                            确定
                        </Button>
                        <Button type="default" loading={this.state.saveLoading}
                                onClick={() => {
                                    this.props.cancel(this.props.model)
                                }}>
                            取消
                        </Button>
                    </div>
                </Form.Item>
            </Form>
        );
    }
}

const GoodFormCom = Form.create<TableFormProps<GoodModel>>()(
    connect(({server}) => ({redux: server}))(GoodForm)
);

interface ISate extends TablePageState {
    queryData: {
        name?: string,
        goodCode?: string
    },
    editModelVisible: boolean,
    formType?: 'edit' | 'add' | 'see',
    selectModel?: GoodModel
}

class GoodList extends Page<GoodModel, TablePageRedux<GoodModel>, FormComponentProps<any>, ISate> {
    public constructor(props: any) {
        super(props, '', 'good');
    }

    public state: ISate = {
        queryData: {},
        pageSize: 10,
        pageNumber: 1,
        total: 100,
        tableLoading: false,
        editModelVisible: false
    };

    componentWillMount(): void {
        this.loadTable();
    }

    public loadTable = (): void => {
        // load data
        this.setState({
            tableLoading: true
        });
        this.postJson(`/es/recommend/${this.state.pageNumber}/${this.state.pageSize}`, [1, 2, 3]).then(r => {
            this.setSta({
                list: r[0]
            })
        }).catch(e => {
            message.error("商品列表加载失败");
        }).finally(() => {
            this.setState({
                tableLoading: false
            });
        })
    };

    public editHandleCancel() {
        this.setState({
            editModelVisible: false
        })
    }

    public rowSelection = {
        onChange: (selectedRowKeys, selectedRows) => {
            console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        },
        getCheckboxProps: record => ({
            disabled: record.name === 'Disabled User',
            name: record.name,
        }),
    };

    public render() {
        const state = this.state;
        const queryData = state.queryData;
        const redux = this.props.redux;
        return <div className="list-box">
            <div className='row-box i-yx-between al-str'>
                <div><Icon type="search"/>筛选搜索</div>
                <div className='i-xy-center jf-start search-box'>
                    <div className='search-item'>
                        <div className='search-item-label'>输入搜索：</div>
                        <Input placeholder="商品名称" value={queryData.name} allowClear onChange={(e) => {
                            this.setState({
                                queryData: {
                                    ...state.queryData,
                                    name: e.target.value
                                }
                            })
                        }}/>
                    </div>
                    <div className='search-item'>
                        <div className='search-item-label'>商品货号：</div>
                        <Input placeholder="商品货号" value={queryData.goodCode} allowClear onChange={(e) => {
                            this.setState({
                                queryData: {
                                    ...state.queryData,
                                    goodCode: e.target.value
                                }
                            })
                        }}/>
                    </div>
                </div>
                <div className='i-xy-between jf-end'>
                    <Button onClick={
                        () => {
                            this.setState({
                                queryData: {}
                            })
                        }
                    }>重置</Button>
                    <Button icon="search" type="primary" style={{marginLeft: '1rem'}}>搜索</Button>
                </div>
            </div>
            <div className='row-box i-xy-between'>
                <div>
                    <Icon type="table"/> 数据列表
                </div>
                <Button icon="plus" size='small' onClick={
                    () => {
                        this.setState({
                            editModelVisible: true,
                            selectModel: null,
                            formType: "add"
                        })
                    }
                }>添加</Button>
            </div>
            <div className='row-box table-row-box'>
                <Table dataSource={redux.list} bordered
                       rowSelection={this.rowSelection}
                       loading={this.state.tableLoading}
                       pagination={false}
                >
                    <Column title="编号" dataIndex="id" key="id"/>
                    <Column title="商品图片" dataIndex="image" key="image"
                            align='center'
                            render={
                                (text, record: GoodModel) => (
                                    <img src={text} className='good-table-img'/>
                                )}/>
                    <Column title="商品名称" dataIndex="name" key="name"/>
                    <Column title="价格" dataIndex="price" key="price"
                            render={
                                (text, record: GoodModel) => (
                                    <div>¥{text}</div>
                                )
                            }
                    />
                    <Column title="销量" dataIndex="sellPoint" key="sellPoint"/>
                    <Column title="状态" dataIndex="status" key="status"/>
                    <Column title="操作"
                            width='10rem'
                            render={(text, record: GoodModel) => (
                                <div className='table-operation'>
                                    <Button onClick={
                                        () => {
                                            this.setState({
                                                editModelVisible: true,
                                                formType: "see",
                                                selectModel: record
                                            })
                                        }
                                    }>查看</Button>
                                    <Button onClick={
                                        () => {
                                            this.setState({
                                                editModelVisible: true,
                                                formType: "edit",
                                                selectModel: record
                                            })
                                        }
                                    }>编辑</Button>
                                    <Button onClick={
                                        () => {

                                        }
                                    }>日志</Button>
                                    <Button type="danger" onClick={
                                        () => {

                                        }
                                    }>删除</Button>
                                </div>
                            )}/>
                </Table>

                <div className='table-page-box'>
                    <Pagination
                        showSizeChanger
                        showQuickJumper
                        hideOnSinglePage
                        pageSize={this.state.pageSize}
                        current={this.state.pageNumber}
                        total={this.state.total}
                        onChange={(pageNumber: number, pageSize: number) => {
                            this.setState({
                                pageSize,
                                pageNumber
                            }, this.loadTable)
                        }}
                    />
                </div>
            </div>
            <Modal
                title={
                    {
                        add: '添加商品',
                        edit: '编辑商品',
                        see: '查看商品'
                    }[state.formType]
                }
                centered
                visible={this.state.editModelVisible}
                footer={null}
                onCancel={this.editHandleCancel.bind(this)}
            >
                <GoodFormCom model={state.selectModel} formType={state.formType}
                             cancel={
                                 () => {
                                     this.setState({
                                         editModelVisible: false
                                     })
                                 }
                             }
                             formSu={
                                 (good) => {
                                     console.log("good:", good)
                                 }
                             }
                             formFai={
                                 (error) => {
                                     console.log("error:", error)
                                 }
                             }
                />
            </Modal>
        </div>;
    }
}

export default connect(({good}) => ({
    redux: good
}))(GoodList);
