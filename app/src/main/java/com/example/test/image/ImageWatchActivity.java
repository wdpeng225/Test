package com.example.test.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.test.R;
import com.example.test.activity.BaseActivity;
import com.example.test.utils.AssetsUtils;

public class ImageWatchActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_image_watch);
        imageView = findViewById(R.id.imageView);
        Bitmap bitmap = AssetsUtils.getImageFromAssetsFile(this, "img/pic_05.jpg");
        imageView.setImageBitmap(bitmap);
    }
}
