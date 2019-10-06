import './index.css';
import {Router, Switch, Route,} from 'dva/router';
import {Provider} from './redux/store'
import APP from './APP'
import moment from 'moment';
import 'moment/locale/zh-cn';
import {LocaleProvider} from "antd";
import zh_CN from 'antd/lib/locale-provider/zh_CN'

moment.locale('zh-cn');

function RouterConfig({history}) {
    return (
        <LocaleProvider locale={zh_CN}>
            <Provider>
                <Router history={history}>
                    <Switch>
                        <Route path="*" component={APP}/>
                    </Switch>
                </Router>
            </Provider>
        </LocaleProvider>

    )
}

export default RouterConfig;
