package com.uoffice.shiro.RabbitMQ;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit配置类
 */
@Configuration
public class RabbitConfig {
     final static  String topicTitle1="topic.user";
     final static  String topicTitle2="topic.user2";

    @Bean
    public Queue Queue(){
        return new Queue("myRabbit");
    }

    //配置两个topic消息队列类型的消息队列（最灵活）
    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitConfig.topicTitle1);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitConfig.topicTitle2);
    }

    //交换机，负责匹配消息队列的策略
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    //将消息队列绑定到交换机中
    @Bean
    public Binding bindingExchangeWithQueue(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with(RabbitConfig.topicTitle1);
    }
    //#表示绑定的id开头只要是topic.的都能匹配到
    @Bean
    Binding bindingExchangeWithQueues(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

}
