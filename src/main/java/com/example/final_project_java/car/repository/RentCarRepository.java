package com.example.final_project_java.car.repository;

import com.example.final_project_java.car.entity.RentCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentCarRepository extends JpaRepository<RentCar, Integer> {

//    boolean existsUser (User user); // 동일 유저 찾기
//
//    boolean existsRentDate (LocalDateTime rentDate, LocalDateTime turninDate); // 동일 대여 날

    //boolean existsCar (String carId); // 대여 확인

    boolean existsByCarId(String carId);

    boolean existsByUserIdAndRentDateBetween(String userId, LocalDateTime rentDate, LocalDateTime turninDate); // 유저랑 대여 날에 중복이 있는지

//    boolean existsByCarAndRentDateBetween(Car car, LocalDateTime rentDate, LocalDateTime turninDate); // 차랑 대여 날에 중복이 있는지

    List<RentCar> findByUserId(String userId);

}















































