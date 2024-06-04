package coding.toast.rabbitmq.controller;

import coding.toast.rabbitmq.data.PersonInfo;
import coding.toast.rabbitmq.provider.MyMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageSendController {
	
	private final MyMessagePublisher publisher;
	
	@PostMapping("/sendMsg")
	PersonInfo sendMsg(@RequestBody PersonInfo personInfo) {
		System.out.println("personInfo = " + personInfo);
		publisher.directMsgPub("my.direct.queue", personInfo);
		return personInfo;
	}
}

