package cn.jaminye.base;

/**
 * 用户服务
 *
 * @author Jamin
 * @date 2020/8/11 7:01
 */
public interface UserService {
    User getUser();

    User getName(String id);

    User getByName(String name, String sex);
}
