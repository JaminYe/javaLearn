package cn.jaminye.dubbo.server;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;

/**
 * mock服务
 *
 * @author Jamin
 * @date 2020/8/30 10:57
 */
public class MockServiceImpl implements GenericService {
    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        if ("getUser".equals(method)) {
            // User user = new User();
            // user.setId("111");
            // user.setSex("测试");
            // user.setName("测试mock框架");
            //可以使用hashMap代替对象
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "1111");
            map.put("name", "测试mock");
            return map;
            // return user;
        }
        return null;
    }
}
