package com.xzixi.demo.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
public class RabbitTemplateAdapter {

    private RabbitTemplate rabbitTemplate;

    public RabbitTemplateAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.error("消息：{} 发送失败，应答码：{}，原因：{}，交换机：{}，路由键：{}",
                    message.getMessageProperties().getCorrelationId(), replyCode,
                    replyText, exchange, routingKey);
        });
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String id = correlationData==null? "未知": correlationData.getId();
            if (ack) {
                log.debug("发送消息成功，id：{}", id);
            } else {
                log.error("发送消息失败，id：{}，原因：{}", id, cause);
            }
        });
    }

    public void send(String exchange, String routingKey, final Object object) {
        rabbitTemplate.convertAndSend(exchange, routingKey, object);
    }

}
