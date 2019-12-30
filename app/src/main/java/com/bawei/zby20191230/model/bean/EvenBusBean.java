package com.bawei.zby20191230.model.bean;

/**
 * date:2019/12/30
 * author:张博一(zhangboyi)
 * function:
 */
public class EvenBusBean {

    private String name;

    @Override
    public String toString() {
        return "EvenBusBean{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EvenBusBean(String name) {
        this.name = name;
    }
}
