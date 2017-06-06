package com.iseeyou.bianzw.nodelistview.bean;

import java.io.Serializable;

/**
 * 作者：張利军 on 2017/6/6 0006 17:04
 * 邮箱：282384507@qq.com
 * 公司：南京艾思优信息科技有限公司
 */
public class NodeBean implements Serializable {
    public NodeBean(String address, String name) {
        this.address = address;
        this.name = name;
    }

    private String address;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
