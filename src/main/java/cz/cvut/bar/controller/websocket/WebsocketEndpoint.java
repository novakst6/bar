package cz.cvut.bar.controller.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.bar.controller.http.HomeController;
 
@ServerEndpoint("/websocket")
public class WebsocketEndpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(WebsocketEndpoint.class);
    
    /*@OnMessage
    public String hello(String message) {
    	logger.info("Received : "+ message);
        return message;
    }*/    
    
	@OnMessage
	public String onMessage(Session session, String message) {
		logger.info("Received : " + message);

		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					s.getBasicRemote().sendText(message);
				}
			}
		} catch (Exception ex) {
			logger.warn("Exception while broadcasting websock message");
		}
		
		return message;
	}
    
    @OnOpen
    public void myOnOpen(Session session) {
    	logger.info("WebSocket opened: " + session.getId());
    }
    
    @OnClose
    public void myOnClose(CloseReason reason) {
    	if(reason == null){
    		logger.info("Closing a WebSocket, reason null"); return;
    	}
    	logger.info("Closing a WebSocket due to " + reason.getReasonPhrase());
    }
    @OnError
    public void processError(Throwable t) {
    	logger.info("Error!!");
    	t.printStackTrace();
    }
}
