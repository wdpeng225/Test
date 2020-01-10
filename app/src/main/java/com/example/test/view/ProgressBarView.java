package com.example.test.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.test.R;

/**
 * Created by Administrator on 2020/1/9.
 */

public class ProgressBarView extends View {

    private Resources resources;
    private int backgroundColor;
    private int startColor;
    private int endColor;
    private int screenWidth;
    private float viewHeight;
    private int viewWidthPercentage;

    private int value;

    private Paint paint;


    public ProgressBarView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        resources = context.getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progressBar);
        backgroundColor = typedArray.getColor(R.styleable.progressBar_background_color, resources.getColor(R.color.gray_F0F0F0));
        startColor = typedArray.getColor(R.styleable.progressBar_start_color, resources.getColor(R.color.blue_769DF8));
        endColor = typedArray.getColor(R.styleable.progressBar_end_color, resources.getColor(R.color.blue_76C4FF));
        viewHeight = typedArray.getDimension(R.styleable.progressBar_view_height, resources.getDimension(R.dimen.DIMEN_4PX));
        viewWidthPercentage = typedArray.getInteger(R.styleable.progressBar_view_width_percentage,50);

        DisplayMetrics metric = resources.getDisplayMetrics();
        screenWidth = metric.widthPixels; // 屏幕宽度（像素）

        paint = new Paint();
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(screenWidth * viewWidthPercentage/100, (int)viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 设置背景色
        paint.setAntiAlias(true);// 抗锯齿
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(0, 0, screenWidth * viewWidthPercentage/100, viewHeight,(float) 50, (float) 50, paint);

        // 设置前部显示的颜色
        paint.setAntiAlias(true);// 抗锯齿
        LinearGradient linearGradient = new LinearGradient(0,0,screenWidth * viewWidthPercentage/100,0,resources.getColor(R.color.blue_769DF8), resources.getColor(R.color.blue_76C4FF), Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawRoundRect(0, 0, screenWidth * viewWidthPercentage * value/100/100, viewHeight,(float) 50, (float) 50, paint);
    }


}
