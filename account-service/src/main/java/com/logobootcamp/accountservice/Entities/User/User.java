package com.logobootcamp.accountservice.Entities.User;

import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String phone;

    @Column(nullable = false)
    @Length(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private GenderType genderType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StatusType statusType;
}
