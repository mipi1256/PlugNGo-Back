package com.example.final_project_java.charger.repository;

import com.example.final_project_java.charger.Entity.ReservationCharger;
import com.example.final_project_java.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationChargerRepository extends JpaRepository<ReservationCharger, String> {
    boolean existsByUser(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM ReservationCharger WHERE reservationNo = :reservationNo")
    void deleteByReservationNo(@Param("reservationNo") Integer reservationNo);
}