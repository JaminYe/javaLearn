package cn.jaminye.spring_security_demo1.dao;

import cn.jaminye.spring_security_demo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jamin
 * @date 2022/3/6 16:31
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

	/**
	 * 通过用户名称查找用户
	 *
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);
}
