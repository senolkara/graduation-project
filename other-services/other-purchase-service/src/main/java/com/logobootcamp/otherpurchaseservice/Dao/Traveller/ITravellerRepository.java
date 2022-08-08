package com.logobootcamp.otherpurchaseservice.Dao.Traveller;

import com.logobootcamp.otherpurchaseservice.Entities.Traveller.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITravellerRepository extends JpaRepository<Traveller, Long> {
}
