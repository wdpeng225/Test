package com.example.test.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.test.R;
import com.example.test.activity.BaseActivity;
import com.example.test.utils.AssetsUtils;

public class ImageWatchActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setLayoutRecourse(R.layout.activity_image_watch);
        setTitleStringRecoures(R.string.title_activity_image_watch);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initToolBar() {

    }


    @Override
    protected void initView() {
        super.initView();
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void bindData() {
        Bitmap bitmap = AssetsUtils.getImageFromAssetsFile(this, "img/pic_05.jpg");
        imageView.setImageBitmap(bitmap);
    }
}
