package com.hr.fire.inspection.sqlHelpers;

import android.text.format.DateUtils;
import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.StandardType;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;
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
        // 插入公司数据
        String companyName = "辽东作业公司";
        String oilfieldName = "SZ36-1";
        CompanyInfo companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        List platformNameList = Arrays.asList(
                "SZ36-1A",
                "SZ36-1B",
                "SZ36-1C",
                "SZ36-1D",
                "SZ36-1E",
                "SZ36-1F",
                "SZ36-1G",
                "SZ36-1H",
                "SZ36-1J",
                "SZ36-1K",
                "SZ36-1L",
                "SZ36-1M",
                "SZ36-1N"
        );
        int isNecessary = 1;
        String platformName;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }

        companyName = "辽东作业公司";
        oilfieldName = "LD10-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "LD10-1A",
                "LD10-1C"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "LD4-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "LD4-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "LD5-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "LD5-2A",
                "LD5-2B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "LD27-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "LD27-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "LD32-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "LD32-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "JZ9-3";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "JZ9-3A",
                "JZ9-3B",
                "JZ9-3C",
                "JZ9-3E",
                "JZ9-3W",
                "JZ9-3WG"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "JZ20-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "JZ20-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "JZ21-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "JZ21-1A"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "JZ25-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "JZ25-1A",
                "JZ25-1AG"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "JZ25-1S";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "JZ25-1SA",
                "JZ25-1SAG",
                "JZ25-1SB",
                "JZ25-1SC",
                "JZ25-1SD",
                "JZ25-1SE",
                "JZ25-1SEG"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "辽东作业公司";
        oilfieldName = "JX1-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "JX1-1A",
                "JX1-1B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ28-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ28-1"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ28-2S";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ28-2SB",
                "BZ28-2SCEP",
                "BZ28-2SN"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ34-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ34-1A",
                "BZ34-1B",
                "BZ34-1D",
                "BZ34-1F",
                "BZ34-1NC",
                "BZ34-1NE"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ34-2/4";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ34-2/4",
                "BZ34-2/4A",
                "BZ34-2/4B",
                "BZ34-3",
                "BZ34-5"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ34-6/7";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ34-6/7A"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ29-4";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ29-4",
                "BZ29-4SC"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ35-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ35-2A",
                "BZ35-2B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "KL3-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "KL3-2A"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "KL10-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "KL10-1A",
                "KL10-1B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "KL10-4";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "KL10-4A"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤南作业公司";
        oilfieldName = "BZ34-9";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ34-9A",
                "BZ34-9B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "秦皇岛作业公司";
        oilfieldName = "QHD32-6";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "QHD32-6A",
                "QHD32-6B",
                "QHD32-6C",
                "QHD32-6D",
                "QHD32-6E",
                "QHD32-6F",
                "QHD32-6G",
                "QHD32-6H",
                "QHD32-6I",
                "QHD32-6J"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "秦皇岛作业公司";
        oilfieldName = "BZ25-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ25-1A"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "秦皇岛作业公司";
        oilfieldName = "BZ25-1S";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ25-1B",
                "BZ25-1C",
                "BZ25-1D",
                "BZ25-1E",
                "BZ25-1F",
                "BZ25-1M"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "秦皇岛作业公司";
        oilfieldName = "BZ19-4";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ19-4A",
                "BZ19-4B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "CB";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CB-A",
                "CB-B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "QK17-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "QK17-2",
                "QK17-2E"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "QK17-3";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "QK17-3"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "QK18-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "QK18-1"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "QK18-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "QK18-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "BZ26-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ26-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "BZ26-3";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ26-3A",
                "BZ26-3B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "BZ13-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ13-1"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "CFD18-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CFD18-1"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "CFD18-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CFD18-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "CFD2-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CFD2-1"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "NB35-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "NB35-2A",
                "NB35-2B"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "BZ3-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "BZ3-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "渤西作业公司";
        oilfieldName = "QHD33-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "QHD33-1"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "蓬勃作业公司";
        oilfieldName = "PL19-3";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "PL19-3A",
                "PL19-3B",
                "PL19-3C",
                "PL19-3D",
                "PL19-3E",
                "PL19-3G",
                "PL19-3K",
                "PL19-3M",
                "PL19-3V"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "蓬勃作业公司";
        oilfieldName = "PL19-9";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "PL19-9J"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "曹妃甸作业公司";
        oilfieldName = "CFD11-1";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CFD11-1"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "曹妃甸作业公司";
        oilfieldName = "CFD11-2";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CFD11-2"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "曹妃甸作业公司";
        oilfieldName = "CFD11-3/5";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CFD11-3/5"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }
        companyName = "曹妃甸作业公司";
        oilfieldName = "CFD11-6";
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        daoSession.insert(companyInfoBase);
        companyInfoBase = new CompanyInfo();
        companyInfoBase.setCompanyName(companyName);
        companyInfoBase.setOilfieldName(oilfieldName);
        daoSession.insert(companyInfoBase);
        platformNameList = Arrays.asList(
                "CFD11-6"
        );
        isNecessary = 1;
        for(int i = 0;i < platformNameList.size();i++){
            CompanyInfo companyInfo = new CompanyInfo();
            platformName = platformNameList.get(i).toString();
            companyInfo.setCompanyName(companyName);
            companyInfo.setOilfieldName(oilfieldName);
            companyInfo.setPlatformName(platformName);
            companyInfo.setIsNecessary(isNecessary);
            daoSession.insert(companyInfo);
        }

        Log.i("info","插入公司数据成功");


        // 插入checkType 系统名 高压二氧化碳灭火系统
        CheckType checkType = new CheckType();
        String systemName = "高压二氧化碳灭火系统";
        int type = 1;
        long parentId = 0;
        int level = 1;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        checkType.setLevel(level);
        daoSession.insert(checkType);
        // 设备名
        CheckType checkType2 = new CheckType();
        String tableName2 = "药剂瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        CheckType checkType3 = new CheckType();
        String tableName3 = "药剂瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 设备名
        checkType2 = new CheckType();
        tableName2 = "氮气瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "氮气瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);


        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "管线管件";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "保护区";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "功能性试验";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        //七氟丙烷灭火系统
        checkType = new CheckType();
        systemName = "七氟丙烷灭火系统";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "七氟丙烷钢瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "七氟丙烷钢瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 设备名
        checkType2 = new CheckType();
        tableName2 = "氮气驱动瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "氮气驱动瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "管线管件";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "保护区";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "功能性试验";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        // 灭火器
        checkType = new CheckType();
        systemName = "灭火器";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "灭火器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);

        // 火灾自动报警系统
        checkType = new CheckType();
        systemName = "火灾自动报警系统";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "感烟探测器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "感温探测器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "火焰探测器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "手动报警按钮";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "可燃气体探测器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "氢气探测器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "硫化氢探测器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "CO探测器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "火灾报警控制器";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);

        // 厨房设备灭火装置
        checkType = new CheckType();
        systemName = "厨房设备灭火装置";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "药剂瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "药剂瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 设备名
        checkType2 = new CheckType();
        tableName2 = "驱动瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "驱动瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "管线吹通";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "功能性试验";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        // 海水雨淋灭火系统
        checkType = new CheckType();
        systemName = "海水雨淋灭火系统";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);

        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "雨淋阀";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "关键控制阀门";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "功能性试验";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        // 消防水灭火系统
        checkType = new CheckType();
        systemName = "消防水灭火系统";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "消防软管";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "消防软管检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 设备名
        checkType2 = new CheckType();
        tableName2 = "消防炮";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "消防炮检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "其他部件";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "功能性试验";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        // 固定式干粉灭火系统
        checkType = new CheckType();
        systemName = "固定式干粉灭火系统";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "干粉罐";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "干粉罐检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 设备名
        checkType2 = new CheckType();
        tableName2 = "启动瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "启动瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "管线管件";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "功能性试验";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        // 泡沫灭火系统
        checkType = new CheckType();
        systemName = "泡沫灭火系统";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);

        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "泡沫发生装置";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "储罐撬块";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "管路及控制阀门";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "功能性试验";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        // 消防员装备
        checkType = new CheckType();
        systemName = "消防员装备";
        type = 1;
        parentId = 0;
        level = 1;
        checkType.setLevel(level);
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
        tableName2 = "SCBA气瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "SCBA气瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 设备名
        checkType2 = new CheckType();
        tableName2 = "EEBD气瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType2.setName(tableName2);
        checkType2.setType(type);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);
        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "EEBD气瓶检查表";
        type = 1;
        level = 3;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType2);
        daoSession.insert(checkType3);

        // 检查表名
        checkType3 = new CheckType();
        tableName3 = "手电";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        checkType3 = new CheckType();
        tableName3 = "防火服";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);
        checkType3 = new CheckType();
        tableName3 = "消防员战斗服";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);
        checkType3 = new CheckType();
        tableName3 = "备用气瓶";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);
        checkType3 = new CheckType();
        tableName3 = "消防靴";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);
        checkType3 = new CheckType();
        tableName3 = "头盔";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);
        checkType3 = new CheckType();
        tableName3 = "太平斧";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);
        checkType3 = new CheckType();
        tableName3 = "其他";
        type = 1;
        level = 2;
        checkType.setLevel(level);
        checkType3.setName(tableName3);
        checkType3.setType(type);
        checkType3.setParent(checkType);
        daoSession.insert(checkType3);

        Log.i("info","插入checktype成功");

        // 插入yearCheck

        // 获取typecheck id !
        //  高压二氧化碳灭火系统
        QueryBuilder<CheckType> AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("高压二氧化碳灭火系统"));
        long AId = AQB.list().get(0).getId();
        QueryBuilder<CheckType> BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("药剂瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        long PId = BQB.list().get(0).getId();
        QueryBuilder<CheckType> checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("药剂瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        YearCheck yearCheck1 = new YearCheck();
        String project1 = "瓶体外观检查";
        String content1 = "所有储存容器应进行目视检查，看是否有破损、生锈或安装的硬件松动的迹象";
        String requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好，涂层颜色为红色";
        String standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
//        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        YearCheck yearCheck2 = new YearCheck();
        String project2 = "药剂充装量检查";
        String content2 = "检查CO2储瓶内药剂量是否符合要求";
        String requirement2 = "CO2储瓶内药剂量损失不大于10%，每一个保护区整体药剂量损失不大于5%";
        String standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.7";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckTypeId(checkTypeQB.list().get(0).getId());;
        daoSession.insert(yearCheck2);


        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("氮气瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("氮气瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "瓶体外观检查";
        content1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好";
        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好，涂层颜色为红色";
        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "驱动瓶压力检查";
        content2 = "检查驱动瓶压力是否符合要求";
        requirement2 = "驱动瓶压力不低于设计压力90%";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.6.2";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("管线管件"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "系统管路应进行目视检查，以核查是否有破损、支架松动和腐蚀，应检查喷嘴以确保其未因备件的储存或新建结构或新装机器而堵住";
        requirement1 = "系统主要管线、管件、阀门、喷嘴状态良好";
        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "驱动管线密性检查和各接头紧固情况";
        content2 = "进行驱动管线气密性试验";
        requirement2 = "驱动管线及接头部位密封性良好，无泄漏";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》5.4.6";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "管线吹通及集流管密性试验";
        content2 = "检查管线畅通情况及集流管严密性,区域选择阀动作情况";
        requirement2 = "管线畅通，集流管法兰无泄漏，区域选择阀可以顺利打开";
        standard2 = "《海上固定平台安全规则》20.2.2.10";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("保护区"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "保护处所变更检查";
        content1 = "受保护处所的边界应进行目视检查，确认未对围壁进行产生不可关闭\t开口的改装并从而使系统失效";
        requirement1 = "保护处所不应更改，如有更改，应进行变更";
        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "保护处所变更检查";
        content1 = "受保护处所内所保护的物质是否发生变更";
        requirement1 = "保护处所内所保护的物质不应发生变更";
        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);


        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("功能性试验"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "模拟启动灭火系统";
        content1 = "电磁阀动作情况";
        requirement1 = "电磁阀动作";
        standard1 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "模拟启动灭火系统";
        content2 = "声光报警情况";
        requirement2 = "声光报警动作";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "模拟启动灭火系统";
        content2 = "相关设备联动动作情况";
        requirement2 = "相关设备联动动作";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        // 七氟丙烷灭火系统
        AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("七氟丙烷灭火系统"));
        AId = AQB.list().get(0).getId();
        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("七氟丙烷钢瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("七氟丙烷钢瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "瓶体外观检查";
        content1 = "检查FM200储瓶有无碰撞变形及其他机械性损伤，表面有无锈蚀，保护涂层是否完好，检查涂层的颜色";
        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好，涂层颜色为红色";
        standard1 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.6.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
//        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "储瓶压力检查";
        content2 = "检查储瓶内压力是否符合要求";
        requirement2 = "压力不低于设计压力90%";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.7";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckTypeId(checkTypeQB.list().get(0).getId());;
        daoSession.insert(yearCheck2);


        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("氮气驱动瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("氮气驱动瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "瓶体外观检查";
        content1 = "检查驱动瓶有无碰撞变形及其他机械性损伤，表面有无锈蚀，保护涂层是否完好";
        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好";
        standard1 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.6.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "驱动瓶压力检查";
        content2 = "检查驱动瓶压力是否符合要求";
        requirement2 = "驱动瓶压力不低于设计压力90%";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.6.2";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("管线管件"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "系统主要管线、管件、阀门状态是否良好";
        requirement1 = "系统主要管线、管件、阀门状态良好";
        standard1 = "GB50263-2007《气体灭火系统施工及验收规范》4.2.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "驱动管线密性检查和各接头紧固情况";
        content2 = "进行驱动管线气密性试验";
        requirement2 = "驱动管线及接头部位密封性良好，无泄漏";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》5.4.6";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "管线吹通及集流管密性试验";
        content2 = "检查管线畅通情况及集流管严密性,区域选择阀动作情况";
        requirement2 = "管线畅通，集流管法兰无泄漏，区域选择阀可以顺利打开";
        standard2 = "《海上固定平台安全规则》20.2.2.10";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("保护区"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "保护处所变更检查";
        content1 = "受保护处所的边界应进行目视检查，确认未对围壁进行产生不可关闭开口的改装并从而使系统失效";
        requirement1 = "保护处所不应更改，如有更改，应进行变更";
        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "保护处所变更检查";
        content1 = "受保护处所内所保护的物质是否发生变更";
        requirement1 = "保护处所内所保护的物质不应发生变更";
        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("功能性试验"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "模拟启动灭火系统";
        content1 = "电磁阀动作情况";
        requirement1 = "电磁阀动作";
        standard1 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "模拟启动灭火系统";
        content2 = "声光报警情况";
        requirement2 = "声光报警动作";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "模拟启动灭火系统";
        content2 = "相关设备联动动作情况";
        requirement2 = "相关设备联动动作";
        standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.8";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        // 厨房设备灭火装置
        AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("厨房设备灭火装置"));
        AId = AQB.list().get(0).getId();
        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("药剂瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("药剂瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "瓶体外观检查";
        content1 = "检查储瓶有无碰撞变形及其他机械性损伤，表面有无锈蚀，保护涂层是否完好";
        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好";
        standard1 = "CECS233-2007《厨房设备灭火装置技术规程》6.2.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "药剂量检查";
        content2 = "检查储瓶内药剂量是否符合要求";
        requirement2 = "储瓶内药剂量符合生产厂家规定";
        standard2 = "产品使用说明书";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckTypeId(checkTypeQB.list().get(0).getId());;
        daoSession.insert(yearCheck2);


        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("驱动瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("驱动瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "瓶体外观检查";
        content1 = "检查驱动瓶有无碰撞变形及其他机械性损伤，表面有无锈蚀，保护涂层是否完好";
        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好";
        standard1 = "CECS233-2007《厨房设备灭火装置技术规程》6.2.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "驱动瓶压力检查";
        content2 = "检查驱动瓶压力是否符合要求";
        requirement2 = "驱动气体贮存容器内的压力，不得小于设计贮存压力的90%";
        standard2 = "CECS233-2007《厨房设备灭火装置技术规程》6.2.1";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("管线吹通"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "喷头清理";
        content1 = "检查喷头是否清洁，是否有油污";
        requirement1 = "喷头清洁无油污";
        standard1 = "产品使用说明书";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "管线吹通";
        content2 = "进行管线吹通，检查管线是否堵塞";
        requirement2 = "管线畅通，无堵塞";
        standard2 = "CECS233-2007《厨房设备灭火装置技术规程》6.2.2";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("功能性试验"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "模拟启动厨房湿粉灭火系统";
        content1 = "驱动装置动作情况，相关设备联动动作情况";
        requirement1 = "驱动装置动作，相关设备联动动作";
        standard1 = "CECS233-2007《厨房设备灭火装置技术规程》附录B";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        // 海水雨淋灭火系统
        AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("海水雨淋灭火系统"));
        AId = AQB.list().get(0).getId();

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("雨淋阀"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "检查雨淋阀外观，有无锈蚀";
        requirement1 = "检查雨淋阀的外观，有无锈蚀；";
        standard1 = "GB50261-2017《自动喷水灭火系统施工及验收规范》9.0.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "组件检查";
        content2 = "检查雨淋阀的各主要组件是否完整，有无缺失，有无渗漏";
        requirement2 = "检查雨淋阀的功能组件是否完整，有无缺失，有无渗漏；";
        standard2 = "GB50261-2017《自动喷水灭火系统施工及验收规范》9.0.6";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("关键控制阀门"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "检查阀门的外观，检查其锈蚀情况，有无破损";
        requirement1 = "检查阀门的外观，检查其锈蚀情况，有无破损；";
        standard1 = "GB50261-2017《自动喷水灭火系统施工及验收规范》9.0.10";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "动作检查";
        content1 = "检查阀门现场状态及开关灵活情况";
        requirement1 = "阀门现场状态符合要求，开关灵活无卡滞";
        standard1 = "GB50261-2017《自动喷水灭火系统施工及验收规范》9.0.10";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("功能性试验"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "雨淋阀启动试验";
        content1 = "试验雨淋阀启动功能，包括现场启动和远程启动两种方式";
        requirement1 = "雨淋阀能正常启动和复位";
        standard1 = "GB50261-2017《自动喷水灭火系统施工及验收规范》9.0.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "现场喷淋试验";
        content1 = "选取一处雨淋阀保护区域进行喷淋试验";
        requirement1 = "现场正常喷淋，喷嘴无堵塞";
        standard1 = "《中国船级社消防系统检测机构服务指南》4.6.5.16";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "消防泵启动试验";
        content1 = "试验消防泵手动、自动能否正常启动，检查启动前后消防管网压力";
        requirement1 = "消防泵正常启动，消防泵启动前后出口压力符合设计要求";
        standard1 = "中国船级社《船用消防系统检测机构服务指南》4.12.4.14.3";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        // 消防水灭火系统
        AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("消防水灭火系统"));
        AId = AQB.list().get(0).getId();
        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("消防软管"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("消防软管检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "检查橡胶软管、水龙带的外观，有无裂纹、划伤、破损、老化，各组件有无锈蚀和破损";
        requirement1 = "橡胶软管、水龙带无裂纹、划伤、破损、老化，各组件无锈蚀和破损";
        standard1 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);

        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("消防炮"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("消防炮检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "检查消防炮及组件的外观，检查其锈蚀情况，有无破损";
        requirement1 = "消防炮及组件完好、可用";
        standard1 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("其他部件"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "管网";
        content1 = "检查系统管网外观有无锈蚀";
        requirement1 = "检查系统管网外观无锈蚀";
        standard1 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "国际通岸接头";
        content2 = "检查国际通岸接头配备及技术状态";
        requirement2 = "按规定进行配备国际通岸接头及其附件，外观良好";
        standard2 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "隔离阀";
        content2 = "检查隔离阀的关闭功能";
        requirement2 = "隔离阀能够关闭";
        standard2 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "减压阀";
        content2 = "确认所有消防泵减压阀设置正确";
        requirement2 = "减压阀按照规定设置压力";
        standard2 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "过滤器";
        content2 = "检查过滤器是否好用";
        requirement2 = "确认他们没有被杂物阻塞和污染";
        standard2 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("功能性试验"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "喷水试验";
        content1 = "检查软管或水龙带、消防炮的喷射压力、流量、直流/开花功能";
        requirement1 = "软管或水龙带的喷水压力、流量及耐压性符合要求，直流/开花功能正常";
        standard1 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "消防泵启动试验";
        content1 = "试验消防泵手动、自动能否正常启动，检查启动前后消防管网压力";
        requirement1 = "消防泵正常启动，消防泵启动前后出口压力符合设计要求";
        standard1 = "《中国船级社消防系统检测机构服务指南》4.6.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        // 固定式干粉灭火系统
        AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("固定式干粉灭火系统"));
        AId = AQB.list().get(0).getId();
        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("干粉罐"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("干粉罐检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "罐体外观检查";
        content1 = "检查罐体有无碰撞变形及其他机械性损伤，表面有无锈蚀，保护涂层是否完好，检查涂层的颜色";
        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好，涂层颜色为红色";
        standard1 = "";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "干粉检查";
        content1 = "检查干粉存储状态";
        requirement1 = "干粉储存良好，无结块";
        standard1 = "";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);

        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("启动瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("启动瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "瓶体外观检查";
        content1 = "检查驱启动瓶有无碰撞变形及其他机械性损伤，表面有无锈蚀，保护涂层是否完好";
        requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好";
        standard1 = "CECS322-2010《干粉灭火装置技术规程》6.0.4";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "启动瓶压力检查";
        content1 = "检查启动瓶压力是否符合要求";
        requirement1 = "启动瓶压力不低于设计压力90%";
        standard1 = "CECS322-2010《干粉灭火装置技术规程》6.0.4";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "启动气体";
        content1 = "确定按照系统制造商的说明用氮气搅拌干粉";
        requirement1 = "应用氮气作为启动气体";
        standard1 = "《中国船级社消防系统检测机构服务指南》4.12.4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("管线管件"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "系统主要管线、管件、阀门、喷嘴状态是否良好";
        requirement1 = "系统主要管线、管件、阀门、喷嘴状态良好";
        standard1 = "CECS322-2010《干粉灭火装置技术规程》6.0.4";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "管线吹通";
        content2 = "检查管线畅通情况";
        requirement2 = "管线畅通";
        standard2 = "《中国船级社消防系统检测机构服务指南》4.12.4.13.2";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("功能性试验"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "模拟启动灭火系统";
        content1 = "电磁阀动作情况";
        requirement1 = "电磁阀动作";
        standard1 = "CECS322-2010《干粉灭火装置技术规程》6.0.4";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "模拟启动灭火系统";
        content1 = "相关设备联动动作情况";
        requirement1 = "相关设备联动动作";
        standard1 = "CECS322-2010《干粉灭火装置技术规程》6.0.4";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        // 泡沫灭火系统
        AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("泡沫灭火系统"));
        AId = AQB.list().get(0).getId();

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("泡沫发生装置"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "检查泡沫炮、泡沫枪、喷头的外观";
        requirement1 = "泡沫炮、泡沫枪、喷头的外观良好";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "动作检查";
        content2 = "检查泡沫枪直流/开花功能，泡沫炮回转俯仰、直流/开花功能";
        requirement2 = "泡沫枪直流/开花功能正常，泡沫炮回转俯仰、直流/开花功能正常";
        standard2 = "《FPSO安全规则》21.2.4.6";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("储罐撬块"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "检查泡沫罐的外观有无锈蚀、裂纹、变形";
        requirement1 = "泡沫罐的外观无锈蚀、裂纹、变形";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "组件检查";
        content1 = "罐体组件有无缺失，检查设备固定是否牢固，连接是否紧固";
        requirement1 = "罐体组件无缺失，设备固定牢固，连接紧固；";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "标识检查";
        content1 = "检查罐体上是否设施清晰的标识";
        requirement1 = "罐体上设置清晰的标识";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "泡沫液检查";
        content1 = "检查泡沫浓缩液的容量、有效期限、检验日期；检查泡沫液贮存环境是否合适";
        requirement1 = "泡沫浓缩液的容量、有效期限、检验日期符合设计要求，泡沫液贮存环境符合泡沫液使用环境";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("管路及控制阀门"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "外观检查";
        content1 = "检查系统管网外观有无锈蚀";
        requirement1 = "系统管网外观无锈蚀";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "阀门检查";
        content1 = "检查阀门启闭状态是否与设计符合，阀门是否正常开启/关闭";
        requirement1 = "阀门启闭状态符合设计要求，阀门可正常开启/关闭";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("功能性试验"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "消防泵启动试验";
        content1 = "试验消防泵手动、自动能否正常启动，检查启动前后消防管网压力";
        requirement1 = "消防泵正常启动，消防泵启动前后出口压力符合设计要求";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "泡沫液泵启动试验";
        content1 = "试验泡沫液泵手动、自动能否正常启动";
        requirement1 = "泡沫液泵能正常启动";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "选择阀动作试验";
        content1 = "试验泡沫灭火系统区域选择阀动作是否灵活，保护区域是否正确";
        requirement1 = "选择阀能正常动作，并且动作灵活，现场选择阀保护区域与设计要求一致";
        standard1 = "《FPSO安全规则》21.2.4.6";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        // 消防员装备
        AQB = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq("消防员装备"));
        AId = AQB.list().get(0).getId();
        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("SCBA气瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("SCBA气瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查全面罩的透明窗、环状密封、系带、供气阀是否完好，检查供气阀与全面罩的连接是否牢固";
        requirement1 = "全面罩的透明窗、环状密封、系带、供气阀完好，供气阀与全面罩的连接牢固";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查供气阀的动作是否灵活，与低压导管（俗称中压导管）的连接是否牢固";
        requirement1 = "供气阀的动作灵活，与低压导管（俗称中压导管）的连接牢固";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查气源压力表是否正常指示压力";
        requirement1 = "气源压力表正常指示压力";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查背托是否完好无损，左右肩带、左右腰带缝合线是否断裂";
        requirement1 = "背托完好无损，左右肩带、左右腰带缝合线未断裂";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查气瓶的固定带是否牢固，气瓶与减压阀的连接是否牢固";
        requirement1 = "气瓶的固定带牢固，气瓶与减压阀的连接牢固";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查气瓶内的压力";
        requirement1 = "气瓶内的压力不低于气瓶规定的工作压力";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查整套呼吸器的气密性";
        requirement1 = "打开瓶头阀2分钟后关闭瓶头阀，观察压力表的示值，观察压力表在1分钟的时间，其压力下降不超过0.2Mpa";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "SCBA";
        content1 = "检查全面罩和供气阀的匹配情况";
        requirement1 = "关闭供气阀，打开瓶头阀开关，佩戴好全面罩吸气，供气阀应自动开启；当呼气或屏住呼吸时不应听到供气的“咝咝”声音，如果有“咝咝”声音说明呼气阀有故障，不能使用";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckTypeId(checkTypeQB.list().get(0).getId());
        daoSession.insert(yearCheck1);

        BQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("EEBD气瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        PId = BQB.list().get(0).getId();
        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("EEBD气瓶检查表"),
                        CheckTypeDao.Properties.ParentId.eq(PId)
                );
        yearCheck1 = new YearCheck();
        project1 = "EEBD";
        content1 = "检查头罩上进气分散器与输气管接头的联接是否牢固、可靠";
        requirement1 = "头罩上进气分散器与输气管接头的联接牢固、可靠";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "EEBD";
        content1 = "检查头罩是否干燥";
        requirement1 = "保持整个头罩的清洁、干燥";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "EEBD";
        content1 = "检查输气管与快速接头连接是否牢固";
        requirement1 = "应保持不松动，不脱落";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "EEBD";
        content1 = "检查减压器与气瓶的瓶头阀连接是否牢固";
        requirement1 = "减压器与气瓶的瓶头阀连接牢固";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "EEBD";
        content1 = "检查气瓶内气源压力";
        requirement1 = "打开气瓶瓶头阀观察压力表，其瓶内压力不低于气瓶规定的工作压力（一般工作压力在：19.6—21Mpa）";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "EEBD";
        content1 = "脱开头罩，检查气瓶压力是否维持不变";
        requirement1 = "气瓶压力维持不变";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "EEBD";
        content1 = "检查挎包是否完好无损，肩带缝合线是否断裂，尼龙搭扣牢固可用";
        requirement1 = "挎包完好无损，肩带缝合线无断裂，尼龙搭扣牢固可用";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("手电"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "手电";
        content1 = "检查手电是否有防爆标志";
        requirement1 = "手电应有防爆产品证书或者防爆标志";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck2 = new YearCheck();
        project2 = "手电";
        content2 = "检查手电电池是否超出有效期";
        requirement2 = "电池使用年限不应超过有效期限";
        standard2 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);
        yearCheck2 = new YearCheck();
        project2 = "手电";
        content2 = "检查手电是否在可用状态";
        requirement2 = "手电是否有电";
        standard2 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck2.setProject(project2);
        yearCheck2.setContent(content2);
        yearCheck2.setRequirement(requirement2);
        yearCheck2.setStandard(standard2);
        yearCheck2.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck2);


        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("防火服"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "防火服";
        content1 = "检查外观是否良好，防护服、手套、靴套、头套的外表完整性，没有破损等缺陷";
        requirement1 = "外观良好，无破损";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "防火服";
        content1 = "检查是否在有效期范围内";
        requirement1 = "使用年限应在厂家规定有效期限内";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("消防员战斗服"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "消防员战斗服";
        content1 = "检查外观是否良好";
        requirement1 = "外观良好，无破损";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "消防员战斗服";
        content1 = "检查是否在有效期范围内";
        requirement1 = "使用年限应在厂家规定有效期限内";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("备用气瓶"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "备用气瓶";
        content1 = "检查压力是否符合要求";
        requirement1 = "气瓶内的压力不低于气瓶规定的工作压力";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "备用气瓶";
        content1 = "对气瓶进行目测，检查其外表是否存在凹坑、裂纹、划伤、腐蚀、磕伤、鼓包等缺陷，如果有裂纹、鼓包缺陷应予以报废";
        requirement1 = "外观良好，无缺陷";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("消防靴"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "消防靴";
        content1 = "检查消防靴是否完好，橡胶是否老化和破损等缺陷";
        requirement1 = "外观良好，无破损";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "消防靴";
        content1 = "检查消防靴的有效期";
        requirement1 = "使用年限应在厂家规定有效期限内";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("头盔"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "头盔";
        content1 = "检查头盔是否坚固完整";
        requirement1 = "外观良好，无破损";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "头盔";
        content1 = "核对头盔的有效期，一般头盔有效期为3年";
        requirement1 = "使用年限应在厂家规定有效期限内";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("太平斧"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "太平斧";
        content1 = "手柄具有耐高电压绝缘，检查其是否完好，是否有破损等缺陷";
        requirement1 = "外观良好，无破损";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq("其他"),
                        CheckTypeDao.Properties.ParentId.eq(AId)
                );
        yearCheck1 = new YearCheck();
        project1 = "其他";
        content1 = "检查其他消防员装备配备数量是否符合设计要求";
        requirement1 = "配备数量符合设计要求";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);
        yearCheck1 = new YearCheck();
        project1 = "其他";
        content1 = "检查消防员装备外观是否良好";
        requirement1 = "外观良好，无破损";
        standard1 = "《船用消防系统检测机构服务指南》4.13.1";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkTypeQB.list().get(0));
        daoSession.insert(yearCheck1);

        Log.i("info","插入yearcheck成功");

        // 插入巡检相关数据
        // 巡检系统
        checkType = new CheckType();
        systemName = "灭火器";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "灭火器";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "气体灭火系统";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
        checkType2 = new CheckType();
//        tableName2 = "气体灭火系统";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "防火风闸";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "防火风闸";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "雨淋阀";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "海水雨淋灭火系统";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "消防软管站";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "消防软管站";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "消防水龙带";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "消防水龙带";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "火气探头及火灾盘";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "火气探头检查表";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);
//        checkType2 = new CheckType();
//        tableName2 = "火气监控系统检查表";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);


        checkType = new CheckType();
        systemName = "火气监控系统";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);

        checkType = new CheckType();
        systemName = "厨房湿粉灭火系统";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "厨房湿粉灭火系统系统检查表";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "泡沫灭火系统";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "泡沫灭火系统检查表";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "消防泵";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "消防泵检查表";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "消防员装备箱";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "消防员装备箱";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        checkType = new CheckType();
        systemName = "消防水炮";
        type = 2;
        parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 设备名
//        checkType2 = new CheckType();
//        tableName2 = "消防水炮";
//        type = 2;
//        checkType2.setName(tableName2);
//        checkType2.setType(type);
//        checkType2.setParent(checkType);
//        daoSession.insert(checkType2);

        // 法律法规分类
        StandardType standardType = new StandardType();
        String standardName = "行业标准";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "国家法律";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "国际标准";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "行政规范";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "行业规章";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "国家标注";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "良好作业实践及指导性文件";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "监督性机构规范性文件";
        standardType.setName(standardName);
        daoSession.insert(standardType);

        standardType = new StandardType();
        standardName = "其他";
        standardType.setName(standardName);
        daoSession.insert(standardType);



    }
    public void insertTestData() {

        // 插入设备数据
        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
        long companyInfoId = 3;
        // checkTypeId 对应 药剂瓶 2
        long checkTypeId = 2;
        // number 区号 SD002
        String number = "SD001";
        // checkDate 检查日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date checkDate = null;
        try {
            checkDate = format.parse("2019-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ItemInfo itemObj = new ItemInfo();
        String no = "YJP0001";
        String volume = "9";
        String weight = "3";
        String goodsWeight = "8";
        String prodFactory = "红日药业";
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
        Date prodDate = null;
        try {
            prodDate = format2.parse("2018-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date observeDate = null;
        try {
            observeDate = format2.parse("2019-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String isPass = "是";
        String labelNo = "BQ0001";
//        String systemNumber = "SD001";
        String protectArea = "主配电间";
        String codePath = "/src/YJP0001.jpg";
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
        companyInfoId = 3;
        // checkTypeId 对应 药剂瓶 2
        checkTypeId = 2;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        itemObj = new ItemInfo();
        no = "YJP0001";
        volume = "9";
        weight = "3";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format2 = new SimpleDateFormat("yyyy-MM");
        prodDate = null;
        try {
            prodDate = format2.parse("2018-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format2.parse("2019-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0001";
//        systemNumber = "SD001";
        protectArea = "主配电间";
        codePath = "/src/YJP0002.jpg";
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
        companyInfoId = 3;
        // checkTypeId 对应 氮气瓶 4
        checkTypeId = 4;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        itemObj = new ItemInfo();
        no = "DQP0001";
        volume = "9";
        weight = "3";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format2 = new SimpleDateFormat("yyyy-MM");
        prodDate = null;
        try {
            prodDate = format2.parse("2018-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format2.parse("2019-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0003";
//        systemNumber = "SD001";
        protectArea = "主配电间";
        codePath = "/src/DQP0002.jpg";
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
        companyInfoId = 3;
        // checkTypeId 对应 氮气瓶 4
        checkTypeId = 4;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        itemObj = new ItemInfo();
        no = "DQP0005";
        volume = "9";
        weight = "3";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format2 = new SimpleDateFormat("yyyy-MM");
        prodDate = null;
        try {
            prodDate = format2.parse("2018-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format2.parse("2019-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0005";
//        systemNumber = "SD001";
        protectArea = "主配电间";
        codePath = "/src/DQP0002.jpg";
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 3;
        // checkTypeId 对应 氮气瓶 4
        checkTypeId = 4;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-6-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        itemObj = new ItemInfo();
        no = "DQP0007";
        volume = "9";
        weight = "10";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format2 = new SimpleDateFormat("yyyy-MM");
        prodDate = null;
        try {
            prodDate = format2.parse("2018-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format2.parse("2019-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0005";
//        systemNumber = "SD001";
        protectArea = "主配电间";
        codePath = "/src/DQP0002.jpg";
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 3;
        // checkTypeId 对应 氮气瓶 4
        checkTypeId = 4;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        itemObj = new ItemInfo();
        no = "DQP0005";
        volume = "9";
        weight = "10";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format2 = new SimpleDateFormat("yyyy-MM");
        prodDate = null;
        try {
            prodDate = format2.parse("2018-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format2.parse("2019-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0005";
//        systemNumber = "SD001";
        protectArea = "主配电间";
        codePath = "/src/DQP0002.jpg";
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        ///////////////////////////////////////////////////////
        // companyInfoId CompanyInfo{id=165, companyName='渤西作业公司', oilfieldName='BZ13-1', platformName='BZ13-1', isNecessary=1}
        companyInfoId = 165;
        // checkTypeId 对应 药剂瓶 2
        checkTypeId = 2;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-10-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        itemObj = new ItemInfo();
        no = "DQP0005";
        volume = "9";
        weight = "3";
        goodsWeight = "8";
        prodFactory = "红日药业";
        format2 = new SimpleDateFormat("yyyy-MM");
        prodDate = null;
        try {
            prodDate = format2.parse("2018-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        observeDate = null;
        try {
            observeDate = format2.parse("2019-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isPass = "是";
        labelNo = "BQ0005";
//        systemNumber = "SD001";
        protectArea = "主配电间1";
        codePath = "/src/DQP0002.jpg";
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(number);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 药剂瓶 2
        checkTypeId = 2;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(number);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 药剂瓶 2
        checkTypeId = 4;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 药剂瓶 2
        checkTypeId = 4;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2020-10-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 药剂瓶 2
        checkTypeId = 4;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-10-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo(no);
        itemObj.setVolume(volume);
        itemObj.setWeight(weight);
        itemObj.setGoodsWeight(goodsWeight);
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setTaskNumber("1,2,3,7");
//        itemObj.setSystemNumber(systemNumber);
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

///////////////////////////////////////////////////////////////////////////////////////////
        companyInfoId = 165;
        // checkTypeId 对应 七氟丙烷钢瓶 10
        checkTypeId = 10;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("1235623");
        itemObj.setVolume("10");

        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");

        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 七氟丙烷钢瓶 10
        checkTypeId = 10;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("1523");
        itemObj.setVolume("10");

        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");

        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 氮气驱动瓶 12
        checkTypeId = 12;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("1523");
        itemObj.setVolume("10");

        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");

        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 氮气驱动瓶 12
        checkTypeId = 12;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("1523");
        itemObj.setVolume("10");

        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");

        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 氮气驱动瓶 12
        checkTypeId = 12;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("1523");
        itemObj.setVolume("10");

        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");

        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 灭火器 18
        checkTypeId = 18;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("MFZ/ABC1");
        itemObj.setAgentsType("P");
        itemObj.setLevel("1A,21B");
        itemObj.setVolume("10");
        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setTypeConformity("是");
        itemObj.setPositionConformity("是");
        itemObj.setAppearance("是");
        itemObj.setIsPressure("否");
        itemObj.setEffectiveness("否");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 灭火器 18
        checkTypeId = 18;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("MFZ/ABC1");
        itemObj.setAgentsType("P");
        itemObj.setLevel("1A,21B");
        itemObj.setVolume("10");
        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setTypeConformity("是");
        itemObj.setPositionConformity("是");
        itemObj.setAppearance("是");
        itemObj.setIsPressure("否");
        itemObj.setEffectiveness("否");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 灭火器 18
        checkTypeId = 18;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("MFZ/ABC1");
        itemObj.setAgentsType("P");
        itemObj.setLevel("1A,21B");
        itemObj.setVolume("10");
        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setTypeConformity("是");
        itemObj.setPositionConformity("是");
        itemObj.setAppearance("是");
        itemObj.setIsPressure("否");
        itemObj.setEffectiveness("否");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 灭火器 18
        checkTypeId = 18;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("MFZ/ABC1");
        itemObj.setAgentsType("P");
        itemObj.setLevel("1A,21B");
        itemObj.setVolume("10");
        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setTypeConformity("是");
        itemObj.setPositionConformity("是");
        itemObj.setAppearance("是");
        itemObj.setIsPressure("否");
        itemObj.setEffectiveness("否");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 灭火器 18
        checkTypeId = 18;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("MFZ/ABC1");
        itemObj.setAgentsType("P");
        itemObj.setLevel("1A,21B");
        itemObj.setVolume("10");
        itemObj.setGoodsWeight("9");
        itemObj.setPressure("100");
        itemObj.setProdFactory(prodFactory);
        itemObj.setProdDate(prodDate);
        itemObj.setObserveDate(observeDate);
        itemObj.setTypeConformity("是");
        itemObj.setPositionConformity("是");
        itemObj.setAppearance("是");
        itemObj.setIsPressure("否");
        itemObj.setEffectiveness("否");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 感烟探测器 20
        checkTypeId = 20;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 感烟探测器 20
        checkTypeId = 20;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 感烟探测器 20
        checkTypeId = 20;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 感烟探测器 20
        checkTypeId = 20;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 感温探测器 21
        checkTypeId = 21;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 感温探测器 21
        checkTypeId = 21;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 感温探测器 21
        checkTypeId = 21;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 火焰探测器 22
        checkTypeId = 22;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 火焰探测器 22
        checkTypeId = 22;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 手动报警按钮 23
        checkTypeId = 23;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 手动报警按钮 23
        checkTypeId = 23;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");


        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 可燃气体探测器 24
        checkTypeId = 24;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 可燃气体探测器 24
        checkTypeId = 24;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 氢气探测器 25
        checkTypeId = 25;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 氢气探测器 25
        checkTypeId = 25;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);


        companyInfoId = 165;
        // checkTypeId 对应 硫化氢探测器 26
        checkTypeId = 26;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 硫化氢探测器 26
        checkTypeId = 26;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 CO探测器 27
        checkTypeId = 27;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 CO探测器 27
        checkTypeId = 27;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 CO探测器 27
        checkTypeId = 27;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setAppearance("是");
        itemObj.setResponseTime("5");
        itemObj.setSetAlarm25("25");
        itemObj.setSetAlarm50("50");
        itemObj.setTestAlarm25("26");
        itemObj.setTestAlarm50("51");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 火灾报警控制器 28
        checkTypeId = 28;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setPositionConformity("BBBBB");
        itemObj.setAppearance("是");
        itemObj.setCheck("是");
        itemObj.setSlience("是");
        itemObj.setReset("是");
        itemObj.setPowerAlarmFunction("正常");
        itemObj.setAlarmFunction("是");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 火灾报警控制器 28
        checkTypeId = 28;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setDeviceType("ZZZ");
        itemObj.setTypeNo("O");
        itemObj.setNo("O");
        itemObj.setPositionConformity("BBBBB");
        itemObj.setAppearance("是");
        itemObj.setCheck("是");
        itemObj.setSlience("是");
        itemObj.setReset("是");
        itemObj.setPowerAlarmFunction("正常");
        itemObj.setAlarmFunction("是");


        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);


        companyInfoId = 165;
        // checkTypeId 对应 厨房药剂瓶 30
        checkTypeId = 30;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setAgentsType("111ase");
        itemObj.setNo("O");
        itemObj.setVolume("10");
        itemObj.setWeight("5");
        itemObj.setGoodsWeight("7");
        itemObj.setFillingDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 厨房药剂瓶 30
        checkTypeId = 30;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setAgentsType("111ase");
        itemObj.setNo("O");
        itemObj.setVolume("10");
        itemObj.setWeight("5");
        itemObj.setGoodsWeight("7");
        itemObj.setFillingDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 厨房驱动瓶 332
        checkTypeId = 32;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("O");
        itemObj.setPressure("10");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 厨房驱动瓶 332
        checkTypeId = 32;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03 09:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("O");
        itemObj.setPressure("10");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 消防软管 41
        checkTypeId = 41;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("AA");
        itemObj.setNo("11");
        itemObj.setDeviceType("masd");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 消防软管 41
        checkTypeId = 41;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("AA");
        itemObj.setNo("11");
        itemObj.setDeviceType("masd");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 消防炮 43
        checkTypeId = 43;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("AA");
        itemObj.setNo("11");
        itemObj.setDeviceType("masd");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);


        companyInfoId = 165;
        // checkTypeId 对应 消防炮 43
        checkTypeId = 43;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("AA");
        itemObj.setNo("11");
        itemObj.setDeviceType("masd");

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
//        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);


        companyInfoId = 165;
        // checkTypeId 对应 干粉罐 48
        checkTypeId = 48;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("AA");
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setGoodsWeight("11");
        itemObj.setFillingDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 干粉罐 48
        checkTypeId = 48;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setTypeNo("AA");
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setGoodsWeight("11");
        itemObj.setFillingDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 启动瓶 50
        checkTypeId = 50;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setPressure("20");
        itemObj.setObserveDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 启动瓶 50
        checkTypeId = 50;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setPressure("20");
        itemObj.setObserveDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 SCBA气瓶 60
        checkTypeId = 60;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setPressure("20");
        itemObj.setObserveDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 SCBA气瓶 60
        checkTypeId = 60;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setPressure("20");
        itemObj.setObserveDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 EEBD气瓶 62
        checkTypeId = 62;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setPressure("20");
        itemObj.setObserveDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 EEBD气瓶 62
        checkTypeId = 62;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setPressure("20");
        itemObj.setObserveDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        companyInfoId = 165;
        // checkTypeId 对应 EEBD气瓶 62
        checkTypeId = 62;
        // number 区号 SD002
        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemObj = new ItemInfo();
        itemObj.setNo("11");
        itemObj.setVolume("50");
        itemObj.setWeight("12");
        itemObj.setPressure("20");
        itemObj.setObserveDate(prodDate);

        itemObj.setCheckDate(checkDate);
        itemObj.setIsPass(isPass);
        itemObj.setLabelNo(labelNo);
        itemObj.setSystemNumber(number);
        itemObj.setTaskNumber("1,2,3,7");
        itemObj.setProtectArea(protectArea);
        itemObj.setCodePath(codePath);
        ServiceFactory.getYearCheckService().insertItemDataEasy(itemObj,companyInfoId,checkTypeId,number,checkDate,protectArea);

        ///// 海水雨淋灭火系统
        companyInfoId = 165;
        // checkTypeId 37 38 39
        // yearCheckId

        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long itemId = 0;
        YearCheckResult retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间as");
        retObj.setSystemNumber(number);
        checkTypeId = 37;
        long yearCheckId = 30;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 37;
        yearCheckId = 31;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电a间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 38;
        yearCheckId = 32;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 38;
        yearCheckId = 33;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 34;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 35;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 36;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        // 泡沫灭火系统检查 55 56 57 58
        checkTypeId = 55;
        yearCheckId = 55;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 55;
        yearCheckId = 56;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 57;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 58;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 59;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 60;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 57;
        yearCheckId = 61;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 57;
        yearCheckId = 62;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 63;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 64;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 65;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");


        ///// 海水雨淋灭火系统
        companyInfoId = 3;
        // checkTypeId 37 38 39
        // yearCheckId

        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2020-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemId = 0;
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间as");
        retObj.setSystemNumber(number);
        checkTypeId = 37;
        yearCheckId = 30;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 37;
        yearCheckId = 31;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电a间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 38;
        yearCheckId = 32;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 38;
        yearCheckId = 33;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 34;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 35;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 36;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        // 泡沫灭火系统检查 55 56 57 58
        checkTypeId = 55;
        yearCheckId = 55;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 55;
        yearCheckId = 56;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 57;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 58;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 59;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 60;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 57;
        yearCheckId = 61;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 57;
        yearCheckId = 62;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 63;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 64;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 65;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");

        ///// 海水雨淋灭火系统
        companyInfoId = 5;
        // checkTypeId 37 38 39
        // yearCheckId

        number = "SD002";
        // checkDate 检查日期
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-07-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemId = 0;
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间as");
        retObj.setSystemNumber(number);
        checkTypeId = 37;
        yearCheckId = 30;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 37;
        yearCheckId = 31;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电a间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 38;
        yearCheckId = 32;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 38;
        yearCheckId = 33;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 34;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 35;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 39;
        yearCheckId = 36;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        // 泡沫灭火系统检查 55 56 57 58
        checkTypeId = 55;
        yearCheckId = 55;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 55;
        yearCheckId = 56;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 57;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 58;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 59;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 56;
        yearCheckId = 60;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 57;
        yearCheckId = 61;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 57;
        yearCheckId = 62;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("是");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 63;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 64;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");
        retObj = new YearCheckResult();
        retObj.setIsPass("否");
        retObj.setDescription("xxxxxx");
        retObj.setImageUrl("xxxx/xxx/xx");
        retObj.setProtectArea("配电间asa");
        retObj.setSystemNumber(number);
        checkTypeId = 58;
        yearCheckId = 65;
        ServiceFactory.getYearCheckService().insertCheckResultDataEasy(retObj,itemId,yearCheckId,companyInfoId,checkTypeId,number,checkDate,"");

        /////////////////插入巡检测试数据///////////////////////
        //灭火器
        String checkPerson = "张三";
        String profession = "安全";
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        companyInfoId = 165;
        // 灭火器
        long systemId = 72;
        InspectionResult insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("否");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("否");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        Log.i("tang","插入测试数据成功");

        //灭火器
        checkPerson = "张三";
        profession = "安全";
        format = new SimpleDateFormat("yyyy-MM-dd");
        checkDate = null;
        try {
            checkDate = format.parse("2019-08-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        companyInfoId = 3;
        // 灭火器
        systemId = 72;
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("否");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("否");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("否");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        Log.i("tang","插入测试数据成功");

        //灭火器
        checkPerson = "张三";
        profession = "安全";
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        checkDate = null;
        try {
            checkDate = format.parse("2019-08-03 10:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        companyInfoId = 5;
        // 灭火器
        systemId = 72;
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("否");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("是");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("否");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        insObj = new InspectionResult();
        insObj.setCheckPerson(checkPerson);
        insObj.setProfession(profession);
        insObj.setDescription("xxxxxxxxxxxxx");
        insObj.setImgPath("aa/asd/asd");
        insObj.setParam1("MFZ/ABC2");
        insObj.setParam2("dd");
        insObj.setParam3("2");
        insObj.setParam4("是");
        insObj.setParam5("否");
        insObj.setParam6("是");
        insObj.setParam7("是");
        insObj.setParam8("是");
        insObj.setParam9("是");
        ServiceFactory.getInspectionService().insertInspectionData(insObj, companyInfoId, systemId, checkDate);
        Log.i("tang","插入测试数据成功");

    }

}