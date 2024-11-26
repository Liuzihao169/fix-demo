package com.kuake.fix_client.config;

/**
 * @author liuzihao
 * @create 2024-11-25-23:21
 */
import com.kuake.fix_client.component.MyApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

@Configuration
public class QuickFixConfig {

    @Bean
    public Application quickFixApplication() {
        return new MyApplication(); // 自定义 Application 实现
    }

    @Bean
    public SessionSettings sessionSettings() throws ConfigError {
        return new SessionSettings("quickfix.cfg");
    }

    @Bean
    public MessageStoreFactory messageStoreFactory(SessionSettings sessionSettings) {
        return new FileStoreFactory(sessionSettings);
    }

    @Bean
    public LogFactory logFactory(SessionSettings sessionSettings) {
        return new FileLogFactory(sessionSettings);
    }

    @Bean
    public MessageFactory messageFactory() {
        return new DefaultMessageFactory();
    }

    @Bean
    public SocketInitiator socketInitiator(
            Application application,
            MessageStoreFactory messageStoreFactory,
            SessionSettings sessionSettings,
            LogFactory logFactory,
            MessageFactory messageFactory
    ) throws ConfigError {
        return new SocketInitiator(application, messageStoreFactory, sessionSettings, logFactory, messageFactory);
    }
}

