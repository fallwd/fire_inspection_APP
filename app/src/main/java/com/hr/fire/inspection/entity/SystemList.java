package com.hr.fire.inspection.entity;

import android.widget.Button;

import java.io.Serializable;

public class SystemList implements Serializable {
    private Long id;

    private String name; //类型名称

    public SystemList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SystemList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
