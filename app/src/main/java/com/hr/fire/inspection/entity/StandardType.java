package com.hr.fire.inspection.entity;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
        nameInDb = "t_standard_type"
)
public class StandardType {

    @Id(autoincrement = true)
    private Long id;//分类标准id
    private String name;//类型名称
@Generated(hash = 895502079)
public StandardType(Long id, String name) {
    this.id = id;
    this.name = name;
}
@Generated(hash = 624325245)
public StandardType() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getName() {
    return this.name;
}
public void setName(String name) {
    this.name = name;
}

}
