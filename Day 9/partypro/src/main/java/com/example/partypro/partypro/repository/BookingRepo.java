package com.example.partypro.partypro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.partypro.partypro.model.Booking;

public interface BookingRepo extends JpaRepository<Booking,Long>{
    Optional<Booking> findBybName(String bName);

    
}
