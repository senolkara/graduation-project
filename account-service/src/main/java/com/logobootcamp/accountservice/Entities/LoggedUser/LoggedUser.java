package com.logobootcamp.accountservice.Entities.LoggedUser;

import com.logobootcamp.accountservice.Entities.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "logged_users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoggedUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Date date;
}
