import {APPReduxData} from "../../APP";
import {TablePageRedux} from "../../Page";
import {GoodModel} from "../../pages/good/list/Goodlist";


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
