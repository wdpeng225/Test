package com.example.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.test.R;
import com.example.test.utils.ToolbarManager;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private int layoutRecourse;
    private int titleStringRecoures;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRecourse);
        initToolBar();
        initView();
        initData();
        bindData();
        initListener();
    }

    protected void setLayoutRecourse(int layoutRecourse) {
        this.layoutRecourse = layoutRecourse;
    }

    protected void setTitleStringRecoures (int titleStringRecoures) {
        this.titleStringRecoures = titleStringRecoures;
    }

    protected void initToolBar () {
        ToolbarManager.with(this)
                .title(getResources().getString(titleStringRecoures))
                .setNavigationIcon(R.mipmap.left, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    protected void initView () {

    }

    protected void initData (){

    }

    protected void bindData () {

    }

    protected void initListener () {

    }

    @Override
    public void onClick(View v) {

    }
}
