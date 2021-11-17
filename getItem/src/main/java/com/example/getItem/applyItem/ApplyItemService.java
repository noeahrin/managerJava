package com.example.getItem.applyItem;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ApplyItemService {

	private RabbitTemplate rabbit;

	private ApplyItemService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}

	public void sendApplyItem(ApplyItem product) {
		System.out.println(product);
		rabbit.convertAndSend("sales.product.create3", product);
	}
}
