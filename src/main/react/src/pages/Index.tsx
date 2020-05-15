/**
 * @author by Len
 * date:2019/10/6 18:02
 */
import Page from "../Page";
import * as React from "react";
import {connect} from "dva";

class Index extends Page<any, any, any> {
    public constructor(props: any) {
        super(props, '', 'app');
    }

    public render() {
        return <div className="index-box">
            123
        </div>;
    }
}

export default connect(({index}) => ({
    redux: index
}))(Index);
