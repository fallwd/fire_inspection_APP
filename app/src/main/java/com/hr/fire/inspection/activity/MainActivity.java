package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.dao.BottleInfoDao;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.utils.GreenDaoHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((GreenDaoHelper) getApplication()).getDaoSession();
        BottleInfoDao bottleInfoDao = daoSession.getBottleInfoDao();
//        bottleInfoDao.insert();

    }
}
