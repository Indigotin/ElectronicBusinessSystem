import * as React from 'react';
import 'antd/dist/antd.css';
import './index.css'
import * as H from 'history';
import {match} from 'react-router';
import {Pageable} from './IApi'
import IComp from './IComp';
import {RouterAPI} from "dva";

export interface IPageProps<P = {}, R = {}> {
    location?: H.Location<P>,
    match?: match<P>,
    redux: R
}

export interface TablePageState {
    pageNumber: number,
    pageSize: number,
    total?: number,
    tableLoading: boolean
}


export interface TablePageRedux<M> {
    list?: Array<M>
}


class Page<M = {}, R = any, P = {}, S = {}, SS = any> extends IComp<M, R, P & IPageProps<P,R>, S, SS> {
    public constructor(props: any, baseUrl?: string, namespace?: string) {
        super(props, baseUrl, namespace);
    }

    basePage(page: Pageable, param?: Object): Promise<import("./IApi").PageData<M>> {
        return this.api.basePage(page, param);
    }

    getOneById(id: number): Promise<M> {
        return this.api.getOneById(id);
    }

    push(pathname: string, state?: {}) {
        this.props.history.push({
            pathname,
            state
        });
    }

    go(n: number) {
        this.props.history.go(n);
    }

    goBack() {
        this.go(-1);
    }
}

export default Page;
