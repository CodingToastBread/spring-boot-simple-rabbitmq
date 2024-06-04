package coding.toast.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMqCreationConfig {
	public RabbitMqCreationConfig(AmqpAdmin rabbitAdmin,
	                              DirectExchange myDirectExchange,
	                              TopicExchange myTopicExchange,
	                              List<Queue> queueList,
	                              List<Binding> bindingList) {
		
		// DirectExchange, TopicExchange 생성 (RabbitMQ 에 없는 경우에만, 이미 있으면 ignore)
		rabbitAdmin.declareExchange(myTopicExchange);
		rabbitAdmin.declareExchange(myDirectExchange);
		
		// Queue 생성 (RabbitMQ 에 없는 경우에만, 이미 있으면 ignore)
		for (Queue queue : queueList) {
			rabbitAdmin.declareQueue(queue);
		}
		
		// Binding 생성 (RabbitMQ 에 없는 경우에만, 이미 있으면 ignore)
		for (Binding binding : bindingList) {
			rabbitAdmin.declareBinding(binding);
		}
	}
}
