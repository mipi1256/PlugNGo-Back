package com.example.final_project_java.car.entity;

import com.example.final_project_java.userapi.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "rent_car")
public class RentCar {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int carNo;

   @Builder.Default
   private int reservationNo  = (int)(Math.random() * 89999) + 100000; // 예약 번호

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumns({
           @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
           @JoinColumn(name = "email", referencedColumnName = "email"),
           @JoinColumn(name = "login_method", referencedColumnName = "login_method")
   })
   private User user;

   private LocalDate birthday;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "car_id")
   private Car car;

   @Column(nullable = false)
   private LocalDateTime rentDate;

   @Column(nullable = false)
   private Time rentTime;

   @Column(nullable = false)
   private LocalDateTime turninDate;

   @Column(nullable = false)
   private Time turninTime;

   @Column(nullable = false)
   private int rentCarPrice;

   @Column(nullable = false)
   private String phoneNumber;


}












































