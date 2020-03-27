package gameserver.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import gameserver.model.Message;
import gameserver.model.UserEndPoint;
import org.springframework.stereotype.Component;
//See http://thegeekyasian.com/websocket-in-spring-boot/
@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
@Component
public class ChatEndpoint {
    private Session session;

    //
    // Map sessionId => session, user & endpoint data
    //
    public static HashMap<String, UserEndPoint> userEndPoints = new HashMap<>();

    public ChatEndpoint(){

    }
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {

        System.out.println("onOpen : "+ username);
        UserEndPoint userEndPoint  = new UserEndPoint();
        userEndPoint.user = username;
        userEndPoint.endPoint = this;
        userEndPoint.session = session;


        this.session = session;
        userEndPoints.put(userEndPoint.session.getId() , userEndPoint);

        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        String userFromSessionEndpoint = userEndPoints.get(session.getId()).user;
        message.setFrom(userFromSessionEndpoint);
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {

        Message message = new Message();
        String userFromSessionEndpoint = userEndPoints.get(session.getId()).user;
        message.setFrom(userFromSessionEndpoint);
        message.setContent("Disconnected!");
        userEndPoints.remove(session.getId());
        broadcast(message);

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) throws IOException, EncodeException {
        userEndPoints.values().forEach(userEndpoint -> {
            synchronized (userEndpoint) {
                try {
                    if (userEndpoint.session.isOpen()) {
                        userEndpoint.session
                                .getBasicRemote()
                                .sendObject(message);
                    }
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
