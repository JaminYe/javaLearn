package cn.jaminye;

import lombok.Data;

import java.util.Date;

/**
 * 系统信息
 *
 * @author jamin
 * @date 2020/10/24 6:16 下午
 */
@Data
public class OsBean {
    /**
     * pid
     */
    private String pid;
    /**
     * 使用内存情况
     */
    private Long usedMemorySize;
    /**
     * 可用内存
     */
    private Long usableMemorySize;
    /**
     * ip
     */
    private String ip;
    /**
     * cpu
     */
    private Double cpu;
    /**
     * 最新更新时间
     */
    private Date lastUpdateTime;

}
