package com.kuake.fix_client.component;

/**
 * @author liuzihao
 * @create 2024-11-25-23:24
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import quickfix.SocketInitiator;

@Component
public class QuickFixRunner implements CommandLineRunner {

    private final SocketInitiator socketInitiator;

    public QuickFixRunner(SocketInitiator socketInitiator) {
        this.socketInitiator = socketInitiator;
    }

    @Override
    public void run(String... args) throws Exception {
        socketInitiator.start();
        Runtime.getRuntime().addShutdownHook(new Thread(socketInitiator::stop));
    }
}

