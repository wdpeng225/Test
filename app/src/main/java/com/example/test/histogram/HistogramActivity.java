package com.example.test.histogram;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2020/1/6.
 */

public class HistogramActivity extends BaseActivity {

    private Random random = new Random();

    private List<HistogramData> data = new ArrayList<>();

    private HorizontalScrollView horizontalScrollView;

    private HistogramsView histogramView;

    private int positionSelected = 0;

    private boolean isViewInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_histogram);
        setTitleString(getResources().getString(R.string.title_activity_histogram));
        onCreateView();
    }

    @Override
    protected void initView() {
        super.initView();
        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        histogramView = findViewById(R.id.histogram_chart_view);
    }

    @Override
    protected void initData() {
        super.initData();

        for (int i = 0; i < 30; i++) {
           data.add(new HistogramData("第" + (i + 1) + "条", random.nextInt(60)));
        }
        positionSelected = data.size() - 1;
    }

    @Override
    protected void bindData() {
        super.bindData();
        histogramView.setData(data);
        histogramView.invalidate();
        histogramView.requestLayout();
        int width = histogramView.getMeasuredWidth();
        horizontalScrollView.scrollTo(width, 0);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void initListener() {
        super.initListener();

        // histogramView加载完成时回调
        histogramView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isViewInit) {
                    int width = (int) getResources().getDimension(R.dimen.DIMEN_100PX) * positionSelected;
                    horizontalScrollView.scrollTo(width, 0);
                    isViewInit = false;
                }
            }
        });

        histogramView.setItemSelelctListener(new ItemSelelctListener() {
            @Override
            public void itemSelect(int selectPosition) {
                Toast.makeText(HistogramActivity.this, "第：" + (selectPosition + 1) + "项被点击！！！！", Toast.LENGTH_SHORT).show();
                positionSelected = selectPosition;
            }
        });
    }


}
