package coding.toast.rabbitmq;

import coding.toast.rabbitmq.data.PersonInfo;
import coding.toast.rabbitmq.provider.MyMessagePublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRabbitmqApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(MyMessagePublisher publisher) {
		return args -> {
			
			// Binding 관련 내용을 까먹었으면 RabbitMqComponentsConfig 클래스를 다시 참조해주세요~
			
			// DirectExchange => directBinding1 (routing_key = my.daily.code) => my.first.queue 로 메세지 전송
			publisher.directMsgPub("my.daily.code",
				new PersonInfo("MY_DAILY_CODE", 10));
			
			// DirectExchange => directBinding2(routing_key = your.daily.code) => my.second.queue 로 메세지 전송
			publisher.directMsgPub("your.daily.code",
				new PersonInfo("YOUR_DAILY_CODE", 20));
			
			// TopicExchange => topicBinding1(routing_key = *.*.code) => my.first.queue 로 메세지 전송
			publisher.topicMsgPub("nice.cool.code",
				new PersonInfo("NICE_COOL_CODE", 30));
			
			// TopicExchange => topicBinding2(routing_key = my.*.*) => my.second.queue 로 메세지 전송
			publisher.topicMsgPub("my.daily.life",
				new PersonInfo("MY_DAILY_LIFE", 40));
			
		};
	}
}
