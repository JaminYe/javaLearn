package cn.jaminye.base;

import java.io.Serializable;

/**
 * @author Jamin
 * @date 2020/8/11 7:02
 */
public class User implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * name
     */
    private String name;
    /**
     * sex
     */
    private String sex;

    public User() {
    }

    public User(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
