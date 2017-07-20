package se.voipbusiness.ms;

import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by espinraf on 2017-07-20.
 */
@Component
public class TestWebSocketHandler extends TextWebSocketHandler {

    private final Logger log = LoggerFactory.getLogger(TestWebSocketHandler.class);
    private HashMap<String, WebSocketSession> sesMap = new HashMap<String, WebSocketSession>();

    @Autowired
    private IMap<String, String> cacheMap;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info(message.getPayload());
        try {
            session.sendMessage(new TextMessage(message.getPayload()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(message.getPayload().contains("cacheMap")){
            sendIMap();
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        log.error("error occured at sender " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sesMap.remove(session.getId());
        log.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sesMap.put(session.getId(), session);
        log.info("Connected ... " + session.getId());
    }

    public void sendToAll(String msg){
        for (WebSocketSession ses : sesMap.values()){
            try {
                ses.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendIMap(){
        for (WebSocketSession ses : sesMap.values()){
            try {
                ses.sendMessage(new TextMessage(cacheMap.get("1")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
