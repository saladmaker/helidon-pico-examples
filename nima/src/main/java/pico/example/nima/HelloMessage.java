package pico.example.nima;

import jakarta.inject.Singleton;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


@Singleton
public class HelloMessage implements Notification{
    private static final System.Logger LOGGER = System.getLogger(HelloMessage.class.getName());

    @Override
    public String message(String message){
        LOGGER.log(System.Logger.Level.INFO, String.format("send message: %s",message));
        return "Hello "+message;
    }
    
    @PostConstruct
    void init(){
        LOGGER.log(System.Logger.Level.INFO, "post construct lifecycle");
        
    }

    @PreDestroy
    void clean(){
        LOGGER.log(System.Logger.Level.INFO, "pre destroy lifecycle");
    }

}
