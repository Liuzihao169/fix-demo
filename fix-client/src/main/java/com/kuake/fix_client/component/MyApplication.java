package com.kuake.fix_client.component;

/**
 * @author liuzihao
 * @create 2024-11-25-23:22
 */
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.MsgType;

@Slf4j
public class MyApplication extends MessageCracker implements Application {

    @Override
    public void onCreate(SessionID sessionId) {
        log.info("Session created: " + sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        log.info("Logon: " + sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        log.info("Logout: " + sessionId);
    }

    @SneakyThrows
    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        log.info("msgType:"+ getMsgTypeAndSeq(message)+" ,Sending admin message: " + message + " " + sessionId);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        log.info("Sending app message: " + message+ " " + sessionId + " " + sessionId);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound {

        log.info("msgType:"+ getMsgTypeAndSeq(message)+" ,Received admin message: " + message + " " + sessionId);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        log.info("msgType:"+ getMsgTypeAndSeq(message)+" ,Received app message: " + message + " " + sessionId);
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
