package cn.jaminye.netty_socketio.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jaminye
 * @date 2021/10/30 下午2:14
 */
@Slf4j
@Configuration
public class SocketIoConfig {

	/**
	 * 端口
	 */
	@Value("${socketio.port}")
	private int port;

	/**
	 * 连接数量
	 */
	@Value("${socketio.workCount}")
	private int workCount;


	/**
	 * 是否允许客户端连接
	 */
	@Value("${socketio.allowCustomRequests}")
	private boolean allowCustomRequests;


	/**
	 * 协议升级时间
	 */
	@Value("${socketio.upgradeTimeout}")
	private int upgradeTimeout;


	/**
	 * ping超时时间
	 */
	@Value("${socketio.pingTimeout}")
	private int pingTimeout;


	/**
	 * ping间隔时间
	 */
	@Value("${socketio.pingInterval}")
	private int pingInterval;


	/**
	 * http最大交互内容大小
	 */
	@Value("${socketio.maxHttpContentLength}")
	private int maxHttpContentLength;

	/**
	 * 每帧处理数据大小限制
	 */
	@Value("${socketio.maxFramePayloadLength}")
	private int maxFramePayloadLength;

	@Bean
	public SocketIOServer socketIOServer(){
		com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
		configuration.setPort(port);
		SocketConfig socketConfig = new SocketConfig();
		socketConfig.setReuseAddress(true);
		configuration.setSocketConfig(socketConfig);
		configuration.setWorkerThreads(workCount);
		configuration.setAllowCustomRequests(allowCustomRequests);
		configuration.setUpgradeTimeout(upgradeTimeout);
		configuration.setPingTimeout(pingTimeout);
		configuration.setPingInterval(pingInterval);
		configuration.setMaxHttpContentLength(maxHttpContentLength);
		configuration.setMaxFramePayloadLength(maxFramePayloadLength);
		return new SocketIOServer(configuration);
	}


	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
		return new SpringAnnotationScanner(socketIOServer);
	}

}
