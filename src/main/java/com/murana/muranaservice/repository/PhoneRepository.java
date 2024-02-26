package com.murana.muranaservice.repository;

import com.murana.muranaservice.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
