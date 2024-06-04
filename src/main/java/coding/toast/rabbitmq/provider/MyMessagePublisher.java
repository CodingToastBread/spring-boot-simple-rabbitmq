package coding.toast.rabbitmq.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyMessageProvider {
	
	private final TopicExchange myTopicExchange;
	private final DirectExchange myDirectExchange;
	
	private RabbitTemplate rabbitTemplate;
	
	public void directMsgPub(String msg) {
		rabbitTemplate.convertAndSend(myTopicExchange.getName(), msg);
	}
	
	public void topicMsgPub() {
		
	}
	
}
