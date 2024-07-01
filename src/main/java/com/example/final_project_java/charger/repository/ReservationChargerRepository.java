package com.example.final_project_java.charger.repository;

import com.example.final_project_java.charger.Entity.ReservationCharger;
import com.example.final_project_java.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationChargerRepository extends JpaRepository<ReservationCharger, String> {
    boolean existsByUser(User user);
}