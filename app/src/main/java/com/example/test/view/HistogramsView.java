package com.example.test.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.histogram.HistogramData;
import com.example.test.histogram.ItemSelelctListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 各路段事故数量统计
 *
 * @author
 */
public class HistogramsView extends View {

    private Resources resources;

    private List<HistogramData> dataList = new ArrayList<>();

    private Paint paintCommon = new Paint();

    private Path path = new Path();

    private int screenWidth = 0;

    private int selectPostion = 0;

    private ItemSelelctListener itemSelelctListener;

    public void setItemSelelctListener(ItemSelelctListener itemSelelctListener) {
        this.itemSelelctListener = itemSelelctListener;
    }

    private void init() {
        resources = this.getContext().getResources();
        DisplayMetrics metric = getResources().getDisplayMetrics();
        screenWidth = metric.widthPixels; // 屏幕宽度（像素）
    }

    public HistogramsView(Context context) {
        super(context);
        init();
    }

    public HistogramsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistogramsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = (int) resources.getDimension(R.dimen.DIMEN_400PX);
        setMeasuredDimension(measureWidth(), height);
    }

    private int measureWidth(){
        int width = (int) (dataList.size() * resources.getDimension(R.dimen.DIMEN_100PX));
        return width;
    }

    public void setData(List<HistogramData> dataList) {
        this.dataList = dataList;
        selectPostion = dataList.size() - 1;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resources = null;
        dataList.clear();
        dataList = null;
        paintCommon = null;
        path = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        float item_width = resources.getDimension(R.dimen.DIMEN_30PX);
        float column_width = resources.getDimension(R.dimen.DIMEN_100PX);
        float column_height = resources.getDimension(R.dimen.DIMEN_400PX);

        if (null != dataList && dataList.size() > 0) {

            for (int i = 0; i < dataList.size(); i++) {
                String key = dataList.get(i).getKey();
                int value = dataList.get(i).getValue();

                // 上部文字标题
                paintCommon.reset();
                paintCommon.setAntiAlias(true);// 抗锯齿
                if (i == selectPostion) {
                    paintCommon.setColor(resources.getColor(R.color.blue_769DF8));
                } else {
                    paintCommon.setColor(resources.getColor(R.color.black_4A4A4A));
                }
                paintCommon.setTextSize(resources.getDimension(R.dimen.DIMEN_20PX));
                paintCommon.setTextAlign(Align.CENTER);
                canvas.drawText(key, column_width * i + column_width / 2, resources.getDimension(R.dimen.DIMEN_20PX), paintCommon);

                float item_height = (resources.getDimension(R.dimen.DIMEN_400PX) -  resources.getDimension(R.dimen.DIMEN_50PX))* value / 60  ;
                float position_item_top = (column_height - item_height);
                float position_item_bottom = resources.getDimension(R.dimen.DIMEN_400PX);

                // 柱状图
                paintCommon.reset();
                paintCommon.setAntiAlias(true);// 抗锯齿
                paintCommon.setTextSize(resources.getDimension(R.dimen.DIMEN_14PX));
                LinearGradient linearGradient = new LinearGradient(0,position_item_top,0,position_item_bottom,resources.getColor(R.color.blue_769DF8), resources.getColor(R.color.blue_76C4FF), Shader.TileMode.CLAMP);
                paintCommon.setShader(linearGradient);
                canvas.drawRoundRect(column_width * i + (column_width - item_width)/2, position_item_top, column_width * i + (column_width - item_width)/2 + item_width, position_item_bottom,(float) 50, (float) 50, paintCommon);

                // 柱状图上的文字
                paintCommon.reset();
                paintCommon.setAntiAlias(true);// 抗锯齿
                paintCommon.setTextSize(resources.getDimension(R.dimen.DIMEN_14PX));
                paintCommon.setTextAlign(Align.CENTER);
                paintCommon.setColor(resources.getColor(R.color.black_4A4A4A));
                canvas.drawText(String.valueOf(value), //
                        column_width * i + column_width/2, //
                        position_item_top - resources.getDimension(R.dimen.DIMEN_10PX), //
                        paintCommon);//

                // 绘制竖向分割线
                paintCommon.reset();
                paintCommon.setAntiAlias(true);
                paintCommon.setColor(resources.getColor(R.color.gray_F0F0F0));
                paintCommon.setStrokeWidth((float) 1.0);
                canvas.drawLine(column_width * (i + 1), (float)10.0, column_width * (i + 1), getResources().getDisplayMetrics().heightPixels/3, paintCommon);
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float column_width = resources.getDimension(R.dimen.DIMEN_100PX);
        int position = (int) (event.getX()/column_width);
        itemSelelctListener.itemSelect(position);
        selectPostion = position;
        invalidate();
        requestLayout();

        boolean isDown = true;
        float startClickPosition = 0;
        float endClickPosition = 0;


        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return isDown;
    }
}
