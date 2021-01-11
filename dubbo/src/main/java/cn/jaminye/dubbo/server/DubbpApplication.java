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
        // RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.150.120:2181");
        //协议 端口号
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", -1);
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setInterface(UserService.class);
        // 接口不变 设置提供服务的为mock服务
        // setMock(serviceConfig);
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.setToken(true);
        // serviceConfig.setGroup("jamin");
        // setLoadbalance(serviceConfig);
        // serviceConfig.setLoadbalance("consistenthash");
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.export();
        System.out.println("服务已暴露");
        System.in.read();
    }


    /**
     * 设置为mock服务
     *
     * @param serviceConfig
     * @author Jamin
     * @date 2020/8/30 11:04
     */
    public static void setMock(ServiceConfig serviceConfig) {
        serviceConfig.setRef(new MockServiceImpl());
    }
}
