package com.bawei.zby20191230.presenter;

import com.bawei.zby20191230.base.BasePresenter;
import com.bawei.zby20191230.contract.IMainContract;
import com.bawei.zby20191230.model.MainModel;
import com.bawei.zby20191230.model.bean.BaseBean;

/**
 * date:2019/12/30
 * author:张博一(zhangboyi)
 * function:
 */
public class MainPresenter extends BasePresenter<IMainContract.IView> implements IMainContract.IPresenter {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }

    @Override
    public void getMainData(String keyword) {
        mainModel.getMainData(keyword, new IMainContract.IModel.IModelCallBack() {
            @Override
            public void onMainSuccess(BaseBean baseBean) {
                view.onMainSuccess(baseBean);
            }

            @Override
            public void onMainFailure(Throwable throwable) {
                view.onMainFailure(throwable);
            }
        });
    }
}
