package com.kuake.fix_server.config;

/**
 * @author liuzihao
 * @create 2024-11-25-23:18
 */
import com.kuake.fix_server.component.MyServerApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

@Configuration
public class QuickFixServerConfig {

    @Bean
    public Application quickFixApplication() {
        return new MyServerApplication(); // 自定义服务端 Application 实现
    }

    @Bean
    public SessionSettings sessionSettings() throws ConfigError {
        return new SessionSettings("quickfix-server.cfg");
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
    public SocketAcceptor socketAcceptor(
            Application application,
            MessageStoreFactory messageStoreFactory,
            SessionSettings sessionSettings,
            LogFactory logFactory,
            MessageFactory messageFactory
    ) throws ConfigError {
        return new SocketAcceptor(application, messageStoreFactory, sessionSettings, logFactory, messageFactory);
    }
}
