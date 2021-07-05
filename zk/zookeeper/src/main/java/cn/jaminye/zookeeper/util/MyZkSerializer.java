package cn.jaminye.zookeeper.util;

import org.I0Itec.zkclient.exception.ZkMarshallingError;

/**
 * zkclient序列化使用
 *
 * @author jamin
 * @date 2020/10/13 11:49 下午
 */
public class MyZkSerializer implements org.I0Itec.zkclient.serialize.ZkSerializer {
    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        return String.valueOf(data).getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes);
    }
}
