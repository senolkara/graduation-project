package com.logobootcamp.notificationservice.Dao.Notification.SendToPhone;

import com.logobootcamp.commonservice.Dto.Notification.NotificationDto;
import com.logobootcamp.notificationservice.Dao.Notification.INotificationDao;
import com.logobootcamp.notificationservice.Dao.Notification.INotificationMongoRepository;
import com.logobootcamp.notificationservice.Entities.Notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MongoDBPhoneDao implements INotificationDao {

    private final INotificationMongoRepository iNotificationMongoRepository;

    /**
     * telefon numarasına mesaj gönderme
     *
     * @param notificationDto
     */
    @Override
    public void sendContent(NotificationDto notificationDto) {
        System.out.println("send notification to phone: " + notificationDto.getUserDto().getPhone());
        String content = notificationDto.getUserDto().getFirstName() + " " +
                notificationDto.getUserDto().getLastName() + " registered user purchased " +
                notificationDto.getPurchaseDtoList().size() + " tickets.";
        Notification notification = new Notification();
        notification.setEmail(notificationDto.getUserDto().getEmail());
        notification.setPhone(notificationDto.getUserDto().getPhone());
        notification.setContent(content);
        this.iNotificationMongoRepository.save(notification);
    }
}
