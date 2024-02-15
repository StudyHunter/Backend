package inf.questpartner.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import inf.questpartner.config.ChatHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, WebSocketConfigurer {

    private final ChatHandler chatHandler;

    public WebSocketConfig(ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 프로토콜을 사용하는 엔드포인트를 등록
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로 메시지 전달할 때 사용할 메시지 브로커를 설정
        registry.enableSimpleBroker("/topic");
        // 클라이언트에서 메시지를 전송할 때 사용할 목적지 prefix를 설정
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocket 핸들러를 등록
        registry.addHandler(chatHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
