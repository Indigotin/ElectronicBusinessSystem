import {APPReducers} from "./redux/reducers/reducers";
import {APPReduxData} from "./APP";

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
