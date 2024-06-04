package coding.toast.rabbitmq.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyMessagePublisher {
	
	private final TopicExchange myTopicExchange;
	private final DirectExchange myDirectExchange;
	// 자동설정에 의해 생성된 rabbitTemplate 사용,
	// 참고로 MessageCommunicationConfig 에서 커스텀 설정도 마친 상태입니다!
	private final RabbitTemplate jsonRabbitTemplate;
	
	public void directMsgPub(String routingKey, Object msg) {
		jsonRabbitTemplate.convertAndSend(myDirectExchange.getName(), routingKey, msg);
	}
	
	public void topicMsgPub(String routingKey, Object msg) {
		jsonRabbitTemplate.convertAndSend(myTopicExchange.getName(), routingKey, msg);
	}
}
