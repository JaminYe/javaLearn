package cn.jaminye.sharding_sphere_sharding_jdbc.dao;

import cn.jaminye.sharding_sphere_sharding_jdbc.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
