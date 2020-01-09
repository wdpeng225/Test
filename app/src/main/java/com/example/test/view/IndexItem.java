package com.example.test.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.R;

/**
 * Created by Administrator on 2020/1/3.
 */

public class IndexItem extends RelativeLayout {

    private RelativeLayout indexItemRoot;
    private TextView itemContent;
    private String activityName;
    private String contentStr;

    public IndexItem(Context context) {
        super(context);
        initView();
    }

    public IndexItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs, defStyleAttr);
        initView();
    }

    private void initData (Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.index_item, defStyleAttr, 0);
        activityName = ta.getString(R.styleable.index_item_activityName);
        contentStr = ta.getString(R.styleable.index_item_contentStr);
        ta.recycle();
    }

    private void initView () {
        LayoutInflater.from(getContext()).inflate(R.layout.view_item_index, this, true);
        indexItemRoot = (RelativeLayout)findViewById(R.id.index_item_root);
        itemContent = (TextView)findViewById(R.id.index_item_content);
        itemContent.setText(contentStr);
        indexItemRoot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity();
            }
        });
    }

    private void gotoActivity() {
        try {
            Class clz = Class.forName(activityName);
            getContext().startActivity(new Intent(getContext(), clz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
