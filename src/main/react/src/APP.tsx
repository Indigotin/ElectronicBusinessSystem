import './index.css';
import * as React from 'react';
import {Route, Switch, Link} from 'dva/router';
import {connect} from "dva";
import Page, {IPageProps} from './Page';
import Login from "./pages/login/Login";
import Index from "./pages/Index";
import {Breadcrumb, Icon, Layout, Menu} from 'antd';
import GoodList from './pages/good/list/Goodlist'

const {Header, Content, Sider} = Layout;
const {SubMenu} = Menu;

export interface APPReduxData {
    token?: string,
    siderShow: boolean
}

const loginPath = '/login'

const routerMap = [
    {
        path: '/',
        com: Index
    },
    {
        path: '/good/list',
        com: GoodList
    }
];
const menuData = [
    {
        key: 'index',
        title: '首页',
        type: 'home',
        url: '/'
    },
    {
        key: 'good',
        title: '商品',
        type: 'schedule',
        children: [
            {
                key: 'good-list',
                title: '商品列表',
                type: 'database',
                url: '/good/list'
            },
            {
                key: 'good-add',
                title: '添加商品',
                type: 'plus-square',
                url: '/good/add'
            },
            {
                key: 'good-class-list',
                title: '商品分类',
                type: 'profile',
                url: '/good/class/list'
            },
            {
                key: 'good-type',
                title: '商品类型',
                type: 'hdd',
                url: '/good/type/list'
            },
            {
                key: 'good-brand',
                title: '品牌管理',
                type: 'tags',
                url: '/good/brand/list'
            }
        ]
    },
    {
        key: 'order',
        title: '订单',
        type: 'account-book',
        children: [
            {
                key: 'order-list',
                title: '订单列表',
                type: 'reconciliation',
                url: '/order/list'
            },
            {
                key: 'order-setting',
                title: '订单设置',
                type: 'setting',
                url: '/order/setting'
            },
            {
                key: 'order-refund',
                title: '退款申请处理',
                type: 'slack-circle',
                url: '/order/refund'
            },
            {
                key: 'order-refund-why',
                title: '退款原因设置',
                type: 'tool',
                url: '/order/refund-why'
            },
        ]
    },
    {
        key: 'market',
        title: '营销',
        type: 'reddit-square',
        children: [
            {
                key: 'market-kill-ac',
                title: '秒杀活动管理',
                type: 'clock-circle',
                url: '/market/kill-ac'
            },
            {
                key: 'market-coupon-list',
                title: '优惠券列表',
                type: 'medium-circle',
                url: '/market/coupon/list'
            }
        ]
    }
];

class App extends Page<any, APPReduxData, IPageProps<IPageProps>, {
    menuBreadcrumb: Array<{
        key: string,
        title: string
    }>,
    siderCollapse: boolean
}> {
    public constructor(props: IPageProps) {
        super(props, '', 'app');
    }

    public state = {
        menuBreadcrumb: [
            {
                key: 'index',
                title: '首页'
            }
        ],
        siderCollapse: true
    };

    public menuRender(data, that) {
        if (data.children) {
            return <SubMenu
                key={data.key}
                title={
                    <span>
                        <Icon theme="filled" type={data.type}/>
                        <span>{data.title}</span>
                    </span>
                }
            >
                {
                    data.children.map(node => that.menuRender(node, that))
                }
            </SubMenu>
        } else {
            return <Menu.Item key={data.key}><Link to={data.url}><Icon theme="filled"
                                                                       type={data.type}/><span>{data.title}</span>
            </Link></Menu.Item>
        }
    }

    public menuClick = ({item, key, keyPath}) => {
        let findTitle = (node, key): string => {
            if (node.key === key) {
                return node.title;
            }
            if (node.children) {
                for (let i = 0; i < node.children.length; i++) {
                    let title = findTitle(node.children[i], key);
                    if (title) {
                        return title;
                    }
                }

            }
            return null;
        };
        this.setState({
            menuBreadcrumb: (keyPath.reverse() as Array<string>).map((key: string) => {
                return {
                    key,
                    title: findTitle({
                        children: menuData
                    }, key)
                }
            })
        })
    };

    public render() {
        const state = this.state;
        return (
            <div className='app'>
                {
                    this.props.location.pathname === loginPath ?
                        <Switch>
                            <Route path='/login' exact component={Login}/>
                        </Switch>
                        :
                        <Layout>
                            <Header className="header" style={{padding: 0}}>
                                <div className="logo xy-center full-height"
                                     style={{width: state.siderCollapse ? '80px' : '12rem'}}>
                                    <img src='https://s01.mifile.cn/i/logo-footer.png?v2'
                                         style={{height: state.siderCollapse ? '40px' : '2.5rem'}}/>
                                </div>
                            </Header>
                            <Layout className='left-layout'>
                                {
                                    this.props.redux.siderShow ? (
                                        <Sider theme="dark"
                                               width='12rem'
                                               breakpoint='lg'
                                               onCollapse={() => {
                                                   this.setState({
                                                       siderCollapse: !state.siderCollapse
                                                   })
                                               }}
                                               collapsed={state.siderCollapse}
                                               collapsible>
                                            {
                                                <Menu
                                                    mode="inline"
                                                    theme="dark"
                                                    onClick={this.menuClick}
                                                    inlineCollapsed={state.siderCollapse}
                                                    defaultSelectedKeys={['index']}
                                                >
                                                    {
                                                        menuData.map(menu => this.menuRender(menu, this))
                                                    }
                                                </Menu>
                                            }
                                        </Sider>
                                    ) : ''
                                }
                                <Layout style={{padding: '0 24px 24px'}}>
                                    <Breadcrumb style={{margin: '16px 0'}}>
                                        {
                                            state.menuBreadcrumb.map(node => {
                                                return <Breadcrumb.Item>{node.title}</Breadcrumb.Item>
                                            })
                                        }
                                    </Breadcrumb>
                                    <Content
                                        style={{
                                            background: '#fff',
                                            padding: 24,
                                            margin: 0,
                                            minHeight: 280,
                                        }}
                                    >
                                        {
                                            routerMap.map(rou => {
                                                return (
                                                    <Route path={rou.path} exact
                                                           render={
                                                               (props: any) => {
                                                                   return <rou.com {...props} />
                                                               }
                                                           }
                                                    />
                                                )
                                            })
                                        }
                                    </Content>
                                </Layout>
                            </Layout>
                        </Layout>
                }
            </div>
        )
    }
}

export default connect(({app}) => ({
    redux: app
}))(App);
