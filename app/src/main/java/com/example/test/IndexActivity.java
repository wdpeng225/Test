package com.example.test;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.test.activity.BaseActivity;

public class IndexActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_index);
        setTitleString(getResources().getString(R.string.title_activity_main));
        onCreateView();
    }

}
