package com.hr.fire.inspection.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemConstant {

    String [][] carbon1 = {
                            {"序号","瓶号","容积/L","瓶重/kg","药剂量/kg","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},

                            {"No","Volume","Weight","GoodsWeight","ProdFactory","ProdDate","ObserveDate","TaskNumber","IsPass","LabelNo",}

                    };

    String carbon1Title = "药剂瓶";


    String [][] carbon2 = {
            {"序号","瓶号","容积/L","瓶重/kg","药剂量/kg","生产厂家","生产日期","水压试验日期","工作代号","是否合格","检验标签"},

            {"No","Volume","Weight","GoodsWeight","ProdFactory","ProdDate","ObserveDate","TaskNumber","IsPass","LabelNo",}

    };


    private SystemConstant() {
        genColumnsMap();
        genTitlesMap();
    }

    private static SystemConstant instance = new SystemConstant();


    public static SystemConstant getInstance() {
        return instance;
    }


    static Map<Long,String[][]> columns = new HashMap<>();
    static Map<Long,String> titles = new HashMap<>();


    public void genColumnsMap() {
        columns.put(2L,carbon1);
    }

    public void genTitlesMap() {
        titles.put(2L,carbon1Title);
    }



    public static Map<String,Object> getSystem(long id) {

        Map<String,Object> ret = new HashMap<>();
        ret.put("columns",columns.get(id));
        ret.put("title",titles.get(id));

        return ret;
    }
}
