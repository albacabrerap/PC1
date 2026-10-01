package com.practica.pc1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusEventRepository extends JpaRepository<CampusEvent,Long> {
    Page<CampusEvent> findAllEventsWithStatusPublished(Pageable pageable);
}
