package com.kuake.fix_server.component;

/**
 * @author liuzihao
 * @create 2024-11-25-23:18
 */
import quickfix.*;

public class MyServerApplication implements Application {

    @Override
    public void onCreate(SessionID sessionId) {
        System.out.println("Session created: " + sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        System.out.println("Client logged in: " + sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        System.out.println("Client logged out: " + sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("Sending admin message to client: " + message);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        System.out.println("Sending application message to client: " + message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) {
        System.out.println("Received admin message from client: " + message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("Received application message from client: " + message);
        // 可以在这里处理接收到的业务消息
    }
}

