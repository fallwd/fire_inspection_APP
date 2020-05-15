package com.hr.fire.inspection.entity;

import java.util.ArrayList;
import java.util.List;

public class NJMhqSelectItem2 {
    private List<NJMhqSelectItem2> workItemList = new ArrayList();
    private List<String> titlelist = new ArrayList();

    private String title;
    private boolean state = false;


    public List<NJMhqSelectItem2> getWorkSelectData() {
        List<String> list = initData();
        for (int i = 0; i < list.size(); i++) {
            workItemList.add(new NJMhqSelectItem2(list.get(i), false));
        }
        return workItemList;
    }

    private List<String> initData() {
        titlelist.clear();
        titlelist.add("1.灭火器外观检查");
        titlelist.add("2.重量检查");
        titlelist.add("3.充装");
        titlelist.add("4.检验日期标签");
        titlelist.add("5.压力检查");
        titlelist.add("6.维修");
        titlelist.add("7.水压试验");
        titlelist.add("8.新灭火器");


        return titlelist;
    }

    public NJMhqSelectItem2() {
    }

    public NJMhqSelectItem2(String s, boolean b) {
        this.title = s;
        this.state = b;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "WorkIItemBean{" +
                "workItemList=" + workItemList +
                ", title='" + title + '\'' +
                ", state=" + state +
                '}';
    }
}
