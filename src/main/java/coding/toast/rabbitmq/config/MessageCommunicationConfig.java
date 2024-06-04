package coding.toast.rabbitmq.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class MessageCommunicationConfig {
	public static final String MESSAGE_CONVERTER_NAME = "jacksonMessageConverter";
	
	/**
	 * 전송 또는 수신하는 메시지에 대한 내용을 받습니다.
	 */
	@Bean(name = MESSAGE_CONVERTER_NAME)
	public MessageConverter jacksonMessageConverter() {
		ObjectMapper configure
			= new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return new Jackson2JsonMessageConverter(configure);
	}
	
	/**
	 * RabbitAutoConfiguration 에서 제공하는 default rabbitTemplate 에 추가적인 설정을 하려면 아래처럼 하면 됩니다!
	 */
	@Bean
	public RabbitTemplateCustomizer rabbitTemplateCustomizer() {
		return rabbitTemplate -> rabbitTemplate.setMessageConverter(jacksonMessageConverter());
	}
	
	// 개인의 rabbitTemplate 을 생성하고 싶다면 아래처럼하셔도 됩니다!
	// 참고로 RabbitAutoConfiguration 에서 거의다 뱃겨 온 내용입니다. 다만 setMessageConverter() 설정만 더 해줬습니다.
	/*
	@Bean
	public RabbitTemplate jsonRabbitTemplate(RabbitTemplateConfigurer configurer,
											 ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate();
		configurer.configure(template, connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
	*/
}
