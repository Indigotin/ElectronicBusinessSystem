import {APPReducers, GoodReducers, OrderReducers} from "./redux/reducers/reducers";
import {APPReduxData} from "./APP";
import {TablePageRedux} from "./Page";
import {GoodModel} from "./pages/good/list/Goodlist";
import {OrderModel} from "./pages/order/list/Orderlist";

interface Model<R> {
    namespace: string,
    state: R,
    reducers: any
}

export const app: Model<APPReduxData> = {
    namespace: 'app',
    state: {
        token: '',
        siderShow: true
    },
    reducers: APPReducers
};
export const Good:Model<TablePageRedux<GoodModel>> = {
    namespace: 'good',
    state: {
        list:[]
    },
    reducers: GoodReducers
};
export const Order:Model<TablePageRedux<OrderModel>> = {
    namespace: 'order',
    state: {
        list:[]
    },
    reducers: OrderReducers
};
