package cn.jaminye.zookeeper.util;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.PanelUI;

/**
 * @author jamin
 * @date 2020/10/13 11:33 下午
 * zkClient使用
 */
public class ZkClientTest {
    ZkClient zkClient;

    @Before
    public void init() {
        zkClient = new ZkClient("172.16.150.128");
        zkClient.setZkSerializer(new MyZkSerializer());
    }

    /**
     * 获取数据
     */
    @Test
    public void getData() {
        String s = zkClient.readData("/jamin").toString();
        System.out.println(s);
    }

    /**
     * 赋值
     */
    @Test
    public void setData() {
        zkClient.writeData("/jamin", "test");
    }


    @Test
    public void test() throws InterruptedException {
        zkClient.subscribeDataChanges("/jamin", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(data.toString());
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }


}

