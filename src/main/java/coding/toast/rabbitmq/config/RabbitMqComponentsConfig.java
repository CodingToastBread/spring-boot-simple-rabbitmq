package coding.toast.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqComponentsConfig {
	
	@Bean
	DirectExchange myDirectExchange() {
		return ExchangeBuilder.directExchange("my.direct.exchange")
			.durable(true).build();
	}
	
	@Bean
	TopicExchange myTopicExchange() {
		return ExchangeBuilder.topicExchange("my.topic.exchange")
			.durable(true).build();
	}
	
	@Bean
	Queue queue1() {
		return QueueBuilder.durable("my.first.queue").build();
	}
	
	@Bean
	Queue queue2() {
		return QueueBuilder.durable("my.second.queue").build();
	}
	
	@Bean
	Binding directBinding1(Queue queue1, DirectExchange myDirectExchange) {
		return BindingBuilder.bind(queue1).to(myDirectExchange).with("my.daily.code");
	}
	
	@Bean
	Binding directBinding2(Queue queue2, DirectExchange myDirectExchange) {
		return BindingBuilder.bind(queue2).to(myDirectExchange).with("your.daily.code");
	}
	
	@Bean
	Binding topicBinding1(Queue queue1, TopicExchange myTopicExchange) {
		return BindingBuilder.bind(queue1).to(myTopicExchange).with("*.*.code");
	}
	
	@Bean
	Binding topicBinding2(Queue queue2, TopicExchange myTopicExchange) {
		return BindingBuilder.bind(queue2).to(myTopicExchange).with("my.*.*");
	}
}
