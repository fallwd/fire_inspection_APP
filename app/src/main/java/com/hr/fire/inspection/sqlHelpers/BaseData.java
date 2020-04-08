package com.hr.fire.inspection.sqlHelpers;

import android.text.format.DateUtils;

import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.utils.GreenDaoHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseData {
    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    public void initData() throws ParseException {
        // 插入公司数据
        CompanyInfo companyInfo = new CompanyInfo();
        String companyName = "辽东作业公司";
        String oilfieldName = "SZ36-1";
        String platformName = "SZ36-1A";
        int isNecessary = 1;
        companyInfo.setCompanyName(companyName);
        companyInfo.setOilfieldName(oilfieldName);
        companyInfo.setPlatformName(platformName);
        companyInfo.setIsNecessary(isNecessary);
        daoSession.insert(companyInfo);

        // 插入checkType
        CheckType checkType = new CheckType();
        String systemName = "二氧化碳灭火系统";
//        String tableName = "药剂瓶";
        int type = 1;
        long parentId = 0;
        checkType.setName(systemName);
        checkType.setType(type);
        checkType.setParentId(parentId);
        daoSession.insert(checkType);
        // 获取系统名对应的id
        CheckType checkType2 = new CheckType();
//        String systemName = "二氧化碳灭火系统";
        String tableName = "药剂瓶";
        int type2 = 1;
        checkType2.setName(tableName);
        checkType2.setType(type2);
        checkType2.setParent(checkType);
        daoSession.insert(checkType2);

        // 插入ItemInfo

        ItemInfo itemInfo = new ItemInfo();
        String no = "YJP0001";
        String volume = "5";
        String weight = "10";
        String goodsWeight = "8";
        String prodFactory = "xxx公司";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date prodDate = format.parse("2018-09-10");
        Date observeDate = format.parse("2020-03-07");
        String isPass = "是";
        String labelNo = "YJP0001";
        itemInfo.setNo(no);
        itemInfo.setVolume(volume);
        itemInfo.setWeight(weight);
        itemInfo.setGoodsWeight(goodsWeight);
        itemInfo.setProdFactory(prodFactory);
        itemInfo.setProdDate(prodDate);
        itemInfo.setObserveDate(observeDate);
        itemInfo.setIsPass(isPass);
        itemInfo.setLabelNo(labelNo);
        itemInfo.setCheckType(checkType2);
        itemInfo.setCompanyInfo(companyInfo);
        daoSession.insert(itemInfo);

        // 插入yearCheck
        YearCheck yearCheck1 = new YearCheck();
        String project1 = "瓶体外观检查";
        String content1 = "所有储存容器应进行目视检查，看是否有破损、生锈或安装的硬件松动的迹象";
        String requirement1 = "储瓶无碰撞变形及其他机械性损伤，表面无锈蚀，保护涂层完好，涂层颜色为红色";
        String standard1 = "中国船级社《船用消防系统检测机构服务指南》4.10.2";
        yearCheck1.setProject(project1);
        yearCheck1.setContent(content1);
        yearCheck1.setRequirement(requirement1);
        yearCheck1.setStandard(standard1);
        yearCheck1.setCheckType(checkType2);
        daoSession.insert(yearCheck1);
        YearCheck yearCheck2 = new YearCheck();
        String project2 = "药剂充装量检查";
        String content2 = "检查CO2储瓶内药剂量是否符合要求";
        String requirement2 = "CO2储瓶内药剂量损失不大于10%，每一个保护区整体药剂量损失不大于5%";
        String standard2 = "GB50263-2007《气体灭火系统施工及验收规范》8.0.7";
        yearCheck1.setProject(project2);
        yearCheck1.setContent(content2);
        yearCheck1.setRequirement(requirement2);
        yearCheck1.setStandard(standard2);
        yearCheck1.setCheckType(checkType2);
        daoSession.insert(yearCheck2);

        // 插入yearCheckResult
        YearCheckResult yearCheckResult1 = new YearCheckResult();
        String isPass1 = "是";
        String imageUrl1 = "imgs/xxx";
        String description1 = "xxxxxxxxxxxxxxxxxxx";
        yearCheckResult1.setIsPass(isPass1);
        yearCheckResult1.setImageUrl(imageUrl1);
        yearCheckResult1.setDescription(description1);
        yearCheckResult1.setYearCheck(yearCheck1);
        yearCheckResult1.setItemInfo(itemInfo);
        daoSession.insert(yearCheckResult1);
        YearCheckResult yearCheckResult2 = new YearCheckResult();
        String isPass2 = "是";
        String imageUrl2 = "imgs/xxx";
        String description2 = "xxxxxxxxxxxxxxxxxxx";
        yearCheckResult2.setIsPass(isPass2);
        yearCheckResult2.setImageUrl(imageUrl2);
        yearCheckResult2.setDescription(description2);
        yearCheckResult2.setYearCheck(yearCheck2);
        yearCheckResult2.setItemInfo(itemInfo);
        daoSession.insert(yearCheckResult2);

    }





}
