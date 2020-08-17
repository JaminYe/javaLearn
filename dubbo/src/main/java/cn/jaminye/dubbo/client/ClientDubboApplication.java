package cn.jaminye.dubbo.client;

import cn.jaminye.base.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jamin
 * @date 2020/8/11 7:25
 */
public class ClientDubboApplication {
    public static void main(String[] args) throws IOException {
        ApplicationConfig applicationConfig = new ApplicationConfig("client");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(UserService.class);
        // referenceConfig.setUrl("dubbo://192.168.21.1:20880/cn.jaminye.base.UserService");
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setGroup("jamin");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (bufferedReader.readLine().equals("quit")) {
                break;
            }
            UserService userService = (UserService) referenceConfig.get();
            System.out.println(userService.getName("1111"));
        }
    }

}
