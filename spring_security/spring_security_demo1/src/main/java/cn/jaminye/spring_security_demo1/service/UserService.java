package cn.jaminye.spring_security_demo1.service;

import cn.jaminye.spring_security_demo1.dao.UserDao;
import cn.jaminye.spring_security_demo1.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Jamin
 * @date 2022/3/6 16:34
 */
@Service
public class UserService implements UserDetailsService {

	@Resource
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findUserByUsername(username);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return user;
	}
}
