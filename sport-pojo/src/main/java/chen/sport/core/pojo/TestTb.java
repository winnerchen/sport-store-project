package chen.sport.core.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Yiheng Chen
 * @Description: 测试表实体类
 * @Date: Created in 10:55 2017/8/13
 * @Modified by:
 */
public class TestTb implements Serializable {
    private Integer id;
    private String name;
    private Date birthday;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
