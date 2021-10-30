package cn.jaminye.netty_socketio.config;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 启动类
 *
 * @author jaminye
 * @date 2021/10/30 下午2:39
 */
@Slf4j
@Component
public class SocketServer implements CommandLineRunner {

	private SocketIOServer socketIOServer;

	@Autowired
	public SocketServer(SocketIOServer socketIOServer) {
		this.socketIOServer = socketIOServer;
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("=================socket io 启动============");
		socketIOServer.start();
		log.info("=================socket io启动成功===========");
	}
}
