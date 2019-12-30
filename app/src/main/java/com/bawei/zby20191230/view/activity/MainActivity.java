package com.bawei.zby20191230.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bawei.zby20191230.R;
import com.bawei.zby20191230.base.BaseActivity;
import com.bawei.zby20191230.contract.IMainContract;
import com.bawei.zby20191230.model.bean.BaseBean;
import com.bawei.zby20191230.presenter.MainPresenter;
import com.bawei.zby20191230.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.IView {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initData() {
        presenter.getMainData("板鞋");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainPresenter providerPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onMainSuccess(BaseBean baseBean) {
        List<BaseBean.ResultBean> result = baseBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(result);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        recycler.setAdapter(myAdapter);
    }

    @Override
    public void onMainFailure(Throwable throwable) {
        Toast.makeText(this, "薄脆"+throwable, Toast.LENGTH_SHORT).show();
    }
}
