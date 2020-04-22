package cn.jaminye.spring.iocsourcecode.entity;

/**
 * 实体类
 *
 * @author Jamin
 * @date 2020/4/9 22:01
 */

//@Component
public class Dog {
    /**
     * id
     */
    private int id;
    /**
     * name
     */
    private String name;

    public Dog() {
        System.out.println("dog无参构造");
    }
}
