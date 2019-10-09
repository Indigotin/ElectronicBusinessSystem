import {APPReduxData} from "../../APP";
import {TablePageRedux} from "../../Page";
import {GoodModel} from "../../pages/good/list/Goodlist";
import {OrderModel} from "../../pages/order/list/Orderlist";


export const APPReducers = {
    updateState(state: APPReduxData, { data }) {
        return {
            ...state, ...data
        }
    }
};
export const GoodReducers = {
    updateState(state: TablePageRedux<GoodModel>, { data }) {
        return {
            ...state, ...data
        }
    }
};
export const OrderReducers = {
    updateState(state: TablePageRedux<OrderModel>, { data }) {
        return {
            ...state, ...data
        }
    }
};
