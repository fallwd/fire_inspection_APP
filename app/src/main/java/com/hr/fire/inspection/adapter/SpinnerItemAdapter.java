package com.hr.fire.inspection.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hr.fire.inspection.R;
public class SpinnerItemAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

//    private View setCursListener(String[] curs, int select, boolean monitor) {
//        Spinner spinner = (Spinner)findViewById(R.id.spinner1);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, curs);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setSelection(select);
//        if(monitor){
//            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
//                    curyid = position;
//                    //showPrice(position);
//                    TextView tv = (TextView)view;
//                    tv.setTextColor(getResources().getColor(R.color.white));    //设置颜色
//
//                    tv.setTextSize(12.0f);    //设置大小
//
//                    tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);   //设置居中
//                }
//                @Override
//                public void onNothingSelected(AdapterView<?> parent){}
//            });
//        }
//    }
}
