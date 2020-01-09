package com.example.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.test.R;
import com.example.test.utils.ToolbarManager;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private String titleStr;

    protected void onCreateView () {
        initToolBar();
        initView();
        initData();
        bindData();
        initListener();
    }

    protected void setTitleString(String titleStr) {
        this.titleStr = titleStr;
    }

    protected void initToolBar () {
        ToolbarManager.with(this)
                .title(titleStr)
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
