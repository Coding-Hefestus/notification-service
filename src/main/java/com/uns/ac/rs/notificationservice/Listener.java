package com.uns.ac.rs.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uns.ac.rs.notificationservice.config.JmsConfig;
import com.uns.ac.rs.notificationservice.feign.FeignClientSchedulerService;
import common.events.ReservationEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class Listener {

    private SimpMessageSendingOperations messagingTemplate;
    private FeignClientSchedulerService feignClientSchedulerService;

    @Autowired
    public Listener(SimpMessageSendingOperations messagingTemplate, FeignClientSchedulerService feignClientSchedulerService){
        this.messagingTemplate = messagingTemplate;
        this.feignClientSchedulerService = feignClientSchedulerService;
    }

    @JmsListener(destination = JmsConfig.PAYMENT_QUEUE)
    public void listen(String paymentId) throws JsonProcessingException {


        //TODO - use 'payment' and fetch data from where ever it is supposed to be fetched to be able to construct ReservationEventDto
        ReservationEventDto eventDto = feignClientSchedulerService.fetchReservationEvent(Integer.parseInt(paymentId));


        messagingTemplate.convertAndSend("/scheduler/reservation-event", eventDto);

        //TODO - send emails to admins
    }

}
