package com.hr.fire.inspection.entity;

import java.util.ArrayList;
import java.util.List;

public class WorkIItemBean {
    private List<WorkIItemBean> workItemList = new ArrayList();
    private List<String> titlelist = new ArrayList();

    private String title;
    private boolean state = false;


    public List<WorkIItemBean> getWorkSelectData() {
        List<String> list = initData();
        for (int i = 0; i < list.size(); i++) {
            workItemList.add(new WorkIItemBean(list.get(i), false));
        }
        return workItemList;
    }

    private List<String> initData() {
        titlelist.clear();
        titlelist.add("1.面罩检查");
        titlelist.add("2.呼吸阀检查");
        titlelist.add("3.调节装置检查");
        titlelist.add("4.背带检查");
        titlelist.add("5.功能试验");
        titlelist.add("6.气瓶检查");
        titlelist.add("7.气瓶充气");
        titlelist.add("8.气瓶水压试验");
        titlelist.add("8.气瓶水压试验");
        titlelist.add("9.检验日期标签");
        titlelist.add("10.新气瓶");
        return titlelist;
    }

    public WorkIItemBean() {
    }

    public WorkIItemBean(String s, boolean b) {
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
