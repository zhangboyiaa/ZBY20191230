package com.bawei.zby20191230.contract;

import com.bawei.zby20191230.model.bean.BaseBean;

/**
 * date:2019/12/30
 * author:张博一(zhangboyi)
 * function:
 */
public interface IMainContract {

    interface IView{
        void onMainSuccess(BaseBean baseBean);

        void onMainFailure(Throwable throwable);
    }

    interface IPresenter{
        void getMainData(String keyword);
    }

    interface IModel{

        void getMainData(String keyword,IModelCallBack iModelCallBack);

        interface IModelCallBack{
            void onMainSuccess(BaseBean baseBean);

            void onMainFailure(Throwable throwable);
        }
    }
}
