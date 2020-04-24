package com.hr.fire.inspection.entity;

public class Function {
    private String name;
    private int icon;
    private boolean isGray = false;
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

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", isGray=" + isGray +
                ", tag=" + tag +
                '}';
    }
}
