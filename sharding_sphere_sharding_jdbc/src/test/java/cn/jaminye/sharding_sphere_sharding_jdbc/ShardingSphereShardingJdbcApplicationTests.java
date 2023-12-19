package cn.jaminye.sharding_sphere_sharding_jdbc;

import cn.jaminye.sharding_sphere_sharding_jdbc.dao.UserMapper;
import cn.jaminye.sharding_sphere_sharding_jdbc.entity.User;
//import com.baomidou.dynamic.datasource.annotation.DS;
//import com.baomidou.dynamic.datasource.annotation.DSTransactional;
//import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.codehaus.groovy.runtime.typehandling.NumberMath;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
@RunWith(SpringRunner.class)
class ShardingSphereShardingJdbcApplicationTests {
	@Resource
	private UserMapper userMapper;

	@Test
	void testInsert() {
		SecureRandom secureRandom = new SecureRandom();
		for (int i = 0; i < 1000; i++) {
			User user = new User();
			user.setId(Long.valueOf(i));
			user.setAge(secureRandom.nextInt(100));
			user.setName("jaminye" + i);
			userMapper.insert(user);
		}

	}

	/**
	 * test list
	 */
	@Test
	void testList(){
		List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class));
		System.out.println(users);
	}


	@Test
	void testIn(){
		List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class).in(User::getId,123L,8L).eq(User::getAge,12).orderByDesc(User::getAge));
		//List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class).between(User::getId,0L,100L));
		System.out.println(users);
	}


	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(i%2+"---------"+(NumberMath.intdiv(i%4,2).intValue()+1));
		}
	}

}
