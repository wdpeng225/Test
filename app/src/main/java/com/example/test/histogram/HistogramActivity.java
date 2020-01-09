package com.example.test.histogram;

import android.os.Bundle;

import com.example.test.R;
import com.example.test.activity.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Administrator on 2020/1/6.
 */

public class HistogramActivity extends BaseActivity {

    private Random random = new Random();
    private ArrayList<HashMap<String, Object>> pileNoDateList = new ArrayList<>();

    private HistogramsView histogramView;

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
        histogramView = findViewById(R.id.histogram_chart_view);
    }

    @Override
    protected void initData() {
        super.initData();

        pileNoDateList.clear();
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("startPileNo", i);
            if (i >25) {
                data.put("upCount", 60);
            } else {

                data.put("upCount", i);
            }
            pileNoDateList.add(data);
        }
    }

    @Override
    protected void bindData() {
        super.bindData();
        histogramView.setData(pileNoDateList);
        histogramView.invalidate();
        histogramView.requestLayout();
    }
}
