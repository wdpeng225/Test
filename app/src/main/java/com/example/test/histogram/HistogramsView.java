package com.example.test.histogram;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 各路段事故数量统计
 *
 * @author
 */
public class HistogramsView extends View {

    private String[] pileNo;
    private Resources resources;
    List<HashMap<String, Object>> pileNoDateList = new ArrayList<HashMap<String, Object>>();

    private List<HashMap<String, Integer>> accident_data = new ArrayList<HashMap<String, Integer>>();

    private int[] accident_count;

    private int maxValue;

    private int addValue;

    private Paint paintCommon = new Paint();

    private Path path = new Path();

    private int screenWidth = 0;

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

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth();
        int height = (int) resources.getDimension(R.dimen.DIMEN_400PX);
        setMeasuredDimension(measureWidth(), height);
    }

    private int measureWidth() {
        int result = 0; // 结果
        int myWidth = 0;
        if (null != pileNo && pileNo.length > 0) {
//            myWidth = (int) resources.getDimension(R.dimen.accident_begin_x) + (int) (pileNo.length * resources.getDimension(R.dimen.accident_y_space) + resources.getDimension(R.dimen.accident_begin_y) * 2);
            myWidth = (int) (pileNo.length * resources.getDimension(R.dimen.DIMEN_100PX));
        }

        return myWidth;
    }

    public void setData(List<HashMap<String, Object>> pileNoDateList) {

        accident_count = new int[]{0, 5, 10, 15, 20, 25};
        maxValue = 0;
        accident_data.clear();

        this.pileNoDateList.clear();
        this.pileNoDateList.addAll(pileNoDateList);

        pileNo = new String[pileNoDateList.size()];

        for (int i = 0; i < pileNoDateList.size(); i++) {
            HashMap<String, Integer> hm = new HashMap<String, Integer>();
            int pileNoCache = (int) Float.parseFloat(pileNoDateList.get(i).get("startPileNo").toString());
            pileNo[i] = String.valueOf(pileNoCache);


            int upCount = (int) Float.parseFloat(pileNoDateList.get(i).get("upCount").toString());

            if (maxValue < upCount) {
                maxValue = upCount;
            }

            hm.put("left", (int) Float.parseFloat(pileNoDateList.get(i).get("upCount").toString()));

            accident_data.add(hm);

        }

        if (maxValue > 25) {
            addValue = (int) ((maxValue / 5f) + 1f);
            for (int i = 1; i < accident_count.length; i++) {
                accident_count[i] = i * addValue;
            }
        } else {
            addValue = 5;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resources = null;
        pileNoDateList.clear();
        pileNoDateList = null;
        accident_data.clear();
        accident_data = null;
        paintCommon = null;
        path = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float item_width = resources.getDimension(R.dimen.DIMEN_30PX);

        float column_width = resources.getDimension(R.dimen.DIMEN_100PX);

        float column_height = resources.getDimension(R.dimen.DIMEN_400PX);

        path.reset();

        if (null != pileNo && pileNo.length > 0) {

            // 绘制X周刻度
            paintCommon.reset();
            paintCommon.setAntiAlias(true);// 抗锯齿
            paintCommon.setColor(Color.parseColor("#7E7E7E"));
            paintCommon.setTextSize(resources.getDimension(R.dimen.DIMEN_20PX));
            paintCommon.setTextAlign(Align.CENTER);
            // 绘制桩号
            for (int i = 0; i < pileNo.length; i++) {
                canvas.drawText("第" + pileNo[i] + "条", column_width * i + column_width / 2, resources.getDimension(R.dimen.DIMEN_20PX), paintCommon);
            }

            int accident_data_count = accident_data.size();

            if (accident_data_count > 0) {
                for (int i = 0; i < accident_data_count; i++) {

                    int leftSize = accident_data.get(i).get("left");

                    float item_height = (resources.getDimension(R.dimen.DIMEN_400PX) -  resources.getDimension(R.dimen.DIMEN_50PX))* leftSize / 60  ;

                    float position_item_top = (column_height - item_height);

                    float position_item_bottom = resources.getDimension(R.dimen.DIMEN_400PX);

                    // 左方向
                    paintCommon.reset();
                    paintCommon.setAntiAlias(true);// 抗锯齿
                    paintCommon.setTextSize(resources.getDimension(R.dimen.DIMEN_14PX));
                    paintCommon.setColor(Color.parseColor("#00ff00"));
                    LinearGradient linearGradient = new LinearGradient(0,position_item_top,0,position_item_bottom,resources.getColor(R.color.blue_769DF8), resources.getColor(R.color.blue_76C4FF), Shader.TileMode.CLAMP);
                    paintCommon.setShader(linearGradient);
                    canvas.drawRoundRect(column_width * i + (column_width - item_width)/2, position_item_top, column_width * i + (column_width - item_width)/2 + item_width, position_item_bottom,(float) 50, (float) 50, paintCommon);

                    // 绘制左方向的数值
                    paintCommon.reset();
                    paintCommon.setAntiAlias(true);// 抗锯齿
                    paintCommon.setTextSize(resources.getDimension(R.dimen.DIMEN_14PX));
                    paintCommon.setTextAlign(Align.CENTER);
                    paintCommon.setColor(Color.parseColor("#ff00ff"));
                    canvas.drawText(String.valueOf(leftSize), //
                            column_width * i + column_width/2, //
                            position_item_top - resources.getDimension(R.dimen.DIMEN_10PX), //
                            paintCommon);//

                    // 绘制竖向分割线
                    paintCommon.reset();
                    paintCommon.setAntiAlias(true);
                    paintCommon.setColor(Color.parseColor("#000000"));
                    paintCommon.setStrokeWidth((float) 1.0);
                    canvas.drawLine(column_width * (i + 1), (float)10.0, column_width * (i + 1), getResources().getDisplayMetrics().heightPixels/3, paintCommon);
                }

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("===touch position===", "======" + event.getX() + "======");

        float column_width = resources.getDimension(R.dimen.DIMEN_100PX);
        int position = (int) (event.getX()/column_width);

        Toast.makeText(getContext(), "第：" + position + "项被点击！！！！", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
