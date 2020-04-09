package com.hr.fire.inspection.sqlHelpers;

import android.text.format.DateUtils;
import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.utils.GreenDaoHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BaseData {

    public void initData() {
        DaoSession daoSession = GreenDaoHelper.getDaoSession();
//        // 插入公司数据
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        List platformNameList = Arrays.asList(
//                "SZ36-1A",
//                "SZ36-1B",
//                "SZ36-1C",
//                "SZ36-1D",
//                "SZ36-1E",
//                "SZ36-1F",
//                "SZ36-1G",
//                "SZ36-1H",
//                "SZ36-1J",
//                "SZ36-1K",
//                "SZ36-1L",
//                "SZ36-1M",
//                "SZ36-1N"
//        );
//        int isNecessary = 1;
//        String platformName;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//
//        companyName = "辽东作业公司";
//        oilfieldName = "LD10-1";
//        platformNameList = Arrays.asList(
//                "LD10-1A",
//                "LD10-1C"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "LD4-2";
//        platformNameList = Arrays.asList(
//                "LD4-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "LD5-2";
//        platformNameList = Arrays.asList(
//                "LD5-2A",
//                "LD5-2B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "LD27-2";
//        platformNameList = Arrays.asList(
//                "LD27-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "LD32-2";
//        platformNameList = Arrays.asList(
//                "LD32-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "JZ9-3";
//        platformNameList = Arrays.asList(
//                "JZ9-3A",
//                "JZ9-3B",
//                "JZ9-3C",
//                "JZ9-3E",
//                "JZ9-3W",
//                "JZ9-3WG"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "JZ20-2";
//        platformNameList = Arrays.asList(
//                "JZ20-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "JZ21-1";
//        platformNameList = Arrays.asList(
//                "JZ21-1A"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "JZ25-1";
//        platformNameList = Arrays.asList(
//                "JZ25-1A",
//                "JZ25-1AG"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "JZ25-1S";
//        platformNameList = Arrays.asList(
//                "JZ25-1SA",
//                "JZ25-1SAG",
//                "JZ25-1SB",
//                "JZ25-1SC",
//                "JZ25-1SD",
//                "JZ25-1SE",
//                "JZ25-1SEG"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "辽东作业公司";
//        oilfieldName = "JX1-1";
//        platformNameList = Arrays.asList(
//                "JX1-1A",
//                "JX1-1B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ28-1";
//        platformNameList = Arrays.asList(
//                "BZ28-1"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ28-2S";
//        platformNameList = Arrays.asList(
//                "BZ28-2SB",
//                "BZ28-2SCEP",
//                "BZ28-2SN"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ34-1";
//        platformNameList = Arrays.asList(
//                "BZ34-1A",
//                "BZ34-1B",
//                "BZ34-1D",
//                "BZ34-1F",
//                "BZ34-1NC",
//                "BZ34-1NE"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ34-2/4";
//        platformNameList = Arrays.asList(
//                "BZ34-2/4",
//                "BZ34-2/4A",
//                "BZ34-2/4B",
//                "BZ34-3",
//                "BZ34-5"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ34-6/7";
//        platformNameList = Arrays.asList(
//                "BZ34-6/7A"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ29-4";
//        platformNameList = Arrays.asList(
//                "BZ29-4",
//                "BZ29-4SC"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ35-2";
//        platformNameList = Arrays.asList(
//                "BZ35-2A",
//                "BZ35-2B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "KL3-2";
//        platformNameList = Arrays.asList(
//                "KL3-2A"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "KL10-1";
//        platformNameList = Arrays.asList(
//                "KL10-1A",
//                "KL10-1B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "KL10-4";
//        platformNameList = Arrays.asList(
//                "KL10-4A"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤南作业公司";
//        oilfieldName = "BZ34-9";
//        platformNameList = Arrays.asList(
//                "BZ34-9A",
//                "BZ34-9B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "秦皇岛作业公司";
//        oilfieldName = "QHD32-6";
//        platformNameList = Arrays.asList(
//                "QHD32-6A",
//                "QHD32-6B",
//                "QHD32-6C",
//                "QHD32-6D",
//                "QHD32-6E",
//                "QHD32-6F",
//                "QHD32-6G",
//                "QHD32-6H",
//                "QHD32-6I",
//                "QHD32-6J"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "秦皇岛作业公司";
//        oilfieldName = "BZ25-1";
//        platformNameList = Arrays.asList(
//                "BZ25-1A"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "秦皇岛作业公司";
//        oilfieldName = "BZ25-1S";
//        platformNameList = Arrays.asList(
//                "BZ25-1B",
//                "BZ25-1C",
//                "BZ25-1D",
//                "BZ25-1E",
//                "BZ25-1F",
//                "BZ25-1M"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "秦皇岛作业公司";
//        oilfieldName = "BZ19-4";
//        platformNameList = Arrays.asList(
//                "BZ19-4A",
//                "BZ19-4B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "CB";
//        platformNameList = Arrays.asList(
//                "CB-A",
//                "CB-B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "QK17-2";
//        platformNameList = Arrays.asList(
//                "QK17-2",
//                "QK17-2E"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "QK17-3";
//        platformNameList = Arrays.asList(
//                "QK17-3"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "QK18-1";
//        platformNameList = Arrays.asList(
//                "QK18-1"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "QK18-2";
//        platformNameList = Arrays.asList(
//                "QK18-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "BZ26-2";
//        platformNameList = Arrays.asList(
//                "BZ26-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "BZ26-3";
//        platformNameList = Arrays.asList(
//                "BZ26-3A",
//                "BZ26-3B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "BZ13-1";
//        platformNameList = Arrays.asList(
//                "BZ13-1"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "CFD18-1";
//        platformNameList = Arrays.asList(
//                "CFD18-1"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "CFD18-2";
//        platformNameList = Arrays.asList(
//                "CFD18-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "CFD2-1";
//        platformNameList = Arrays.asList(
//                "CFD2-1"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "NB35-2";
//        platformNameList = Arrays.asList(
//                "NB35-2A",
//                "NB35-2B"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "BZ3-2";
//        platformNameList = Arrays.asList(
//                "BZ3-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "渤西作业公司";
//        oilfieldName = "QHD33-1";
//        platformNameList = Arrays.asList(
//                "QHD33-1"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "蓬勃作业公司";
//        oilfieldName = "PL19-3";
//        platformNameList = Arrays.asList(
//                "PL19-3A",
//                "PL19-3B",
//                "PL19-3C",
//                "PL19-3D",
//                "PL19-3E",
//                "PL19-3G",
//                "PL19-3K",
//                "PL19-3M",
//                "PL19-3V"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "蓬勃作业公司";
//        oilfieldName = "PL19-9";
//        platformNameList = Arrays.asList(
//                "PL19-9J"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "曹妃甸作业公司";
//        oilfieldName = "CFD11-1";
//        platformNameList = Arrays.asList(
//                "CFD11-1"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "曹妃甸作业公司";
//        oilfieldName = "CFD11-2";
//        platformNameList = Arrays.asList(
//                "CFD11-2"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "曹妃甸作业公司";
//        oilfieldName = "CFD11-3/5";
//        platformNameList = Arrays.asList(
//                "CFD11-3/5"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//        companyName = "曹妃甸作业公司";
//        oilfieldName = "CFD11-6";
//        platformNameList = Arrays.asList(
//                "CFD11-6"
//        );
//        isNecessary = 1;
//        for(int i = 0;i < platformNameList.size();i++){
//            CompanyInfo companyInfo = new CompanyInfo();
//            platformName = platformNameList.get(i).toString();
//            companyInfo.setCompanyName(companyName);
//            companyInfo.setOilfieldName(oilfieldName);
//            companyInfo.setPlatformName(platformName);
//            companyInfo.setIsNecessary(isNecessary);
//            daoSession.insert(companyInfo);
//        }
//
//        Log.i("info","插入公司数据成功");


        // 插入checkType 系统名
//        CheckType checkType = new CheckType();
//        String systemName = "高压二氧化碳系统灭火系统";
//        int type = 1;
//        long parentId = 0;
//        checkType.setName(systemName);
//        checkType.setType(type);
//        checkType.setParentId(parentId);
//        daoSession.insert(checkType);
//        // 设备名
//        CheckType checkType2 = new CheckType();
//        String tableName2 = "药剂瓶";
//        type = 1;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);
//        // 检查表名
//        CheckType checkType3 = new CheckType();
//        String tableName3 = "药剂瓶检查表";
//        type = 1;
//        checkType3.setName(tableName3);
//        checkType3.setType(type);
//        checkType3.setParent(checkType2);
//        daoSession.insert(checkType3);
//
//        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "氮气瓶";
//        type = 1;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);
//        // 检查表名
//        checkType3 = new CheckType();
//        tableName3 = "氮气瓶检查表";
//        type = 1;
//        checkType3.setName(tableName3);
//        checkType3.setType(type);
//        checkType3.setParent(checkType2);
//        daoSession.insert(checkType3);
//
//
//        // 检查表名
//        checkType3 = new CheckType();
//        tableName3 = "管线管件";
//        type = 1;
//        checkType3.setName(tableName3);
//        checkType3.setType(type);
//        checkType3.setParent(checkType);
//        daoSession.insert(checkType3);
//
//        checkType3 = new CheckType();
//        tableName3 = "保护区";
//        type = 1;
//        checkType3.setName(tableName3);
//        checkType3.setType(type);
//        checkType3.setParent(checkType);
//        daoSession.insert(checkType3);
//        QueryBuilder<CheckType> checkTypeQB = daoSession.queryBuilder(CheckType.class).where(
//                CheckTypeDao.Properties.Name.eq("高压二氧化碳系统灭火系统")
//        );
//        CheckType checkType3 = new CheckType();
//        String tableName3 = "功能性试验";
//        int type = 1;
//        checkType3.setName(tableName3);
//        checkType3.setType(type);
//        checkType3.setParent(checkTypeQB.list().get(0));
//        daoSession.insert(checkType3);
//
//        Log.i("info","插入checktype成功");

        // 插入yearCheck

//        // 获取typecheck
//        QueryBuilder<CheckType> checkTypeQB = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.Name.eq("药剂瓶检查表")
//                );
//        YearCheck yearCheck1 = new YearCheck();
//        String project1 = "瓶体外观检查";
//        String content1 = "所有储存容器应进行目视检查，看是否有破损、生锈或安装的硬件松动的迹象";
//        String requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好，涂层颜色为红色";
//        String standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
//        yearCheck1.setProject(project1);
//        yearCheck1.setContent(content1);
//        yearCheck1.setRequirement(requirement1);
//        yearCheck1.setStandard(standard1);
//        yearCheck1.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck1);
//        YearCheck yearCheck2 = new YearCheck();
//        String project2 = "药剂充装量检查";
//        String content2 = "检查CO2储瓶内药剂量是否符合要求";
//        String requirement2 = "CO2储瓶内药剂量损失不大于10%，每一个保护区整体药剂量损失不大于5%";
//        String standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.7";
//        yearCheck2.setProject(project2);
//        yearCheck2.setContent(content2);
//        yearCheck2.setRequirement(requirement2);
//        yearCheck2.setStandard(standard2);
//        yearCheck2.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck2);
//
//
//        checkTypeQB = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.Name.eq("氮气瓶检查表")
//                );
//        yearCheck1 = new YearCheck();
//        project1 = "瓶体外观检查";
//        content1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好";
//        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好，涂层颜色为红色";
//        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
//        yearCheck1.setProject(project1);
//        yearCheck1.setContent(content1);
//        yearCheck1.setRequirement(requirement1);
//        yearCheck1.setStandard(standard1);
//        yearCheck1.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck1);
//        yearCheck2 = new YearCheck();
//        project2 = "驱动瓶压力检查";
//        content2 = "检查驱动瓶压力是否符合要求";
//        requirement2 = "驱动瓶压力不低于设计压力90%";
//        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.6.2";
//        yearCheck2.setProject(project2);
//        yearCheck2.setContent(content2);
//        yearCheck2.setRequirement(requirement2);
//        yearCheck2.setStandard(standard2);
//        yearCheck2.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck2);
//
//        checkTypeQB = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.Name.eq("管线管件")
//                );
//        yearCheck1 = new YearCheck();
//        project1 = "外观检查";
//        content1 = "系统管路应进行目视检查，以核查是否有破损、支架松动和腐蚀，应检查喷嘴以确保其未因备件的储存或新建结构或新装机器而堵住";
//        requirement1 = "系统主要管线、管件、阀门、喷嘴状态良好";
//        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
//        yearCheck1.setProject(project1);
//        yearCheck1.setContent(content1);
//        yearCheck1.setRequirement(requirement1);
//        yearCheck1.setStandard(standard1);
//        yearCheck1.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck1);
//        yearCheck2 = new YearCheck();
//        project2 = "驱动管线密性检查和各接头紧固情况";
//        content2 = "进行驱动管线气密性试验";
//        requirement2 = "驱动管线及接头部位密封性良好，无泄漏";
//        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》5.4.6";
//        yearCheck2.setProject(project2);
//        yearCheck2.setContent(content2);
//        yearCheck2.setRequirement(requirement2);
//        yearCheck2.setStandard(standard2);
//        yearCheck2.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck2);
//        yearCheck2 = new YearCheck();
//        project2 = "管线吹通及集流管密性试验";
//        content2 = "检查管线畅通情况及集流管严密性,区域选择阀动作情况";
//        requirement2 = "管线畅通，集流管法兰无泄漏，区域选择阀可以顺利打开";
//        standard2 = "《海上固定平台安全规则》20.2.2.10";
//        yearCheck2.setProject(project2);
//        yearCheck2.setContent(content2);
//        yearCheck2.setRequirement(requirement2);
//        yearCheck2.setStandard(standard2);
//        yearCheck2.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck2);
//
//        checkTypeQB = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.Name.eq("保护区")
//                );
//        yearCheck1 = new YearCheck();
//        project1 = "保护处所变更检查";
//        content1 = "受保护处所的边界应进行目视检查，确认未对围壁进行产生不可关闭\t开口的改装并从而使系统失效";
//        requirement1 = "保护处所不应更改，如有更改，应进行变更";
//        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
//        yearCheck1.setProject(project1);
//        yearCheck1.setContent(content1);
//        yearCheck1.setRequirement(requirement1);
//        yearCheck1.setStandard(standard1);
//        yearCheck1.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck1);
//
//        checkTypeQB = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.Name.eq("功能性试验")
//                );
//        yearCheck1 = new YearCheck();
//        project1 = "模拟启动灭火系统";
//        content1 = "电磁阀动作情况";
//        requirement1 = "电磁阀动作";
//        standard1 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
//        yearCheck1.setProject(project1);
//        yearCheck1.setContent(content1);
//        yearCheck1.setRequirement(requirement1);
//        yearCheck1.setStandard(standard1);
//        yearCheck1.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck1);
//        yearCheck2 = new YearCheck();
//        project2 = "模拟启动灭火系统";
//        content2 = "声光报警情况";
//        requirement2 = "声光报警动作";
//        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
//        yearCheck2.setProject(project2);
//        yearCheck2.setContent(content2);
//        yearCheck2.setRequirement(requirement2);
//        yearCheck2.setStandard(standard2);
//        yearCheck2.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck2);
//        yearCheck2 = new YearCheck();
//        project2 = "模拟启动灭火系统";
//        content2 = "相关设备联动动作情况";
//        requirement2 = "相关设备联动动作";
//        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
//        yearCheck2.setProject(project2);
//        yearCheck2.setContent(content2);
//        yearCheck2.setRequirement(requirement2);
//        yearCheck2.setStandard(standard2);
//        yearCheck2.setCheckType(checkTypeQB.list().get(0));
//        daoSession.insert(yearCheck2);
//
//        Log.i("info","插入yearcheck成功");

        // 插入ItemInfo 需要companyinfo和checktype两种数据

        QueryBuilder<CompanyInfo> companyInfoQB = daoSession.queryBuilder(CompanyInfo.class).
                where(
                        CompanyInfoDao.Properties.CompanyName.eq("辽东作业公司"),
                        CompanyInfoDao.Properties.OilfieldName.eq("SZ36-1"),
                        CompanyInfoDao.Properties.PlatformName.eq("SZ36-1B")
                );
        QueryBuilder<CheckType> checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("药剂瓶")
                );

        ItemInfo itemInfo = new ItemInfo();

        String no = "YJP0001";
        String volume = "9";
        String weight = "3";
        String goodsWeight = "8";
        String prodFactory = "红日药业";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date prodDate = null;
        try {
            prodDate = format.parse("2018-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date observeDate = null;
        try {
            observeDate = format.parse("2019-03-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date checkDate = null;
        try {
            checkDate = format.parse("2020-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String isPass = "是";
        String labelNo = "BQ0001";
        String systemNumber = "SD001";
        String protectArea = "主配电间";
        String codePath = "/src/YJP0001.jpg";
        itemInfo.setNo(no);
        itemInfo.setVolume(volume);
        itemInfo.setWeight(weight);
        itemInfo.setGoodsWeight(goodsWeight);
        itemInfo.setProdFactory(prodFactory);
        itemInfo.setProdDate(prodDate);
        itemInfo.setObserveDate(observeDate);
        itemInfo.setCheckDate(checkDate);
        itemInfo.setIsPass(isPass);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setSystemNumber(systemNumber);
        itemInfo.setProtectArea(protectArea);
        itemInfo.setCodePath(codePath);
        itemInfo.setCheckType(checkTypeQB.list().get(0));
        itemInfo.setCompanyInfo(companyInfoQB.list().get(0));
        daoSession.insert(itemInfo);


        companyInfoQB = daoSession.queryBuilder(CompanyInfo.class).
                where(
                        CompanyInfoDao.Properties.CompanyName.eq("辽东作业公司"),
                        CompanyInfoDao.Properties.OilfieldName.eq("SZ36-1"),
                        CompanyInfoDao.Properties.PlatformName.eq("SZ36-1B")
                );
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("药剂瓶")
                );

        itemInfo = new ItemInfo();
        no = "YJP0002";
        volume = "9";
        weight = "3";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        prodDate = null;
        try {
            prodDate = format.parse("2018-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format.parse("2020-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkDate = null;
        try {
            checkDate = format.parse("2020-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "否";
        labelNo = "BQ0002";
        systemNumber = "SD002";
        protectArea = "主配电间";
        codePath = "/src/YJP0002.jpg";
        itemInfo.setNo(no);
        itemInfo.setVolume(volume);
        itemInfo.setWeight(weight);
        itemInfo.setGoodsWeight(goodsWeight);
        itemInfo.setProdFactory(prodFactory);
        itemInfo.setProdDate(prodDate);
        itemInfo.setObserveDate(observeDate);
        itemInfo.setCheckDate(checkDate);
        itemInfo.setIsPass(isPass);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setSystemNumber(systemNumber);
        itemInfo.setProtectArea(protectArea);
        itemInfo.setCodePath(codePath);
        itemInfo.setCheckType(checkTypeQB.list().get(0));
        itemInfo.setCompanyInfo(companyInfoQB.list().get(0));
        daoSession.insert(itemInfo);

        companyInfoQB = daoSession.queryBuilder(CompanyInfo.class).
                where(
                        CompanyInfoDao.Properties.CompanyName.eq("辽东作业公司"),
                        CompanyInfoDao.Properties.OilfieldName.eq("SZ36-1"),
                        CompanyInfoDao.Properties.PlatformName.eq("SZ36-1B")
                );
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("氮气瓶")
                );

        itemInfo = new ItemInfo();
        no = "DQP0001";
        volume = "9";
        weight = "3";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        prodDate = null;
        try {
            prodDate = format.parse("2018-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format.parse("2020-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkDate = null;
        try {
            checkDate = format.parse("2020-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0001";
        systemNumber = "SD002";
        protectArea = "主配电间";
        codePath = "/src/DQP0001.jpg";
        itemInfo.setNo(no);
        itemInfo.setVolume(volume);
        itemInfo.setWeight(weight);
        itemInfo.setGoodsWeight(goodsWeight);
        itemInfo.setProdFactory(prodFactory);
        itemInfo.setProdDate(prodDate);
        itemInfo.setObserveDate(observeDate);
        itemInfo.setCheckDate(checkDate);
        itemInfo.setIsPass(isPass);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setSystemNumber(systemNumber);
        itemInfo.setProtectArea(protectArea);
        itemInfo.setCodePath(codePath);
        itemInfo.setCheckType(checkTypeQB.list().get(0));
        itemInfo.setCompanyInfo(companyInfoQB.list().get(0));
        daoSession.insert(itemInfo);

        companyInfoQB = daoSession.queryBuilder(CompanyInfo.class).
                where(
                        CompanyInfoDao.Properties.CompanyName.eq("辽东作业公司"),
                        CompanyInfoDao.Properties.OilfieldName.eq("SZ36-1"),
                        CompanyInfoDao.Properties.PlatformName.eq("SZ36-1B")
                );
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("氮气瓶")
                );

        itemInfo = new ItemInfo();
        no = "DQP0002";
        volume = "9";
        weight = "3";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        prodDate = null;
        try {
            prodDate = format.parse("2018-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format.parse("2020-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkDate = null;
        try {
            checkDate = format.parse("2020-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0002";
        systemNumber = "SD002";
        protectArea = "主配电间";
        codePath = "/src/DQP0002.jpg";
        itemInfo.setNo(no);
        itemInfo.setVolume(volume);
        itemInfo.setWeight(weight);
        itemInfo.setGoodsWeight(goodsWeight);
        itemInfo.setProdFactory(prodFactory);
        itemInfo.setProdDate(prodDate);
        itemInfo.setObserveDate(observeDate);
        itemInfo.setCheckDate(checkDate);
        itemInfo.setIsPass(isPass);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setSystemNumber(systemNumber);
        itemInfo.setProtectArea(protectArea);
        itemInfo.setCodePath(codePath);
        itemInfo.setCheckType(checkTypeQB.list().get(0));
        itemInfo.setCompanyInfo(companyInfoQB.list().get(0));
        daoSession.insert(itemInfo);


        Log.i("info","插入iteminfo成功");
















//        // 插入yearCheckResult
//
//        // 获取系统名对应的id
//        CheckType checkType3 = new CheckType();
////        String systemName = "二氧化碳灭火系统";
//        String tableName3 = "药剂瓶检查表";
//        int type3 = 1;
//        checkType3.setName(tableName);
//        checkType3.setType(type3);
//        checkType3.setParent(checkType2);
//        daoSession.insert(checkType3);
//        Log.i("info","插入checktype成功");
//
//
//        YearCheckResult yearCheckResult1 = new YearCheckResult();
//        String isPass1 = "是";
//        String imageUrl1 = "imgs/xxx";
//        String description1 = "xxxxxxxxxxxxxxxxxxx";
//        yearCheckResult1.setIsPass(isPass1);
//        yearCheckResult1.setImageUrl(imageUrl1);
//        yearCheckResult1.setDescription(description1);
//        yearCheckResult1.setYearCheck(yearCheck1);
//        yearCheckResult1.setItemInfo(itemInfo);
//        itemInfo.setCheckType(checkType3);
//        itemInfo.setCompanyInfo(companyInfo);
//        daoSession.insert(yearCheckResult1);
//        YearCheckResult yearCheckResult2 = new YearCheckResult();
//        String isPass2 = "是";
//        String imageUrl2 = "imgs/xxx";
//        String description2 = "xxxxxxxxxxxxxxxxxxx";
//        yearCheckResult2.setIsPass(isPass2);
//        yearCheckResult2.setImageUrl(imageUrl2);
//        yearCheckResult2.setDescription(description2);
//        yearCheckResult2.setYearCheck(yearCheck2);
//        yearCheckResult2.setItemInfo(itemInfo);
//        itemInfo.setCheckType(checkType3);
//        itemInfo.setCompanyInfo(companyInfo);
//        daoSession.insert(yearCheckResult2);
//        Log.i("info","插入yearcheckresult成功");

    }



}
