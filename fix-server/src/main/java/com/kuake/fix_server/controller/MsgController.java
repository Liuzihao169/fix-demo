package com.kuake.fix_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import quickfix.*;
import quickfix.field.BeginSeqNo;
import quickfix.field.EndSeqNo;
import quickfix.field.MsgType;
import quickfix.SocketInitiator;

/**
 * @author liuzihao.
 * 2024/11/26 14:31
 **/
@RestController
public class MsgController {

    private final SocketAcceptor socketAcceptor;

    public MsgController(SocketAcceptor socketAcceptor) {
        this.socketAcceptor = socketAcceptor;
    }

    @GetMapping("/get/resend/{startSeq}/{endSeq}")
    public void reSend(@PathVariable("startSeq") int start, @PathVariable("endSeq") int endSeq) {
        SessionID session = socketAcceptor.getManagedSessions().get(0).getSessionID();
        sendResendRequest(session, start, endSeq);
    }



    public  void sendResendRequest(SessionID sessionID, int beginSeqNo, int endSeqNo) {
        // 创建一个 Resend Request 消息
        Message resendRequest = new Message();

        // 设置消息类型为 Resend Request
        resendRequest.getHeader().setString(MsgType.FIELD, MsgType.RESEND_REQUEST);

        // 添加 BeginSeqNo (7) 字段: 请求消息的开始序列号
        resendRequest.setField(new BeginSeqNo(beginSeqNo));

        // 添加 EndSeqNo (16) 字段: 请求消息的结束序列号
        // 设置为0表示从 BeginSeqNo 开始的所有消息
        resendRequest.setField(new EndSeqNo(endSeqNo));

        try {
            // 通过给定的 SessionID 发送 Resend Request 消息
            Session.sendToTarget(resendRequest, sessionID);
        } catch (SessionNotFound e) {
            System.out.println("Error sending Resend Request: " + e.getMessage());
        }
    }
}
