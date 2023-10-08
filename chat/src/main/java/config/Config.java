package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class Config  implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    registry.addEndpoint("/server1").withSockJS();
        System.out.println("This is a message that will be displayed in the console");


    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
   registry.enableSimpleBroker("/topic");
   registry.setApplicationDestinationPrefixes("/app");

    }
}
