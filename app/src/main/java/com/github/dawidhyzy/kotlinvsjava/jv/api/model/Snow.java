
package com.github.dawidhyzy.kotlinvsjava.jv.api.model;

import com.google.gson.annotations.SerializedName;

public class Snow {

    @SerializedName("3h")
    private int _3h;

    public int get3h() {
        return _3h;
    }

    public void set3h(int _3h) {
        this._3h = _3h;
    }

}