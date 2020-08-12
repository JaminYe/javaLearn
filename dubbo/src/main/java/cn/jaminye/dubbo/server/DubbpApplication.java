package cn.jaminye.dubbo.server;

import cn.jaminye.base.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * dubbo启动类
 *
 * @author Jamin
 * @date 2020/8/11 7:09
 */
public class DubbpApplication {
    public static void main(String[] args) throws IOException {
        //应用名称
        ApplicationConfig applicationConfig = new ApplicationConfig("server");
        // 注册中心  RegistryConfig.NO_AVAILABLE
        RegistryConfig registryConfig = new RegistryConfig("multicast://224.5.6.7:1234?unicast=false");
        //协议 端口号
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", -1);
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.export();
        System.out.println("服务已暴露");
        System.in.read();
    }
}
