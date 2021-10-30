package cn.jaminye.netty_socketio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * MessageDto
 *
 * @author wliduo[i@dolyw.com]
 * @date 2019/7/31 18:57
 */
@Data
public class MessageDto implements Serializable {

	/**
	 * 源客户端用户名
	 */
	private String sourceUserName;

	/**
	 * 消息内容
	 */
	private String msgContent;
}

