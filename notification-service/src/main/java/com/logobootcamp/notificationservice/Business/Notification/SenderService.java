package com.logobootcamp.notificationservice.Business.Notification;

import com.logobootcamp.commonservice.Dto.Notification.NotificationDto;
import com.logobootcamp.notificationservice.Dao.Notification.INotificationDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SenderService implements ISenderService {

    /**
     * bildirim işlemleri için kullanılan servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final INotificationDao iNotificationDao;

    @Override
    public void sendContent(NotificationDto notificationDto) {
        this.iNotificationDao.sendContent(notificationDto);
    }
}
