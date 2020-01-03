package com.example.test.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.test.R;

/**
 * Created by Robert on 2016/11/24.
 */
public class ToolbarManager {

    private Toolbar toolBar;


    public static ToolbarManager with(AppCompatActivity appcompatActivity){
        Toolbar toolbar = (Toolbar) appcompatActivity.findViewById(R.id.tool_bar);
        setUpToolBar(appcompatActivity, toolbar);
        return new ToolbarManager(toolbar);
    }

    private static void setUpToolBar(AppCompatActivity appCompatActivity, Toolbar toolbar) {
        toolbar.setTitle("");
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private ToolbarManager(Toolbar toolBar) {
        if (null == toolBar){
            throw new IllegalArgumentException("You must set toolbar instance");
        }
        this.toolBar = toolBar;
    }

    public ToolbarManager title(String s) {
        return title(s, null);
    }

    public ToolbarManager title(String s, Integer color){

        TextView textView = (TextView) toolBar.findViewById(R.id.title_content_tv);
        if(textView != null){
            textView.setText(s);
            if(color != null){
                textView.setTextColor(color);
            }
        }else{
            toolBar.setTitle(s);
            if(color != null){
                toolBar.setTitleTextColor(color);
            }
        }
        return this;
    }

    public ToolbarManager setNavigationIcon(int res, View.OnClickListener onClickListener){
        toolBar.setNavigationIcon(res);
        toolBar.setNavigationOnClickListener(onClickListener);
        return this;
    }

    public ToolbarManager setBackgroundColor(int color){
        toolBar.setBackgroundColor(color);
        return this;
    }
}
