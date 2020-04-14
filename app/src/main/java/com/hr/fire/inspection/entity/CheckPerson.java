package com.hr.fire.inspection.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
        nameInDb = "t_check_person"
)
public class CheckPerson {

    @Id(autoincrement = true)
    private Long id;

    private String profession;//专业

    private String checkPerson;//检查人

    private Date checkDate;//检查日期

@Generated(hash = 326552434)
public CheckPerson(Long id, String profession, String checkPerson,
        Date checkDate) {
    this.id = id;
    this.profession = profession;
    this.checkPerson = checkPerson;
    this.checkDate = checkDate;
}

@Generated(hash = 1689252211)
public CheckPerson() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getProfession() {
    return this.profession;
}

public void setProfession(String profession) {
    this.profession = profession;
}

public String getCheckPerson() {
    return this.checkPerson;
}

public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
}

public Date getCheckDate() {
    return this.checkDate;
}

public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
}

}
