package com.hr.fire.inspection.entity;

import java.util.ArrayList;
import java.util.List;

public class NJMhqSelectItem1 {
    private List<NJMhqSelectItem1> workItemList = new ArrayList();
    private List<String> titlelist = new ArrayList();

    private String title;
    private boolean state = false;


    public List<NJMhqSelectItem1> getWorkSelectData() {
        List<String> list = initData();
        for (int i = 0; i < list.size(); i++) {

            workItemList.add(new NJMhqSelectItem1(list.get(i), false));
        }
        return workItemList;
    }

    private List<String> initData() {
        titlelist.clear();
        titlelist.add("1A");
        titlelist.add("2A");
        titlelist.add("3A");
        titlelist.add("4A");
        titlelist.add("5A");
        titlelist.add("6A");
        titlelist.add("8A");
        titlelist.add("10A");
        titlelist.add("21B");
        titlelist.add("34B");
        titlelist.add("55B");
        titlelist.add("89B");
        titlelist.add("144B");
        titlelist.add("183B");
        titlelist.add("297B");

        return titlelist;
    }

    public NJMhqSelectItem1() {
    }

    public NJMhqSelectItem1(String s, boolean b) {
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
