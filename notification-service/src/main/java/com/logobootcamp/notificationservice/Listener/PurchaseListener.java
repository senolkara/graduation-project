package com.logobootcamp.notificationservice.Listener;

import com.logobootcamp.commonservice.Dto.Notification.NotificationDto;
import com.logobootcamp.notificationservice.Business.Notification.ISenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseListener {

    /**
     * kullanıcının satın almış olduğu bilet detaylarını
     * gerekli işlemleri yapmak üzere dinlemesi
     *
     */
    private final ISenderService iSenderService;

    @RabbitListener(queues = "graduation.project")
    public void handleMessage(NotificationDto notificationDto) {
        System.out.println("Call Received...:" + notificationDto.toString());
        System.out.println("----------- database saving ------------");
        System.out.println();
        this.iSenderService.sendContent(notificationDto);
        System.out.println();
        System.out.println("------------ database saved -------------");
    }
}
