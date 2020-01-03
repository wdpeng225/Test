package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.test.activity.BaseActivity;
import com.example.test.image.ImageWatchActivity;

public class MainActivity extends BaseActivity {

    private RelativeLayout layoutLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutRecourse(R.layout.activity_main);
        setTitleStringRecoures(R.string.title_activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        layoutLoadImage = findViewById(R.id.layout_load_image);
    }

    @Override
    protected void initListener() {
        super.initListener();
        layoutLoadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.layout_load_image:
                gotoImageWatchActivity();
                break;
        }
    }

    private void gotoImageWatchActivity () {
        Intent intent = new Intent(this, ImageWatchActivity.class);
        startActivity(intent);
    }


}
