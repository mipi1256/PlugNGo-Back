package com.example.final_project_java.car.repository;

import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.entity.RentCar;
import com.example.final_project_java.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RentCarRepository extends JpaRepository<RentCar, Integer> {

//    boolean existsUser (User user); // 동일 유저 찾기
//
//    boolean existsRentDate (RentCar rentDate, RentCar turninDate); // 동일 대여 날

    boolean existsByUserAndRentDateBetween(User user, LocalDateTime rentDate, LocalDateTime turninDate); // 유저랑 대여 날에 중복이 있는지

    boolean existsByCarAndRentDateBetween(Car car, LocalDateTime rentDate, LocalDateTime turninDate); // 차랑 대여 날에 중복이 있는지

}















































