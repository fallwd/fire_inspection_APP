package com.hr.fire.inspection.entity;

import java.util.ArrayList;
import java.util.List;

public class DryPowderFireSysTabSelect1 {
    private List<DryPowderFireSysTabSelect1> workItemList = new ArrayList();
    private List<String> titlelist = new ArrayList();

    private String title;
    private boolean state = false;

    /**
     * 根据不同的index加载不同的数据
     *
     * @param index
     * @return
     */
    public List<DryPowderFireSysTabSelect1> getWorkSelectData(int index) {
        List<String> list = null;
        workItemList.clear();
        if (index == 1) {
            list = initDataCar();
        } else if (index == 2) {
            list = initDataCar2();
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                workItemList.add(new DryPowderFireSysTabSelect1(list.get(i), false));
            }
        }
        return workItemList;
    }
    // 干粉灭火系统前两个tab的工作代号数据
    private List<String> initDataCar() {
        titlelist.clear();
        titlelist.add("1.外观检查");
        titlelist.add("2.阀门检查");
        titlelist.add("3.压力调节器有效性检查");
        titlelist.add("4.控制阀和分区阀的本地和遥控操作试验");
        titlelist.add("5.干粉搅拌");
        titlelist.add("6.推进气体钢瓶检查");
        titlelist.add("7.管线吹扫");
        titlelist.add("8.干粉含水量检查");
        titlelist.add("9.干粉容器、安全阀和排放软管的压力试验");
        titlelist.add("10.干粉容器的水压试验或无损检测");
        titlelist.add("10.检验日期标签");
        return titlelist;
    }

    // 消防员装备系统工作代号数据
    private List<String> initDataCar2() {
        titlelist.clear();
        titlelist.add("1.面罩检查");
        titlelist.add("2.调节装置检查");
        titlelist.add("3.功能试验");
        titlelist.add("4.气瓶充气");
        titlelist.add("5.检验日期标签");
        titlelist.add("6.呼吸阀检查");
        titlelist.add("7.背带检查");
        titlelist.add("8.气瓶水压试验");
        titlelist.add("9.新气瓶");
        return titlelist;
    }




    public DryPowderFireSysTabSelect1() {
    }

    public DryPowderFireSysTabSelect1(String s, boolean b) {
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
