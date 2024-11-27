package com.kuake.fix_client.controller;

import com.kuake.fix_client.msg.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quickfix.*;
import quickfix.field.ClOrdID;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzihao
 * @create 2024-11-25-23:25
 */
@RestController
@Slf4j
public class MsgController {


    @Autowired
    SocketInitiator socketInitiator;



    @GetMapping("/hello")
    public void sayHelloClient() throws FieldNotFound {
        String msg = "hello" + System.currentTimeMillis();
        CustomMessage message = new CustomMessage();
        message.setName(msg);
        socketInitiator.getManagedSessions().get(0).send(message);

    }

    @GetMapping("/logon")
    public void saylogon(){
        Session session = socketInitiator.getManagedSessions().get(0);
        log.info("log on......");
        session.logon();
    }

    @GetMapping("/logout")
    public void logout(){
        Session session = socketInitiator.getManagedSessions().get(0);
        log.info("log out......");
        session.logout();
    }

    @GetMapping("/session/list")
    public String sessions(){
        List<Session> managedSessions = socketInitiator.getManagedSessions();
        return managedSessions.stream().map(a->a.getSessionID().toString()).collect(Collectors.joining());
    }

}
