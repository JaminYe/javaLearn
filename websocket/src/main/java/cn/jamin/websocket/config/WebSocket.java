package cn.jamin.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamin
 * @date 2020/12/29 21:06
 */
@ServerEndpoint("/webSoket")
@Component
public class WebSocket {
	private static volatile int total = 0;
	private static Set<Session> sessions = new HashSet<>();


	@OnOpen
	public void onOpen(Session session) {
		total++;
		sessions.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		total--;
		sessions.remove(session);
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		sendMessage(message);
	}

	private void sendMessage(String message) {
		for (Session session : sessions) {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
