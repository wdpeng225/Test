package com.example.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.test.R;

/**
 * Created by Administrator on 2020/1/10.
 */

public class DiscScaleView extends View {

    private Paint paint;
    /**
     * 有值的刻度
     */
    private int progressColor;
    /**
     * 没有值的刻度
     */
    private int backgroundColor;
    /**
     * 圆盘最大角度
     */
    private float maxAngle;
    /**
     * 圆盘最大刻度
     */
    private int maxValue;
    /**
     * 圆的半径
     */
    private float radius;
    /**
     * 刻度长度
     */
    private float calibrationLenght;
    /**
     * 每个刻度旋转的角度
     */
    private float limitAngle;
    /**
     * 初始角度
     */
    private float startAngle;
    /**
     * 刻度值
     */
    private int progress;
    /**
     * 描述内容
     */
    private String desc;

    private int progressTextColor;

    private float progressTextSize;

    private float descSize;

    private int descColor;

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public DiscScaleView(Context context) {
        super(context);
        init(context, null);
    }

    public DiscScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DiscScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public DiscScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        initPaint();
    }

    private void initAttrs (Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.discScaleView);
        progressColor = typedArray.getColor(R.styleable.discScaleView_progressColor, getResources().getColor(R.color.blue_769DF8));
        backgroundColor = typedArray.getColor(R.styleable.discScaleView_backgroundColor, getResources().getColor(R.color.gray_F0F0F0));
        maxAngle = typedArray.getFloat(R.styleable.discScaleView_maxAngle, 360.0f);
        maxValue = typedArray.getInteger(R.styleable.discScaleView_maxValue, 100);
        radius = typedArray.getDimension(R.styleable.discScaleView_radius, 100.0f);
        calibrationLenght = typedArray.getDimension(R.styleable.discScaleView_calibrationLenght, 10.0f);
        desc = typedArray.getString(R.styleable.discScaleView_desc);
        descSize = typedArray.getDimension(R.styleable.discScaleView_descSize, getResources().getDimension(R.dimen.DIMEN_30PX));
        descColor = typedArray.getColor(R.styleable.discScaleView_descColor, getResources().getColor(R.color.colorPrimaryDark));
        progressTextSize = typedArray.getDimension(R.styleable.discScaleView_progressTextSize, descSize);
        progressTextColor = typedArray.getColor(R.styleable.discScaleView_progressTextColor, progressColor);
        limitAngle = maxAngle / maxValue;
        startAngle = (maxAngle - 360 );
    }

    private void initPaint () {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension((int)(radius * 2), (int)(radius * 2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.reset();
        paint.setColor(progressColor);
        paint.setStrokeWidth((float) 2.0);
        paint.setAntiAlias(true);
        canvas.translate(radius, radius);
        canvas.rotate(startAngle);

        for (int i = 0; i <= progress; i++) {
            canvas.drawLine(0 - radius, 0, 0 - radius + calibrationLenght, 0, paint);
            canvas.rotate(limitAngle);
        }

        paint.reset();
        paint.setColor(backgroundColor);
        paint.setStrokeWidth((float) 2.0);
        paint.setAntiAlias(true);

        for (int i = progress + 1; i <= maxValue; i++) {
            canvas.drawLine(0 - radius, 0, 0 - radius + calibrationLenght, 0, paint);
            if (i < maxValue) {
                canvas.rotate(limitAngle);
            }
        }

        paint.reset();
        paint.setColor(descColor);
        paint.setAntiAlias(true);
        paint.setTextSize(descSize);
        canvas.rotate(-1 * limitAngle * maxValue + 360 - startAngle);
        canvas.translate((0 - descSize * desc.length()) / 2, 0 - descSize);
        canvas.drawText(desc, 0, 0, paint);

        paint.reset();
        paint.setColor(progressTextColor);
        paint.setTextSize(progressTextSize);
        canvas.translate( descSize * desc.length()/2 , descSize );
        canvas.drawText(String.valueOf(progress), 0 - progressTextSize / 2 , descSize, paint);

    }



}
