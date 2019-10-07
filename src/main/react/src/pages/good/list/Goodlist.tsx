/**
 * @author by keray
 * date:2019/10/6 18:02
 */
import * as React from "react";
import {FormComponentProps} from "antd/lib/form";
import {connect} from "dva";
import Page, {TablePageRedux, TablePageState} from "../../../Page";
import {Button, Icon, Input, message, Pagination, Table, Tag} from "antd";
import "./index.css"

const {Column} = Table;

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

interface ISate extends TablePageState {
    queryData: {
        name?: string,
        goodCode?: string
    }
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
        tableLoading: false
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
            console.log('data:', r);
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
                <Button icon="plus" size='small'>添加</Button>
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

                                        }
                                    }>查看</Button>
                                    <Button onClick={
                                        () => {

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
        </div>;
    }
}

export default connect(({good}) => ({
    redux: good
}))(GoodList);
