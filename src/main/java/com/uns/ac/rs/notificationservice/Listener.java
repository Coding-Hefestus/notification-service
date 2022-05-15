package com.uns.ac.rs.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uns.ac.rs.notificationservice.config.JmsConfig;
import common.events.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Component
@EnableJms
public class Listener {

    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper objectMapper;



    @JmsListener(destination = JmsConfig.RESERVATIONS_QUEUE)
    public void listen(String student) throws JsonProcessingException {
        Student s = new Gson().fromJson(student, Student.class);
        System.out.println(s);
    }

   /*   public void setConnectionFactory(ConnectionFactory cf) {
        this.jmsTemplate = new JmsTemplate(cf);
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }


  @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        //factory.setConnectionFactory(connectionFactory());
        //factory.setDestinationResolver(destinationResolver());
        factory.setSessionTransacted(true);
        factory.setConcurrency("3-10");
        return factory;
    }*/
}
