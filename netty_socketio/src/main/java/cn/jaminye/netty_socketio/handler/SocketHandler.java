package cn.jaminye.netty_socketio.handler;

import cn.jaminye.netty_socketio.entity.MessageDto;
import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jaminye
 * @date 2021/10/30 下午2:53
 */
@Component
@Slf4j
public class SocketHandler {

	private final SocketIOServer socketIOServer;
	private Map<String, UUID> clientMap = new ConcurrentHashMap<>(16);

	@Autowired
	public SocketHandler(SocketIOServer socketIOServer) {
		this.socketIOServer = socketIOServer;
	}

	public Map<String, UUID> getClientMap() {
		return clientMap;
	}

	public void setClientMap(Map<String, UUID> clientMap) {
		this.clientMap = clientMap;
	}

	@OnConnect
	public void onConnect(SocketIOClient socketIOClient) {
		String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
		if (StringUtils.isNotBlank(userName)) {
			log.info("用户开启{}连接,sessionId为{},remoteAddress为{}", userName, socketIOClient.getSessionId().toString(),
					socketIOClient.getRemoteAddress().toString());
			clientMap.put(userName, socketIOClient.getSessionId());
		}
	}

	@OnDisconnect
	public void onDisConnect(SocketIOClient socketIOClient) {
		String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
		if (StringUtils.isNotBlank(userName)) {
			log.info("用户{}断开连接,sessionId为{},remoteAddress为{}", userName, socketIOClient.getSessionId().toString(),
					socketIOClient.getRemoteAddress().toString());
			clientMap.remove(userName);
		}
	}

	@OnEvent("sendMsg")
	public void sendMsg(SocketIOClient socketIOClient, AckRequest ackRequest, MessageDto messageDto) {
		socketIOServer.getBroadcastOperations().sendEvent("chatevent",messageDto);
	}

}
