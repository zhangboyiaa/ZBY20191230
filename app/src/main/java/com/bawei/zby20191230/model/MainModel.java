package com.bawei.zby20191230.model;

import com.bawei.zby20191230.contract.IMainContract;
import com.bawei.zby20191230.model.bean.BaseBean;
import com.bawei.zby20191230.utile.NetUtile;
import com.google.gson.Gson;

/**
 * date:2019/12/30
 * author:张博一(zhangboyi)
 * function:
 */
public class MainModel implements IMainContract.IModel {
    @Override
    public void getMainData(String keyword, IModelCallBack iModelCallBack) {
        String url = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=5&keyword="+keyword;
        NetUtile.getInstance().getJsonGet(url, new NetUtile.MyCallBack() {
            @Override
            public void ongetJson(String json) {
                BaseBean baseBean = new Gson().fromJson(json, BaseBean.class);
                iModelCallBack.onMainSuccess(baseBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallBack.onMainFailure(throwable);
            }
        });
    }
}
