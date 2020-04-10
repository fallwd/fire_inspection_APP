package com.hr.fire.inspection.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.GoodsAdapter;

public class CarBonGoodsWeightAcitivty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_goods_weight);
        initView();
    }

    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        Button cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button submit_btn = findViewById(R.id.submit_btn);
        ListView list = findViewById(R.id.list);
        GoodsAdapter goodsAdapter = new GoodsAdapter(this);
        list.setAdapter(goodsAdapter);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
