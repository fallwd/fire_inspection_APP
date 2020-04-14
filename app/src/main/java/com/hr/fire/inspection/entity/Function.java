package com.hr.fire.inspection.entity;

public class Function {
    private String name;
    private int icon;
    private boolean isGray = false;

    public boolean isShowRedCircle() {
        return isShowRedCircle;
    }

    public void setShowRedCircle(boolean showRedCircle) {
        isShowRedCircle = showRedCircle;
    }

    //显示右上角的红点
    private boolean isShowRedCircle;

    //需要在右上角显示"新"的时候设置为true即可
    private boolean isNewFunc;

    public boolean isNewFunc() {
        return isNewFunc;
    }

    public void setNewFunc(boolean newFunc) {
        isNewFunc = newFunc;
    }

    private int tag;

    public Function(String name, int icon, boolean isGray) {
        this.name = name;
        this.icon = icon;
        this.isGray = isGray;
        tag = icon;
    }

    public Function(String name, int icon) {
        this.name = name;
        this.icon = icon;
        tag = icon;
    }

    public int getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isGray() {
        return isGray;
    }

    public void setGray(boolean gray) {
        isGray = gray;
    }
}
