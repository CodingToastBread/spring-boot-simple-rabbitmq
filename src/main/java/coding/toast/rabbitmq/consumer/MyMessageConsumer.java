package coding.toast.rabbitmq.consumer;

import coding.toast.rabbitmq.data.PersonInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 앞서 설정한 메시지 변환기 Bean 의 명칭입니다.
import static coding.toast.rabbitmq.config.MessageCommunicationConfig.MESSAGE_CONVERTER_NAME;

@Component
public class MyMessageConsumer {
	
	@RabbitListener(queues = "my.first.queue",
					messageConverter = MESSAGE_CONVERTER_NAME)
	public void firstQueueListener(PersonInfo personInfo) {
		System.out.printf("Message From my.first.queue => %s%n", personInfo);
	}
	
	@RabbitListener(queues = "my.second.queue",
					messageConverter = MESSAGE_CONVERTER_NAME)
	public void secondQueueListener(PersonInfo personInfo) {
		System.out.printf("Message From my.second.queue => %s%n", personInfo);
	}
}
