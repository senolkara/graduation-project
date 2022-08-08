package com.logobootcamp.adminpurchaseservice.Entities.Traveller;

import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "travellers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Traveller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private GenderType genderType;
}
