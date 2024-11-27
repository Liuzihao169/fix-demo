package com.kuake.fix_server.component;

/**
 * @author liuzihao
 * @create 2024-11-25-23:18
 */
import com.kuake.fix_server.msg.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.MsgType;

@Slf4j
public class MyServerApplication extends MessageCracker implements Application {

    @Override
    public void onCreate(SessionID sessionId) {
        log.info("Session created: " + sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        log.info("Client logged in: " + sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        log.info("Client logged out: " + sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        log.info(getMsgTypeAndSeq(message)+",Sending admin message to client: " + message);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        log.info("Sending application message to client: " + message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) {
        log.info(getMsgTypeAndSeq(message)+",Received admin message from client: " + message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        log.info("msg");
        log.info("Received application message from client: " + message);
        // 可以在这里处理接收到的业务消息
    }
    String getMsgTypeAndSeq(Message message) {
        String msgType ="";
        try {
            msgType = message.getHeader().getString(MsgType.FIELD);
        } catch (FieldNotFound e) {
            log.error("",e);
        }
        int seq = 0;
        try {
            seq = message.getHeader().getInt(34);
        } catch (FieldNotFound e) {
            log.error("",e);
        }
        return msgType +"===>seq:"+seq;
    }
}

