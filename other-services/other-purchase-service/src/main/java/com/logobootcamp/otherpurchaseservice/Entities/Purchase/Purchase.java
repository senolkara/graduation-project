package com.logobootcamp.otherpurchaseservice.Entities.Purchase;

import com.logobootcamp.otherpurchaseservice.Entities.Traveller.Traveller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long travelId;

    @ManyToOne
    private Traveller traveller;

    private Long paymentId;
}
