package com.logobootcamp.othertravelservice.Entities.Travel;

import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "travels")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Travel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long provinceFrom;

    private Long provinceWhere;

    private Double amount;

    private Date date;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StatusType statusType;
}
