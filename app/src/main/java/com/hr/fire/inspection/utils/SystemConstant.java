package com.hr.fire.inspection.utils;

import com.hr.fire.inspection.fragment.AutomaticFireAlarm2;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm5;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm6;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm7;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm8;
import com.hr.fire.inspection.fragment.AutomaticFireAlarm9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemConstant {
    static String carbon1Title = "药剂瓶";
    static String [][] carbon1 = {
            {"瓶号","容积/L","瓶重/kg","药剂量/kg","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},
            {"no","volume","weight", "goodsWeight", "prodFactory", "prodDate", "observeDate", "taskNumber", "isPass", "labelNo"}
    };

    static String carbon2Title = "氮气瓶";
    String [][] carbon2 = {
            {"瓶号","容积/L","瓶重/kg","压力/MPa","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},
            {"no","volume","weight", "pressure", "prodFactory", "prodDate", "observeDate", "taskNumber", "isPass", "labelNo"}
    };

    static String carbon3Title = "管线管件";
    String [][] yearCheckData = {
            {"检查项目","检查内容","合格要求","检查标准","是否合格","现场照片/录像","隐患描述"},
            {"yearCheck.project","yearCheck.content","yearCheck.requirement", "yearCheck.standard", "isPass", "imageUrl", "description"}
    };
    static String reserve = "保护区";
    static String functionalTest = "功能性试验";

    static String HFC1Title = "七氟丙烷钢瓶";
    String [][] HFC1 = {
            {"瓶号","容积/L","瓶重/kg","压力/MPa","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},
            {"no","volume","weight", "pressure", "prodFactory", "prodDate", "observeDate", "taskNumber", "isPass", "labelNo"}
    };


    static String HFC2Title = "氮气驱动瓶";
    String [][] HFC2 = {
            {"瓶号","容积/L","瓶重/kg","压力/MPa","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},
            {"no","volume","weight", "pressure", "prodFactory", "prodDate", "observeDate", "taskNumber", "isPass", "labelNo"}
    };

    static String MHQTitle = "灭火器";
    String [][] MHQ = {
            {"型号","灭火剂类型","灭火级别","工作代号","生产厂家","生产日期","型号符合性","位置符合性","外观是否良好","压力/重量是否合格","药剂有效性","维修日期","检测结果","检验标签","照片", "隐患描述"},
            {"typeNo","deviceType","level", "taskNumber", "prodFactory", "prodDate", "typeConformity", "positionConformity",
                    "appearance", "isPressure", "effectiveness", "observeDate", "isPass", "labelNo", "imageUrl", "description"}
    };


    static String AutomaticFireAlarm1Title = "感烟探测器";
    static String AutomaticFireAlarm2Title = "感温探测器";
    static String AutomaticFireAlarm3Title = "火焰探测器";
    static String AutomaticFireAlarm4Title = "手动报警按钮";
    static String AutomaticFireAlarm5Title = "可燃气体探测器";
    static String AutomaticFireAlarm6Title = "氢气探测器";
    static String AutomaticFireAlarm7Title = "硫化氢探测器";
    static String AutomaticFireAlarm8Title = "CO探测器";
    static String AutomaticFireAlarm9Title = "火灾报警控制器";
    String [][] AutomaticFireAlarm1 = {
            {"设备类型","生产厂家","型号","外观是否良好","响应时间/s","是否合格","照片","隐患描述"},
            {"deviceType","prodFactory","no", "appearance", "responseTime", "isPass", "imageUrl", "description"}
    };
    String [][] AutomaticFireAlarm2 = {
            {"设备类型","生产厂家","型号","编号", "外观是否良好","设定高报值/25%LEL","设定高高报值/50%LEL","测试高报值/25%LEL","测试高高报值/50%LEL","响应时间","是否合格","照片","隐患描述"},
            {"deviceType","prodFactory","no","typeNo", "appearance", "setAlarm25","setAlarm50","testAlarm25","testAlarm50","responseTime", "isPass", "imageUrl", "description"}
    };
    String [][] AutomaticFireAlarm3 = {
            {"设备类型","生产厂家","型号","编号", "外观是否良好","设定高报值/25ppm","设定高高报值/50ppm","测试高报值25ppm","测试高高报值/50ppm","响应时间","是否合格","照片","隐患描述"},
            {"deviceType","prodFactory","no","typeNo", "appearance", "setAlarm25","setAlarm50","testAlarm25","testAlarm50","responseTime", "isPass", "imageUrl", "description"}
    };
    String [][] AutomaticFireAlarm4 = {
            {"设备位号","设备类型","生产厂家","型号","位置", "外观是否良好","自检功能是否良好","消音功能是否良好","复位功能是否良好","主/备电源连线故障报警功能","报警功能是否正常","照片","隐患描述"},
            {"no","deviceType","prodFactory","typeNo","positionConformity", "appearance", "check","slience","reset","powerAlarmFunction","alarmFunction", "imageUrl", "description"}
    };


    static String NjKitchen1Title = "药剂瓶";
    static String NjKitchen2Title = "驱动瓶";
    static String NjKitchen3Title = "管线吹通";
    static String [][] NjKitchen1 = {
            {"介质类型", "瓶号","容积/L","瓶重/kg","药剂量/kg","生产厂家","生产日期","灌装日期","工作代号","是否合格","检验标签"},
            {"agentsType","no","volume","weight", "goodsWeight", "prodFactory", "prodDate", "fillingDate", "taskNumber", "isPass", "labelNo"}
    };
    String [][] NjKitchen2 = {
            {"瓶号","容积/L","压力/MPa","生产厂家","生产日期","工作代号","是否合格","检验标签"},
            {"no","volume", "pressure", "prodFactory", "prodDate", "taskNumber", "isPass", "labelNo"}
    };


    static String SeawaterSystem1Title = "雨淋阀";
    static String SeawaterSystem2Title = "关键控制阀门";


    static String NjFireFightingWater1Title = "消防软管";
    static String NjFireFightingWater2Title = "消防炮";
    static String NjFireFightingWater3Title = "其他部件";
    String [][] NjFireFightingWater1 = {
            {"类型","编号/位置","生产厂家","规格型号","生产日期","工作代号","是否合格"},
            {"typeNo","no", "prodFactory", "deviceType", "prodDate", "taskNumber", "isPass"}
    };
    String [][] NjFireFightingWater2 = {
            {"类型","编号/位置","生产厂家","规格型号","生产日期","是否合格"},
            {"typeNo","no", "prodFactory", "deviceType", "prodDate", "isPass"}
    };

    static String DryPowderFireSystem1Title = "干粉罐";
    static String DryPowderFireSystem2Title = "启动瓶";
    static String DryPowderFireSystem3Title = "管线管件";

    static String [][] DryPowderFireSystem1 = {
            {"型号", "瓶号","容积/L","瓶重/kg","药剂量/kg","生产厂家","生产日期","灌装日期","工作代号","是否合格","检验标签"},
            {"typeNo","no","volume","weight", "goodsWeight", "prodFactory", "prodDate", "fillingDate", "taskNumber", "isPass", "labelNo"}
    };
    static String [][] DryPowderFireSystem2 = {
            {"瓶号","容积/L","瓶重/kg","压力/MPa","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},
            {"no","volume","weight", "pressure", "prodFactory", "prodDate", "observeDate", "taskNumber", "isPass", "labelNo"}
    };


    static String FoamFire1Title = "泡沫发生装置";
    static String FoamFire2Title = "储罐撬块";
    static String FoamFire3Title = "管路及控制阀门";



    static String DFXI1Title = "SCBA气瓶";
    static String DFXI2Title = "EEBD气瓶";
    static String DFXI3Title = "手电";
    static String DFXI4Title = "防护服";
    static String DFXI5Title = "消防员战斗服";
    static String DFXI6Title = "备用气瓶";
    static String DFXI7Title = "消防靴";
    static String DFXI8Title = "头盔";
    static String DFXI9Title = "太平斧";
    static String DFXI10Title = "其他";
    static String [][] DFXI1 = {
            {"瓶号","容积/L","瓶重/kg","压力/MPa","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},
            {"no","volume","weight", "pressure", "prodFactory", "prodDate", "observeDate", "taskNumber", "isPass", "labelNo"}
    };



    private SystemConstant() {
        columns = new HashMap<>();
        titles = new HashMap<>();
        systemCheckResultIndex = new HashMap<>();

        genColumnsMap();
        genTitlesMap();
        genSystemCheckResultIndex();
    }

    private static SystemConstant instance = new SystemConstant();


    public static SystemConstant getInstance() {
        return instance;
    }


    static Map<Long,String[][]> columns;
    static Map<Long,String> titles;
    static Map<Long,Integer> systemCheckResultIndex; // xx系统-> checkResult index


    public void genColumnsMap() {
        columns.put(2L,carbon1); // 二氧化碳
        columns.put(4L,carbon2);
        columns.put(6L,yearCheckData);
        columns.put(7L,yearCheckData);
        columns.put(8L,yearCheckData);

        columns.put(10L,HFC1); // 七氟丙烷
        columns.put(12L,HFC2);
        columns.put(14L,yearCheckData);
        columns.put(15L,yearCheckData);
        columns.put(16L,yearCheckData);

        columns.put(18L,MHQ); // 灭火器

        columns.put(20L,AutomaticFireAlarm1); // 火灾自动报警系统
        columns.put(21L,AutomaticFireAlarm1); // 火灾自动报警系统
        columns.put(22L,AutomaticFireAlarm1); // 火灾自动报警系统
        columns.put(23L,AutomaticFireAlarm1); // 火灾自动报警系统
        columns.put(24L,AutomaticFireAlarm2); // 火灾自动报警系统
        columns.put(25L,AutomaticFireAlarm2); // 火灾自动报警系统
        columns.put(26L,AutomaticFireAlarm3); // 火灾自动报警系统
        columns.put(27L,AutomaticFireAlarm3); // 火灾自动报警系统
        columns.put(28L,AutomaticFireAlarm4); // 火灾自动报警系统

        columns.put(30L,NjKitchen1); // 厨房设备系统
        columns.put(32L,NjKitchen2); // 厨房设备系统
        columns.put(34L,yearCheckData); // 厨房设备系统
        columns.put(35L,yearCheckData); // 厨房设备系统

        columns.put(37L,yearCheckData); // 海水雨林
        columns.put(38L,yearCheckData); // 海水雨林
        columns.put(39L,yearCheckData); // 海水雨林

        columns.put(41L,NjFireFightingWater1); // 消防水灭火系统
        columns.put(43L,NjFireFightingWater2); // 消防水灭火系统
        columns.put(45L,yearCheckData); //消防水灭火系统
        columns.put(46L,yearCheckData); //消防水灭火系统

        columns.put(48L,DryPowderFireSystem1); // 固定式干粉灭火系统
        columns.put(50L,DryPowderFireSystem2); // 固定式干粉灭火系统
        columns.put(52L,yearCheckData); //固定式干粉灭火系统
        columns.put(53L,yearCheckData); //固定式干粉灭火系统

        columns.put(55L,yearCheckData); // 泡沫灭火系统
        columns.put(56L,yearCheckData); // 泡沫灭火系统
        columns.put(57L,yearCheckData); // 泡沫灭火系统
        columns.put(58L,yearCheckData); // 泡沫灭火系统

        columns.put(60L,DFXI1); // 消防员装备
        columns.put(62L,DFXI1); // 消防员装备
        columns.put(64L,yearCheckData); //消防员装备
        columns.put(65L,yearCheckData); //消防员装备
        columns.put(66L,yearCheckData); //消防员装备
        columns.put(67L,yearCheckData); //消防员装备
        columns.put(68L,yearCheckData); //消防员装备
        columns.put(69L,yearCheckData); //消防员装备
        columns.put(70L,yearCheckData); //消防员装备
        columns.put(71L,yearCheckData); //消防员装备
    }

    public void genTitlesMap() {
        titles.put(2L,carbon1Title); // 二氧化碳药剂瓶
        titles.put(4L,carbon2Title);
        titles.put(6L,carbon3Title);
        titles.put(7L,reserve); // 保护区
        titles.put(8L,functionalTest); // 功能性试验

        titles.put(10L,HFC1Title); // 七氟丙烷
        titles.put(12L,HFC2Title);
        titles.put(14L,carbon3Title);
        titles.put(15L,reserve); // 保护区
        titles.put(16L,functionalTest); // 功能性试验

        titles.put(18L,MHQTitle); // 灭火器

        titles.put(20L,AutomaticFireAlarm1Title); // 灭火器
        titles.put(21L,AutomaticFireAlarm2Title); // 灭火器
        titles.put(22L,AutomaticFireAlarm3Title); // 灭火器
        titles.put(23L,AutomaticFireAlarm4Title); // 灭火器
        titles.put(24L, AutomaticFireAlarm5Title); // 灭火器
        titles.put(25L, AutomaticFireAlarm6Title); // 灭火器
        titles.put(26L, AutomaticFireAlarm7Title); // 灭火器
        titles.put(27L, AutomaticFireAlarm8Title); // 灭火器
        titles.put(28L, AutomaticFireAlarm9Title); // 灭火器

        titles.put(30L,NjKitchen1Title); // 厨房设备系统
        titles.put(32L,NjKitchen2Title); // 厨房设备系统
        titles.put(34L,NjKitchen3Title); // 厨房设备系统
        titles.put(35L,functionalTest); // 功能性试验

        titles.put(37L,SeawaterSystem1Title); // 海水雨林
        titles.put(38L,SeawaterSystem2Title); // 海水雨林
        titles.put(39L,functionalTest); // 功能性试验

        titles.put(41L,NjFireFightingWater1Title); // 消防水灭火系统
        titles.put(43L,NjFireFightingWater2Title); // 消防水灭火系统
        titles.put(45L,NjFireFightingWater3Title); // 消防水灭火系统
        titles.put(46L,functionalTest); // 消防水灭火系统

        titles.put(48L,DryPowderFireSystem1Title); // 固定式干粉灭火系统
        titles.put(50L,DryPowderFireSystem2Title); // 固定式干粉灭火系统
        titles.put(52L,DryPowderFireSystem3Title); // 固定式干粉灭火系统
        titles.put(53L,functionalTest); // 固定式干粉灭火系统

        titles.put(55L,FoamFire1Title); // 泡沫灭火系统
        titles.put(56L,FoamFire2Title); // 泡沫灭火系统
        titles.put(57L,FoamFire3Title); // 泡沫灭火系统
        titles.put(58L,functionalTest); // 泡沫灭火系统

        titles.put(60L,DFXI1Title); // 消防员装备
        titles.put(62L,DFXI2Title); // 消防员装备
        titles.put(64L,DFXI3Title); // 消防员装备
        titles.put(65L,DFXI4Title); // 消防员装备
        titles.put(66L,DFXI5Title); // 消防员装备
        titles.put(67L,DFXI6Title); // 消防员装备
        titles.put(68L,DFXI7Title); // 消防员装备
        titles.put(69L,DFXI8Title); // 消防员装备
        titles.put(70L,DFXI9Title); // 消防员装备
        titles.put(71L,DFXI10Title); // 消防员装备


    }

    public void genSystemCheckResultIndex() {
        systemCheckResultIndex.put(1L,2); //二氧化碳系统对应的检查结果索引
        systemCheckResultIndex.put(9L,2); //七氟丙烷系统对应的检查结果索引
        systemCheckResultIndex.put(17L,2); //七氟丙烷系统对应的检查结果索引
        systemCheckResultIndex.put(19L,10); //火灾自动报警系统对应的检查结果索引
        systemCheckResultIndex.put(29L,2); //厨房设备系统对应的检查结果索引
        systemCheckResultIndex.put(36L,0); //海水雨林对应的检查结果索引
        systemCheckResultIndex.put(40L,2); //消防水灭火系统对应的检查结果索引
        systemCheckResultIndex.put(47L,2); //固定式干粉灭火系统对应的检查结果索引
        systemCheckResultIndex.put(54L,0); //泡沫灭火系统对应的检查结果索引
        systemCheckResultIndex.put(59L,2); //消防员装备系统对应的检查结果索引
    }


    public static int getCheckReusltIndexBySystemId(long id) {
        return systemCheckResultIndex.get(id);
    }

    public static Map<String,Object> getSystem(long id) {

        Map<String,Object> ret = new HashMap<>();
        ret.put("columns",columns.get(id));
        ret.put("title",titles.get(id));

        return ret;
    }
}
