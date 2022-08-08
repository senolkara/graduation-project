package com.logobootcamp.notificationservice.Business.Notification;

import com.logobootcamp.commonservice.Dto.Notification.NotificationDto;

public interface ISenderService {
    void sendContent(NotificationDto notificationDto);
}
