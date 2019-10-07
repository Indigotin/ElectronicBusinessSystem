import * as React from 'react';
import IApi, {FetchRequest, Pageable} from "./IApi";
import BaseApi from "./BaseApi";
import {SubscriptionAPI} from "dva";

export default class IComp<M = {}, R = {}, P = any, S = {}, SS = any> extends React.Component<P & SubscriptionAPI, S, SS> implements IApi<M> {
    protected api: BaseApi<M>;
    protected watch: any = {};
    protected namespace: string;

    public constructor(props: any, baseUrl?: string, namespace?: string) {
        super(props);
        this.api = new BaseApi(baseUrl)
        this.namespace = namespace;
    }

    dispatch(action: {
        type: string,
        data: any,
        owner?: boolean
    }) {
        if (action.owner === undefined) {
            action.owner = true;
        }
        this.props.dispatch({
            type: action.owner ? `${this.namespace}/${action.type}` : action.type,
            data: action.data
        })
    }

    setSta(d: R) {
        this.dispatch({
            type: 'updateState',
            data: d
        })
    }

    request(input: FetchRequest): Promise<any> {
        return this.api.request(input);
    }

    get(url: string, param?: {}): Promise<any> {
        return this.api.get(url, param);
    }

    post(url: string, param?: {}, method?: string): Promise<any> {
        return this.api.post(url, param, method);
    }

    postJson(url: string, data?: any): Promise<any> {
        return this.api.postJson(url, data);
    }

    put(url: string, param?: any): Promise<boolean> {
        return this.api.put(url, param);
    }

    delete(url: string, param?: any): Promise<boolean> {
        return this.api.delete(url, param);
    }

    save(model: M): Promise<M> {
        return this.api.save(model);
    }

    update(model: { id: number; }): Promise<boolean> {
        return this.api.update(model);
    }

    deleteOne(id: number): Promise<boolean> {
        return this.api.deleteOne(id);
    }

    deleteAny(ids: number[]): Promise<boolean> {
        return this.api.deleteAny(ids);
    }

    basePage(page: Pageable, param?: Object): Promise<import("./IApi").PageData<M>> {
        throw new Error("Method not implemented.");
    }

    getOneById(id: number): Promise<M> {
        throw new Error("Method not implemented.");
    }

    shouldComponentUpdate(nextProps, nextState) {
        try {
            let oldProps = {...this.props};
            let newProps = {...nextProps};
            setTimeout(() => {
                for (let key in this.watch || {}) {
                    let keys = key.split(",");
                    for (let k of keys) {
                        let oldValue = IComp.getValue(oldProps, k);
                        let newValue = IComp.getValue(newProps, k);
                        if (!IComp.equals(oldValue, newValue)) {
                            let values = [];
                            for (let k1 of keys) {
                                values.push(IComp.getValue(newProps, k1));
                            }
                            this.watch[key](...values);
                            break;
                        }
                    }
                }
            }, 0);
        } catch (e) {
            console.log("watch props error", e);
        }
        return true;
    }

    setState<K extends keyof S>(
        state: ((prevState: Readonly<S>, props: Readonly<P>) => (Pick<S, K> | S | null)) | (Pick<S, K> | S | null) | any,
        callback?: () => void
    ): void {
        let oldState = {...this.state};
        let newState = state;
        let call = () => {
            try {
                for (let key in this.watch || {}) {
                    let keys = key.split(",");
                    for (let k of keys) {
                        let oldValue = IComp.getValue(oldState, k);
                        let newValue = IComp.getValue(newState, k);
                        if (newValue === undefined) {
                            continue;
                        }
                        if (!IComp.equals(oldValue, newValue)) {
                            let values = [];
                            for (let k1 of keys) {
                                values.push(IComp.getValue(newState, k1));
                            }
                            this.watch[key](...values);
                            break;
                        }
                    }
                }
            } catch (e) {
                console.log();
            }
            if (typeof callback === typeof function () {
            }) {
                callback();
            }
        }
        super.setState(state, call);
    }

    private static equals(o1: any, o2: any): boolean {
        if (typeof o1 !== typeof o2) {
            return false;
        }
        if (typeof o1 === typeof {}) {
            return JSON.stringify(o1) === JSON.stringify(o2);
        }
        return o1 === o2;
    }

    private static getValue(o: object, keyStr: string): any {
        let keys = keyStr.split(".");
        for (let key of keys) {
            if (!o) {
                break;
            }
            o = o[key];
        }
        return o;
    }
}
