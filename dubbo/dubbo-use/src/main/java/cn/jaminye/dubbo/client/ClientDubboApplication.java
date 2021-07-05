package cn.jaminye.dubbo.client;

import cn.jaminye.base.User;
import cn.jaminye.base.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jamin
 * @date 2020/8/11 7:25
 */
public class ClientDubboApplication {
    public static void main(String[] args) throws IOException {
        ApplicationConfig applicationConfig = new ApplicationConfig("client");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.150.120:2181");
        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setRegistry(registryConfig);
        // referenceConfig.setUrl("dubbo://192.168.150.1:20880/cn.jaminye.base" +
        //         ".UserService?token=1fd0461c-964b-4b53-ba10-fd316bf4469c");
        referenceConfig.setInterface(UserService.class);
        // referenceConfig.setUrl("dubbo://192.168.21.1:20880/cn.jaminye.base.UserService");
        referenceConfig.setApplication(applicationConfig);
        //设置同步或异步
        // referenceConfig.setAsync(false);
        //分组
        // referenceConfig.setGroup("jamin");
        referenceConfig.setTimeout(3000);
        //设置负载均衡
        // setLoadbalance(referenceConfig);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            long begin = System.currentTimeMillis();
            if ("quit".equals(bufferedReader.readLine())) {
                break;
            }

/*          异步调用
            UserService userService = (UserService) referenceConfig.get();
            userService.getName("111");
            Future<Object> future0 = RpcContext.getContext().getFuture();
            userService.getName("222");
            Future<Object> future1 = RpcContext.getContext().getFuture();
            try {
                System.out.println((User) future0.get());
                System.out.println(((User) future1.get()));
                long end = System.currentTimeMillis();
                System.out.println(end - begin);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }*/
            //调用链 隐式传参
            // RpcContext.getContext().setAttachment("ceshi", "123");

            referenceConfig.setFilter("-testFilter");
            UserService userService = (UserService) referenceConfig.get();
            User user = userService.getName("123");
            System.out.println(user);

            /*隐式引用
            referenceConfig.setGeneric(true);
            GenericService genericService = (GenericService) referenceConfig.get();
            Object result = genericService.$invoke("getName", new String[]{"java.lang.String"},
            new Object[]{"测试"});
            System.out.println(result);*/
        }
    }

    /**
     * 设置负载均衡
     *
     * @param referenceConfig
     * @author Jamin
     * @date 2020/8/23 18:07
     */
    public static void setLoadbalance(ReferenceConfig referenceConfig) {
        referenceConfig.setLoadbalance("consistenthash");
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getByName");
        Map<String, String> params = new HashMap<>(10);
        params.put("hash.arguments", "0,1");
        params.put("hash.nodes", "200");
        methodConfig.setParameters(params);
        referenceConfig.setMethods(Collections.singletonList(methodConfig));

    }

}
