/**
 * @author by keray
 * date:2019/10/6 18:02
 */
import Page from "../../Page";
import * as React from "react";
import {Form, Icon, Input, Button, Checkbox, message} from 'antd';
import {FormComponentProps} from "antd/lib/form";
import {connect} from "dva";
import "./login.css"

class Login extends Page<any, any, FormComponentProps<any>> {
    public constructor(props: any) {
        super(props, '', 'app');
    }

    private handleSubmit = e => {
        this.props.form.validateFields(err => {
            if (!err) {
                this.login();
            }
        });

    };

    private login() {
        let md5 = require('md5');
        this.postJson("/check", {
            ...this.props.form.getFieldsValue(),
            password: md5(this.props.form.getFieldValue("password")).toUpperCase()
        }).then(result => {
            this.setSta({
                user: result.member
            });
            this.directional();
        }).catch(error => {
            message.error('登录失败');
        });
    }

    private directional() {
        console.log("login", this.props.history)
        if (this.props.history.action == 'POP') {
            this.push("/")
        } else {
            this.go(-1)
        }
    }

    public render() {
        const {getFieldDecorator} = this.props.form;
        return <div className="login-box">
            <Form onSubmit={this.handleSubmit} className="login-form">
                <Form.Item>
                    {getFieldDecorator('username', {
                        rules: [{required: true, message: 'Please input your username!'}],
                    })(
                        <Input
                            prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>}
                            placeholder="Username"
                        />,
                    )}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('password', {
                        rules: [{required: true, message: 'Please input your Password!'}],
                    })(
                        <Input
                            prefix={<Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>}
                            type="password"
                            placeholder="Password"
                        />,
                    )}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('remember', {
                        valuePropName: 'checked',
                        initialValue: true,
                    })(<Checkbox>记住</Checkbox>)}
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        登录
                    </Button>
                </Form.Item>
            </Form>
        </div>;
    }
}

export default Form.create<FormComponentProps<any>>()(
    connect(({app}) => ({
        redux: app
    }))(Login)
);
