package com.logobootcamp.notificationservice.Entities.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(value = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification implements Serializable {

    @Id
    private String id;

    private String email;

    private String phone;

    private String content;
}
