package cn.jaminye;

import cn.hutool.system.oshi.OshiUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author jamin
 * @date 2020/10/24 5:47 下午
 */
@Slf4j
public class Agent {
    ZkClient zkClient;
    private static Agent ourInstance = new Agent();
    /**
     * root节点
     */
    private final String ROOT_PATH = "/monitor";
    /**
     * 服务地址
     */
    private final String SERVICE_PATH = ROOT_PATH + "/service";
    private Thread stateThread;

    public static Agent getInstance() {
        return ourInstance;
    }

    /**
     * 临时节点
     */
    private String nodePath;

    public static void premain(String args, Instrumentation instrumentation) {
        Agent.getInstance().init();
    }

    /**
     * 初始化
     *
     * @author jamin
     * @date 2020/10/28 11:19 下午
     */
    public void init() {
        // 建立连接
        zkClient = new ZkClient("10.211.55.4:2181", 5000, 10000);
        log.info("zookeeper连接成功");
        //创建主目录
        buildRoot();
        //创建临时节点
        createNode();
        stateThread = new Thread(() -> {
            while (true) {
                // 更新数据
                updateNode();
                try {
                    Thread.sleep(5000);
                    System.out.println("线程休眠");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "zk_stateThread");
        stateThread.setDaemon(true);
        stateThread.start();
    }

    /**
     * 修改临时节点数据
     *
     * @author jamin
     * @date 2020/10/28 11:19 下午
     */
    private void updateNode() {
        zkClient.writeData(nodePath, getOsInfo());
    }

    /**
     * 创建临时节点
     *
     * @author jamin
     * @date 2020/10/28 11:20 下午
     */
    private void createNode() {
        nodePath = zkClient.createEphemeralSequential(SERVICE_PATH, getOsInfo());
        log.info("创建服务节点");
    }

    /**
     * 获取系统信息
     *
     * @return {@link String}
     * @author jamin
     * @date 2020/10/28 11:20 下午
     */
    private String getOsInfo() {
        OsBean osBean = new OsBean();
        osBean.setLastUpdateTime(new Date());
        osBean.setIp(getLocalIp());
        osBean.setCpu(OshiUtil.getCpuInfo().getUsed());
        osBean.setUsedMemorySize((OshiUtil.getMemory().getTotal() - OshiUtil.getMemory().getAvailable()) / 1024 / 1024);
        osBean.setUsableMemorySize(OshiUtil.getMemory().getAvailable());
        osBean.setPid(ManagementFactory.getRuntimeMXBean().getName());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(osBean);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取本机地址
     *
     * @return {@link String}
     * @author jamin
     * @date 2020/10/24 6:28 下午
     */
    private String getLocalIp() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert inetAddress != null;
        return inetAddress.getHostAddress();
    }

    /**
     * 创建根节点
     *
     * @author jamin
     * @date 2020/10/24 6:06 下午
     */
    private void buildRoot() {
        if (!zkClient.exists(ROOT_PATH)) {
            zkClient.createPersistent(ROOT_PATH);
        }
    }

}
