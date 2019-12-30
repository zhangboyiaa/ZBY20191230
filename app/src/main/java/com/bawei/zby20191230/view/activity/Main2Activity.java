package com.bawei.zby20191230.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.zby20191230.R;
import com.bawei.zby20191230.base.BaseActivity;
import com.bawei.zby20191230.base.BasePresenter;
import com.bawei.zby20191230.model.bean.EvenBusBean;
import com.bawei.zby20191230.presenter.MainPresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {


    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.buua)
    Button buua;
    @BindView(R.id.buub)
    Button buub;
    @BindView(R.id.buuc)
    Button buuc;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.buud)
    Button buud;
    @BindView(R.id.buue)
    Button buue;

    @Override
    protected void initData() {
        CodeUtils.init(this);
    }

    @Override
    protected void initView() {

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imageView, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(Main2Activity.this, "啊啊啊错了", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected BasePresenter providerPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }

    @OnClick({R.id.buua, R.id.buub, R.id.buuc, R.id.buud, R.id.buue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buua:
                String s = editName.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    Bitmap image = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    imageView.setImageBitmap(image);
                }
                break;
            case R.id.buub:
                CodeUtils.analyzeByCamera(this);
                break;
            case R.id.buuc:
                CodeUtils.analyzeByPhotos(this);
                break;
            case R.id.buud:
                EventBus.getDefault().postSticky(new EvenBusBean("张博一"));
                break;
            case R.id.buue:
                EventBus.getDefault().post("我是字符串");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(Main2Activity.this, "错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getEvenBusBeanData(EvenBusBean evenBusBean){
        Toast.makeText(this, "接收成功"+evenBusBean.getName(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void getJsonBean(String json){
        Toast.makeText(this, "字符接收成功"+json, Toast.LENGTH_SHORT).show();
    }
}
