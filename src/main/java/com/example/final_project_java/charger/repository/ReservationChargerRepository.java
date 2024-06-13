package com.example.final_project_java.charger.repository;

import com.example.final_project_java.charger.Entity.ReservationCharger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationChargerRepository extends JpaRepository<ReservationCharger, String> {
}