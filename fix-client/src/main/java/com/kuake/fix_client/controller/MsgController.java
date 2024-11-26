package com.kuake.fix_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quickfix.SocketInitiator;

/**
 * @author liuzihao
 * @create 2024-11-25-23:25
 */
@RestController
public class MsgController {


    @Autowired
    SocketInitiator socketInitiator;



    @GetMapping("/hello")
    public void sayHelloClient() {

    }

    @GetMapping("/logon")
    public void saylogon(){

    }

}
