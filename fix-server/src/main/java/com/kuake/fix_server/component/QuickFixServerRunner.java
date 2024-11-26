package com.kuake.fix_server.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import quickfix.SocketAcceptor;

@Component
public class QuickFixServerRunner implements CommandLineRunner {

    private final SocketAcceptor socketAcceptor;

    public QuickFixServerRunner(SocketAcceptor socketAcceptor) {
        this.socketAcceptor = socketAcceptor;
    }

    @Override
    public void run(String... args) throws Exception {
        socketAcceptor.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down FIX server...");
            socketAcceptor.stop();
        }));
    }
}
