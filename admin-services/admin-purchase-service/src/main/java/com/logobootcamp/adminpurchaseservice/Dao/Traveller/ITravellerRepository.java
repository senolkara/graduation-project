package com.logobootcamp.adminpurchaseservice.Dao.Traveller;

import com.logobootcamp.adminpurchaseservice.Entities.Traveller.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITravellerRepository extends JpaRepository<Traveller, Long> {
}
