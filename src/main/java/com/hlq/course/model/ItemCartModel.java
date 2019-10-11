package com.hlq.course.model;

import com.hlq.course.pojo.ItemCart;

/**
 * Created by Len on 2019-10-12
 */
public class ItemCartModel extends ItemCart {
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
