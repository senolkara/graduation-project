package com.logobootcamp.notificationservice.Dao.Notification;

import com.logobootcamp.commonservice.Dto.Notification.NotificationDto;

public interface INotificationDao {
    void sendContent(NotificationDto notificationDto);
}
