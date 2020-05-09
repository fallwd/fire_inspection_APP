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
        titlelist.add("1.1A222");
        titlelist.add("2.2A33");
        titlelist.add("3.3A");
        titlelist.add("4.4A44");
        titlelist.add("5.5A");
        titlelist.add("6.6A");
        titlelist.add("7.7rrA");
        titlelist.add("8.8A");
        titlelist.add("8.10A");
        titlelist.add("9.21B");


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
