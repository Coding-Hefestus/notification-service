package com.uns.ac.rs.notificationservice.feign;

import common.events.ReservationEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign-services.scheduler-service.name}", url = "${feign-services.scheduler-service.url}")
public interface FeignClientSchedulerService {

    @GetMapping(value = "${feign-services.scheduler-service.path}" + "/{paymentId}", consumes = "application/json")
    ReservationEventDto fetchReservationEvent(@PathVariable("paymentId") Integer paymentId);
}
