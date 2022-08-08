package com.logobootcamp.notificationservice.Dao.Notification;

import com.logobootcamp.notificationservice.Entities.Notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface INotificationMongoRepository extends MongoRepository<Notification, String> {
}
