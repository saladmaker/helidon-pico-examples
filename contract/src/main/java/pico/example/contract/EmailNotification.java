package pico.example.contract;

import jakarta.inject.Singleton;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Singleton
public class EmailNotification implements Notification {
    private static final System.Logger LOGGER = System.getLogger(EmailNotification.class.getName());

    public void send(String message) {
        LOGGER.log(System.Logger.Level.INFO, String.format("send message: %s", message));
    }

    @PostConstruct
    void init() {
        LOGGER.log(System.Logger.Level.INFO, "post construct lifecycle");
    }

    @PreDestroy
    void clean() {
        LOGGER.log(System.Logger.Level.INFO, "pre destroy lifecycle");
    }

}
