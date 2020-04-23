package com.hr.fire.inspection.service.impl;

import android.database.Cursor;
import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.AnalysisService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AnalysisServiceImpl extends BaseServiceImpl implements AnalysisService {
    @Override
    public List<HashMap> getCompanyCountByYearCheck(String year) {
        // 拼接年份
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = format.parse(year + "-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = format.parse(year + "-12-31 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(%s.%s) AS C,%s.%s AS COMPANY FROM %s INNER JOIN %s ON %s.COMPANY_INFO_ID=%s._id " +
                        "WHERE %s.%s='否' AND CAST(%s.CHECK_DATE AS INTEGER)>%s AND CAST(%s.CHECK_DATE AS INTEGER)<%s GROUP BY %s.%s",
                YearCheckResultDao.TABLENAME,
                YearCheckResultDao.Properties.IsPass.columnName,
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.CompanyName.columnName,
                YearCheckResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,
                YearCheckResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,
                YearCheckResultDao.TABLENAME,
                YearCheckResultDao.Properties.IsPass.columnName,
                YearCheckResultDao.TABLENAME,
                startDate.getTime(),
                YearCheckResultDao.TABLENAME,
                endDate.getTime(),
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.CompanyName.columnName

        ), new String []{});
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            Log.i("tang","getCompanyCountByYearCheck");
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("C")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("count",cursor.getString(cursor.getColumnIndex("C")));
            retList.add(retObj);
        }
        return retList;
    }

    @Override
    public List<HashMap> getOilfieldCountByYearCheck(String year) {
        // 拼接年份
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = format.parse(year + "-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = format.parse(year + "-12-31 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(%s.%s) AS C,%s.%s AS COMPANY,%s.%s AS OILFIELD FROM %s INNER JOIN %s ON %s.COMPANY_INFO_ID=%s._id " +
                        "WHERE %s.%s='否' AND CAST(%s.CHECK_DATE AS INTEGER)>%s AND CAST(%s.CHECK_DATE AS INTEGER)<%s GROUP BY %s.%s",
                YearCheckResultDao.TABLENAME,
                YearCheckResultDao.Properties.IsPass.columnName,
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.CompanyName.columnName,
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.OilfieldName.columnName,
                YearCheckResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,
                YearCheckResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,
                YearCheckResultDao.TABLENAME,
                YearCheckResultDao.Properties.IsPass.columnName,
                YearCheckResultDao.TABLENAME,
                startDate.getTime(),
                YearCheckResultDao.TABLENAME,
                endDate.getTime(),
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.OilfieldName.columnName

        ), new String []{});
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            Log.i("tang","getCompanyCountByYearCheck");
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("C")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
            retObj.put("count",cursor.getString(cursor.getColumnIndex("C")));
            retList.add(retObj);
        }
        return retList;
    }

    @Override
    public List<HashMap> getPlatformCountByYearCheck(String year) {
        // 拼接年份
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = format.parse(year + "-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = format.parse(year + "-12-31 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(%s.%s) AS C,%s.%s AS COMPANY,%s.%s AS OILFIELD,%s.%s AS PLATFORM FROM %s INNER JOIN %s ON %s.COMPANY_INFO_ID=%s._id " +
                        "WHERE %s.%s='否' AND CAST(%s.CHECK_DATE AS INTEGER)>%s AND CAST(%s.CHECK_DATE AS INTEGER)<%s GROUP BY %s.%s",
                YearCheckResultDao.TABLENAME,
                YearCheckResultDao.Properties.IsPass.columnName,
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.CompanyName.columnName,
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.OilfieldName.columnName,
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.PlatformName.columnName,
                YearCheckResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,
                YearCheckResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,
                YearCheckResultDao.TABLENAME,
                YearCheckResultDao.Properties.IsPass.columnName,
                YearCheckResultDao.TABLENAME,
                startDate.getTime(),
                YearCheckResultDao.TABLENAME,
                endDate.getTime(),
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.PlatformName.columnName

        ), new String []{});
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            Log.i("tang","getCompanyCountByYearCheck");
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("C")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
            retObj.put("platform",cursor.getString(cursor.getColumnIndex("PLATFORM")));
            retObj.put("count",cursor.getString(cursor.getColumnIndex("C")));
            retList.add(retObj);
        }
        return retList;
    }

    @Override
    public List<HashMap> getSystemCountByYearCheck(String year) {
        // 拼接年份
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = format.parse(year + "-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = format.parse(year + "-12-31 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(Q.%s) AS C,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
//                        "WHERE Q.%s='否' AND CAST(%s.CHECK_DATE AS INTEGER)>%s AND CAST(%s.CHECK_DATE AS INTEGER)<%s INNER JOIN %s AS E1 ON Q.CHECK_TYPE_ID=E1._id INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id " +
//                        "GROUP BY Q.%s,%s.%s",
////                YearCheckResultDao.TABLENAME,
//                YearCheckResultDao.Properties.IsPass.columnName,
////                CompanyInfoDao.TABLENAME,
//                CompanyInfoDao.Properties.CompanyName.columnName,
////                CompanyInfoDao.TABLENAME,
//                CompanyInfoDao.Properties.OilfieldName.columnName,
////                CompanyInfoDao.TABLENAME,
//                CompanyInfoDao.Properties.PlatformName.columnName,
//                YearCheckResultDao.TABLENAME,
//                CompanyInfoDao.TABLENAME,
////                YearCheckResultDao.TABLENAME,
////                CompanyInfoDao.TABLENAME,
////                YearCheckResultDao.TABLENAME,
//                YearCheckResultDao.Properties.IsPass.columnName,
////                YearCheckResultDao.TABLENAME,
//                startDate.getTime(),
////                YearCheckResultDao.TABLENAME,
//                endDate.getTime(),
//                CheckTypeDao.TABLENAME,
////                YearCheckResultDao.TABLENAME,
////                CheckTypeDao.TABLENAME,
//
////                CompanyInfoDao.TABLENAME,
//                CompanyInfoDao.Properties.PlatformName.columnName,
//                CheckTypeDao.TABLENAME,
//                CheckTypeDao.Properties.Name.columnName
//
//        ), new String []{});
        ArrayList<HashMap> retList = new ArrayList();
//        while (cursor.moveToNext()) {
//            HashMap retObj = new HashMap();
////            Log.i("tang","getCompanyCountByYearCheck");
////            Log.i("tang",cursor.getString(cursor.getColumnIndex("C")));
////            Log.i("tang",cursor.getString(cursor.getColumnIndex("COMPANY")));
//            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
//            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
//            retObj.put("platform",cursor.getString(cursor.getColumnIndex("PLATFORM")));
//            retObj.put("count",cursor.getString(cursor.getColumnIndex("C")));
//            retList.add(retObj);
//        }
        return retList;
    }
}
