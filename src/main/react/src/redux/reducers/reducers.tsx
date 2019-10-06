import {APPReduxData} from "../../APP";


export const APPReducers = {
    updateState(state: APPReduxData, { data }) {
        return {
            ...state, ...data
        }
    }
};
