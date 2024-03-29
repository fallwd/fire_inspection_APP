package com.hr.fire.inspection.service.impl;

import android.database.Cursor;
import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.InspectionResultDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.AnalysisService;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

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
        ArrayList<HashMap> allDataList = new ArrayList<>();
        HashMap currentData = new HashMap();
        currentData.put("name","company");
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            Log.i("tang","getCompanyCountByYearCheck");
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("C")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("name",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("value",cursor.getString(cursor.getColumnIndex("C")));
            retObj.put("type","company");
            retList.add(retObj);
        }
        currentData.put("data",retList);
        allDataList.add(currentData);
        return allDataList;
    }

    @Override
    public List<HashMap> getCompanyCountByInspection(String year) {
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

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(A.%s) AS C,B.%s AS COMPANY FROM %s AS A INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id " +
                        "WHERE " +
                        "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                        " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                        "AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s",
                InspectionResultDao.Properties.CheckTypeId.columnName,
                CompanyInfoDao.Properties.CompanyName.columnName,
                InspectionResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,

                startDate.getTime(),
                endDate.getTime(),

                CompanyInfoDao.Properties.CompanyName.columnName

        ), new String []{});
        ArrayList<HashMap> allDataList = new ArrayList<>();
        HashMap currentData = new HashMap();
        currentData.put("name","company");
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            Log.i("tang","getCompanyCountByYearCheck");
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("C")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("name",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("value",cursor.getString(cursor.getColumnIndex("C")));
            retObj.put("type","company");
            retList.add(retObj);
        }
        currentData.put("data", retList);
        allDataList.add(currentData);
        return allDataList;
    }

    @Override
    public List<HashMap> getOilfieldCountByYearCheck(String year, String companyName) {
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
        List<HashMap> allDataList = this.getCompanyCountByYearCheck(year);

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(%s.%s) AS C,%s.%s AS COMPANY,%s.%s AS OILFIELD FROM %s INNER JOIN %s ON %s.COMPANY_INFO_ID=%s._id " +
                        "WHERE %s.%s='否' AND %s.%s='%s' AND CAST(%s.CHECK_DATE AS INTEGER)>%s AND CAST(%s.CHECK_DATE AS INTEGER)<%s GROUP BY %s.%s",
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

                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.CompanyName.columnName,
                companyName,

                YearCheckResultDao.TABLENAME,
                startDate.getTime(),
                YearCheckResultDao.TABLENAME,
                endDate.getTime(),
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.OilfieldName.columnName

        ), new String []{});
        HashMap currentData = new HashMap();
        currentData.put("name","oilfield");
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("name",cursor.getString(cursor.getColumnIndex("OILFIELD")));
            retObj.put("value",cursor.getString(cursor.getColumnIndex("C")));
            retObj.put("param1",companyName);
            retObj.put("type","oilfield");
            retList.add(retObj);
        }
        currentData.put("data",retList);
        allDataList.add(currentData);
        return allDataList;
    }

    @Override
    public List<HashMap> getOilfieldCountByInspection(String year) {
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

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(A.%s) AS C,B.%s AS COMPANY,B.%s AS OILFIELD FROM %s AS A INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id " +
                        "WHERE " +
                        "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                        " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                        "AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s",
                InspectionResultDao.Properties.CheckTypeId.columnName,
                CompanyInfoDao.Properties.CompanyName.columnName,
                CompanyInfoDao.Properties.OilfieldName.columnName,
                InspectionResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,

                startDate.getTime(),
                endDate.getTime(),

                CompanyInfoDao.Properties.OilfieldName.columnName

        ), new String []{});
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
            retObj.put("count",cursor.getString(cursor.getColumnIndex("C")));
            retList.add(retObj);
        }
        return retList;
    }

    @Override
    public List<HashMap> getPlatformCountByYearCheck(String year, String companyName, String oilfieldName) {
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
        List<HashMap> allDataList = this.getOilfieldCountByYearCheck(year,companyName);

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(%s.%s) AS C,%s.%s AS COMPANY,%s.%s AS OILFIELD,%s.%s AS PLATFORM FROM %s INNER JOIN %s ON %s.COMPANY_INFO_ID=%s._id " +
                        "WHERE %s.%s='否' AND %s.%s='%s' AND CAST(%s.CHECK_DATE AS INTEGER)>%s AND CAST(%s.CHECK_DATE AS INTEGER)<%s GROUP BY %s.%s",
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

                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.OilfieldName.columnName,
                oilfieldName,

                YearCheckResultDao.TABLENAME,
                startDate.getTime(),
                YearCheckResultDao.TABLENAME,
                endDate.getTime(),
                CompanyInfoDao.TABLENAME,
                CompanyInfoDao.Properties.PlatformName.columnName

        ), new String []{});
        HashMap currentData = new HashMap();
        currentData.put("name","platform");
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
//            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
            retObj.put("name",cursor.getString(cursor.getColumnIndex("PLATFORM")));
            retObj.put("value",cursor.getString(cursor.getColumnIndex("C")));
            retObj.put("param1",companyName);
            retObj.put("param2",oilfieldName);
            retObj.put("type","platform");
            retList.add(retObj);
        }
        currentData.put("data",retList);
        allDataList.add(currentData);
        return allDataList;
    }

    @Override
    public List<HashMap> getPlatformCountByInspection(String year) {
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

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(A.%s) AS C,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM FROM %s AS A INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id " +
                        "WHERE " +
                        "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                        " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                        "AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s",
                InspectionResultDao.Properties.CheckTypeId.columnName,
                CompanyInfoDao.Properties.CompanyName.columnName,
                CompanyInfoDao.Properties.OilfieldName.columnName,
                CompanyInfoDao.Properties.PlatformName.columnName,
                InspectionResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,

                startDate.getTime(),
                endDate.getTime(),

                CompanyInfoDao.Properties.PlatformName.columnName

        ), new String []{});
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
            retObj.put("platform",cursor.getString(cursor.getColumnIndex("PLATFORM")));
            retObj.put("count",cursor.getString(cursor.getColumnIndex("C")));
            retList.add(retObj);
        }
        return retList;
    }

    @Override
    public List<HashMap> getSystemCountByYearCheck(String year, String companyName, String oilfieldName, String platform) {
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
        // 三层关系sql 思路 两张表联合，
        // 一张表inner join parentId=0 --->一级
        // 另一张表 inner join parentId!=0 --->二级 inner join parentId=0 一级
        List<HashMap> allDataList = this.getPlatformCountByYearCheck(year,companyName,oilfieldName);
        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(Q.%s) AS C,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                        "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                        "WHERE Q.%s='否' AND W.%s='%s' AND CAST(Q.CHECK_DATE AS INTEGER)>%s AND CAST(Q.CHECK_DATE AS INTEGER)<%s GROUP BY W.%s,R.PARENT_NAME",
                YearCheckResultDao.Properties.IsPass.columnName,
                CompanyInfoDao.Properties.CompanyName.columnName,
                CompanyInfoDao.Properties.OilfieldName.columnName,
                CompanyInfoDao.Properties.PlatformName.columnName,
                YearCheckResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,

                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,

                YearCheckResultDao.Properties.IsPass.columnName,

                CompanyInfoDao.Properties.PlatformName.columnName,
                platform,

                startDate.getTime(),
                endDate.getTime(),
                CompanyInfoDao.Properties.PlatformName.columnName

        ), new String []{});
        HashMap currentData = new HashMap();
        currentData.put("name","system");
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
//            Log.i("tang","getCompanyCountByYearCheck");
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("C")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("COMPANY")));
//            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
//            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
//            retObj.put("platform",cursor.getString(cursor.getColumnIndex("PLATFORM")));
            retObj.put("name",cursor.getString(cursor.getColumnIndex("SYSTEM")));
            retObj.put("value",cursor.getString(cursor.getColumnIndex("C")));
            retObj.put("type","system");
            retList.add(retObj);
        }

        // 测试sql
//        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT * FROM (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R WHERE R.PARENT_ID=1",
//                CheckTypeDao.TABLENAME,
//                CheckTypeDao.TABLENAME,
//                CheckTypeDao.TABLENAME,
//                CheckTypeDao.TABLENAME,
//                CheckTypeDao.TABLENAME
//        ), new String []{});
//        while (cursor.moveToNext()) {
////            HashMap retObj = new HashMap();
//            Log.i("tang","getCompanyCountByYearCheck");
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("ID")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("NAME")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
//            Log.i("tang",cursor.getString(cursor.getColumnIndex("PARENT_NAME")));
////            retList.add(retObj);
//        }

        currentData.put("data",retList);
        allDataList.add(currentData);
        return allDataList;
    }

    @Override
    public List<HashMap> getSystemCountByInspection(String year) {
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

        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(A.%s) AS C,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM FROM %s AS A INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
//                        "(SELECT E1._id AS ID,E1.NAME AS NAME,E1.PARENT_ID AS PARENT_ID,E2.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id WHERE E1.TYPE=2 AND E2.PARENT_ID=0) AS E ON A.CHECK_TYPE_ID=E.ID " +
                        "WHERE " +
                        "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                        " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                        "AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s,E.NAME",
                InspectionResultDao.Properties.CheckTypeId.columnName,
                CompanyInfoDao.Properties.CompanyName.columnName,
                CompanyInfoDao.Properties.OilfieldName.columnName,
                CompanyInfoDao.Properties.PlatformName.columnName,
                InspectionResultDao.TABLENAME,
                CompanyInfoDao.TABLENAME,

                CheckTypeDao.TABLENAME,
//                CheckTypeDao.TABLENAME,

                startDate.getTime(),
                endDate.getTime(),

                CompanyInfoDao.Properties.PlatformName.columnName

        ), new String []{});
        ArrayList<HashMap> retList = new ArrayList();
        while (cursor.moveToNext()) {
            HashMap retObj = new HashMap();
            retObj.put("company",cursor.getString(cursor.getColumnIndex("COMPANY")));
            retObj.put("oilfield",cursor.getString(cursor.getColumnIndex("OILFIELD")));
            retObj.put("platform",cursor.getString(cursor.getColumnIndex("PLATFORM")));
            retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
            retObj.put("count",cursor.getString(cursor.getColumnIndex("C")));
            retList.add(retObj);
        }
        return retList;
    }

    @Override
    public List<HashMap> getYearCheckView(long platformId, long systemId, Date startDate, Date endDate) {
        // 多个判断
//        QueryBuilder<YearCheckResult> queryBuilder;
        String company;
        String oilfield;
        String platform;
        String checkDate;
        if(platformId==0 && systemId==0){
            if(startDate!=null && endDate!=null){
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' AND CAST(Q.CHECK_DATE AS INTEGER)>%s AND CAST(Q.CHECK_DATE AS INTEGER)<%s GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,
                        startDate.getTime(),
                        endDate.getTime(),
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea",cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber",cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
            else {
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,

                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea",cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber",cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            }

        }
        else if(platformId!=0 && systemId==0){
            if(startDate!=null && endDate!=null){
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' AND Q.%s=%s AND CAST(Q.CHECK_DATE AS INTEGER)>%s AND CAST(Q.CHECK_DATE AS INTEGER)<%s GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,
                        YearCheckResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        startDate.getTime(),
                        endDate.getTime(),
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea",cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber",cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
            else {
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' AND Q.%s=%s GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,
                        YearCheckResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea",cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber",cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            }

        }
        else if(platformId==0 && systemId!=0) {
            if (startDate != null && endDate != null) {
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' AND R.PARENT_ID=%s AND CAST(Q.CHECK_DATE AS INTEGER)>%s AND CAST(Q.CHECK_DATE AS INTEGER)<%s GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,
//                        CheckTypeDao.Properties.ParentId.columnName,
                        systemId,
                        startDate.getTime(),
                        endDate.getTime(),
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String[]{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company", company + "_" + oilfield + "_" + platform);
                    retObj.put("system", cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea", cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber", cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate", checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            } else {
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' AND R.PARENT_ID=%s GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,
//                        CheckTypeDao.Properties.ParentId.columnName,
                        systemId,
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String[]{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company", company + "_" + oilfield + "_" + platform);
                    retObj.put("system", cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea", cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber", cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate", checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
        }
        else if(platformId!=0 && systemId!=0) {
            Log.e("aaaaaa", "开始时间："+ startDate.getTime());
            Log.e("aaaaaa", "结束时间："+ endDate.getTime());
            Log.e("aaaaaa", "platformId："+ platformId);
            Log.e("aaaaaa", "systemId："+ systemId);
            if (startDate != null && endDate != null) {
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' AND R.PARENT_ID=%s AND Q.%s=%s AND CAST(Q.CHECK_DATE AS INTEGER)>%s AND CAST(Q.CHECK_DATE AS INTEGER)<%s GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,
//                        CheckTypeDao.Properties.ParentId.columnName,
                        systemId,
                        YearCheckResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        startDate.getTime(),
                        endDate.getTime(),
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String[]{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company", company + "_" + oilfield + "_" + platform);
                    retObj.put("system", cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea", cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber", cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate", checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            } else {
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT Q.%s AS PROTECT_AREA,Q.%s AS SYSTEM_NUMBER,Q.%s AS CHECK_DATE,W.%s AS COMPANY,W.%s AS OILFIELD,W.%s AS PLATFORM,R.PARENT_NAME AS SYSTEM,W.%s AS COMPANY_ID,R.PARENT_ID AS PARENT_ID FROM %s AS Q INNER JOIN %s AS W ON Q.COMPANY_INFO_ID=W._id " +
                                "INNER JOIN (SELECT E3._id AS ID,E3.NAME AS NAME,E3.PARENT_ID AS PARENT_ID,E4.NAME AS PARENT_NAME FROM %s AS E3 INNER JOIN %s AS E4 ON E3.PARENT_ID=E4._id WHERE E3.TYPE=1 AND E4.PARENT_ID=0 UNION SELECT E1._id AS ID,E1.NAME AS NAME,E5._id AS PARENT_ID,E5.NAME AS PARENT_NAME FROM %s AS E1 INNER JOIN %s AS E2 ON E1.PARENT_ID=E2._id INNER JOIN %s AS E5 ON E2.PARENT_ID=E5._id WHERE E1.TYPE=1 AND E2.PARENT_ID!=0 AND E5.PARENT_ID=0) AS R ON Q.CHECK_TYPE_ID=R.ID " +
                                "WHERE Q.%s='否' AND R.PARENT_ID=%s AND Q.%s=%s GROUP BY W.%s,R.PARENT_NAME,Q.CHECK_DATE,Q.PROTECT_AREA,Q.SYSTEM_NUMBER",
                        YearCheckResultDao.Properties.ProtectArea.columnName,
                        YearCheckResultDao.Properties.SystemNumber.columnName,
                        YearCheckResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        CompanyInfoDao.Properties.Id.columnName,
                        YearCheckResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.TABLENAME,

                        YearCheckResultDao.Properties.IsPass.columnName,
//                        CheckTypeDao.Properties.ParentId.columnName,
                        systemId,
                        YearCheckResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String[]{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company", company + "_" + oilfield + "_" + platform);
                    retObj.put("system", cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("protectArea", cursor.getString(cursor.getColumnIndex("PROTECT_AREA")));
                    retObj.put("systemNumber", cursor.getString(cursor.getColumnIndex("SYSTEM_NUMBER")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate", checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("PARENT_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
        }

        return null;
    }

    @Override
    public List<HashMap> getInspectionView(long platformId, long systemId, Date startDate, Date endDate) {
        String company;
        String oilfield;
        String platform;
        String checkDate;
        if(platformId==0 && systemId==0){
            if(startDate!=null && endDate!=null){
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,

                        startDate.getTime(),
                        endDate.getTime(),

                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
            else{
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
        }
        ///////////
        else if(platformId!=0 && systemId==0){
            if(startDate!=null && endDate!=null){
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "AND A.%s=%s AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,

                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        startDate.getTime(),
                        endDate.getTime(),

                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
            else{
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "AND A.%s=%s GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
        }
        //////
        else if(platformId==0 && systemId!=0){
            if(startDate!=null && endDate!=null){
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "AND E.%s=%s AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,

                        CheckTypeDao.Properties.Id.columnName,
                        systemId,
                        startDate.getTime(),
                        endDate.getTime(),

                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
            else{
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "AND E.%s=%s GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.Properties.Id.columnName,
                        systemId,
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
        }
        else if(platformId!=0 && systemId!=0){
            if(startDate!=null && endDate!=null){
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "AND E.%s=%s AND A.%s=%s AND CAST(A.CHECK_DATE AS INTEGER)>%s AND CAST(A.CHECK_DATE AS INTEGER)<%s GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,

                        CheckTypeDao.Properties.Id.columnName,
                        systemId,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        startDate.getTime(),
                        endDate.getTime(),

                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
            else{
                Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT A.%s AS CHECK_PERSON,A.%s AS PROFESSION,A.%s AS CHECK_DATE,B.%s AS COMPANY,B.%s AS OILFIELD,B.%s AS PLATFORM,E.NAME AS SYSTEM,A.%s AS COMPANY_ID,E.%s AS SYSTEM_ID FROM %s AS A " +
                                "INNER JOIN %s AS B ON A.COMPANY_INFO_ID=B._id INNER JOIN %s AS E ON A.CHECK_TYPE_ID=E._id " +
                                "WHERE " +
                                "(A.PARAM1='否' OR A.PARAM2='否' OR A.PARAM3='否' OR A.PARAM4='否' OR A.PARAM5='否' OR A.PARAM6='否' OR A.PARAM7='否' OR A.PARAM8='否' OR A.PARAM9='否' OR A.PARAM10='否' OR A.PARAM11='否'" +
                                " OR A.PARAM12='否' OR A.PARAM13='否' OR A.PARAM14='否' OR A.PARAM15='否' OR A.PARAM16='否' OR A.PARAM17='否' OR A.PARAM18='否' OR A.PARAM19='否' OR A.PARAM20='否' OR A.PARAM21='否' OR A.PARAM22='否' OR A.PARAM23='否' OR A.PARAM24='否' OR A.PARAM25='否' OR A.PARAM26='否') " +
                                "AND E.%s=%s AND A.%s=%s GROUP BY B.%s,E.NAME,A.CHECK_DATE,A.CHECK_PERSON,A.PROFESSION",
                        InspectionResultDao.Properties.CheckPerson.columnName,
                        InspectionResultDao.Properties.Profession.columnName,
                        InspectionResultDao.Properties.CheckDate.columnName,
                        CompanyInfoDao.Properties.CompanyName.columnName,
                        CompanyInfoDao.Properties.OilfieldName.columnName,
                        CompanyInfoDao.Properties.PlatformName.columnName,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        CheckTypeDao.Properties.Id.columnName,
                        InspectionResultDao.TABLENAME,
                        CompanyInfoDao.TABLENAME,

                        CheckTypeDao.TABLENAME,
                        CheckTypeDao.Properties.Id.columnName,
                        systemId,
                        InspectionResultDao.Properties.CompanyInfoId.columnName,
                        platformId,
                        CompanyInfoDao.Properties.PlatformName.columnName

                ), new String []{});
                ArrayList<HashMap> retList = new ArrayList();
                while (cursor.moveToNext()) {
                    HashMap retObj = new HashMap();
                    company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                    oilfield = cursor.getString(cursor.getColumnIndex("OILFIELD"));
                    platform = cursor.getString(cursor.getColumnIndex("PLATFORM"));
                    retObj.put("company",company+"_"+oilfield+"_"+platform);
                    retObj.put("system",cursor.getString(cursor.getColumnIndex("SYSTEM")));
                    retObj.put("checkPerson",cursor.getString(cursor.getColumnIndex("CHECK_PERSON")));
                    retObj.put("profession",cursor.getString(cursor.getColumnIndex("PROFESSION")));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkDate = cursor.getString(cursor.getColumnIndex("CHECK_DATE"));
                    checkDate = format.format(new Date(Long.valueOf(checkDate)));
                    retObj.put("checkDate",checkDate);
                    retObj.put("platformId",cursor.getString(cursor.getColumnIndex("COMPANY_ID")));
                    retObj.put("systemId",cursor.getString(cursor.getColumnIndex("SYSTEM_ID")));
                    retList.add(retObj);
                }
                return retList;
            }
        }

        return null;
    }

    @Override
    public List<YearCheckResult> getYearCheckDetail(long platformId, long systemId, String checkDate, String systemNumber, String protectArea) {
        QueryBuilder<YearCheckResult> queryBuilder;
        List<YearCheckResult> dataList1;
        List<YearCheckResult> dataList;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date checkDateD = null;
        try {
            checkDateD = format.parse(checkDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        queryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.SystemNumber.eq(systemNumber),
                        YearCheckResultDao.Properties.ProtectArea.eq(protectArea),
                        YearCheckResultDao.Properties.CompanyInfoId.eq(platformId),
                        YearCheckResultDao.Properties.CheckDate.eq(checkDateD),
                        YearCheckResultDao.Properties.IsPass.eq("否")
                );
        Join checkTypeJoin = queryBuilder.join(YearCheckResultDao.Properties.CheckTypeId, CheckType.class)
                .where(CheckTypeDao.Properties.Id.eq(systemId));

        dataList1 = queryBuilder.list();

        queryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.SystemNumber.eq(systemNumber),
                        YearCheckResultDao.Properties.ProtectArea.eq(protectArea),
                        YearCheckResultDao.Properties.CompanyInfoId.eq(platformId),
                        YearCheckResultDao.Properties.CheckDate.eq(checkDateD),
                        YearCheckResultDao.Properties.IsPass.eq("否")
                );
        Join checkTypeJoin2 = queryBuilder.join(YearCheckResultDao.Properties.CheckTypeId, CheckType.class);
        Join checkTypeParentJoin2 = queryBuilder.join(checkTypeJoin, CheckTypeDao.Properties.ParentId, CheckType.class,CheckTypeDao.Properties.Id)
                .where(CheckTypeDao.Properties.Id.eq(systemId));
        dataList = queryBuilder.list();
        dataList.addAll(dataList1);

        return dataList;
    }

    @Override
    public List<InspectionResult> getInspectionDetail(long platformId, long systemId, String checkDate, String checkPerson, String profession) {

        QueryBuilder<InspectionResult> queryBuilder;
        List<InspectionResult> dataList1;
        List<InspectionResult> dataList;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date checkDateD = null;
        try {
            checkDateD = format.parse(checkDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 如何实现or查询
        queryBuilder = daoSession.queryBuilder(InspectionResult.class).
//                where(
//                        daoSession.queryBuilder(InspectionResult.class).and(
//                        InspectionResultDao.Properties.CheckPerson.eq(checkPerson),
//                        InspectionResultDao.Properties.Profession.eq(profession),
//                        InspectionResultDao.Properties.CompanyInfoId.eq(platformId),
//                        InspectionResultDao.Properties.CheckDate.eq(checkDateD),
//                        InspectionResultDao.Properties.CheckTypeId.eq(systemId),
//                                daoSession.queryBuilder(InspectionResult.class).or(
//                                        InspectionResultDao.Properties.Param1.eq("否")
//                                )
//                        )
//                );
        where(
        InspectionResultDao.Properties.CheckPerson.eq(checkPerson),
        InspectionResultDao.Properties.Profession.eq(profession),
        InspectionResultDao.Properties.CompanyInfoId.eq(platformId),
        InspectionResultDao.Properties.CheckDate.eq(checkDateD),
        InspectionResultDao.Properties.CheckTypeId.eq(systemId),
        daoSession.queryBuilder(InspectionResult.class).or(
                InspectionResultDao.Properties.Param1.eq("否"),
                InspectionResultDao.Properties.Param2.eq("否"),
                InspectionResultDao.Properties.Param3.eq("否"),
                InspectionResultDao.Properties.Param4.eq("否"),
                InspectionResultDao.Properties.Param5.eq("否"),
                InspectionResultDao.Properties.Param6.eq("否"),
                InspectionResultDao.Properties.Param7.eq("否"),
                InspectionResultDao.Properties.Param8.eq("否"),
                InspectionResultDao.Properties.Param9.eq("否"),
                InspectionResultDao.Properties.Param10.eq("否"),
                InspectionResultDao.Properties.Param11.eq("否"),
                InspectionResultDao.Properties.Param12.eq("否"),
                InspectionResultDao.Properties.Param13.eq("否"),
                InspectionResultDao.Properties.Param14.eq("否"),
                InspectionResultDao.Properties.Param15.eq("否"),
                InspectionResultDao.Properties.Param16.eq("否"),
                InspectionResultDao.Properties.Param17.eq("否"),
                InspectionResultDao.Properties.Param18.eq("否"),
                InspectionResultDao.Properties.Param19.eq("否"),
                InspectionResultDao.Properties.Param20.eq("否"),
                InspectionResultDao.Properties.Param21.eq("否"),
                InspectionResultDao.Properties.Param22.eq("否"),
                InspectionResultDao.Properties.Param23.eq("否"),
                InspectionResultDao.Properties.Param24.eq("否"),
                InspectionResultDao.Properties.Param25.eq("否"),
                InspectionResultDao.Properties.Param26.eq("否")
        )
);
        dataList = queryBuilder.list();
        return dataList;
    }


}
