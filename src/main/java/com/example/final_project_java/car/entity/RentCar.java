package com.example.final_project_java.car.entity;

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
   private int carNo; // 렌트 순서(pk)

   @Builder.Default
   private int reservationNo  = (int)(Math.random() * 89999) + 100000; // 예약 번호

   private String userId; // 유저 id

   private String userName;

   private LocalDate birthday;

   private String email;

   private boolean reservationOK; // 원하는 날짜에 예약 가능한지 표시용

   private String carId; // 카 id

   private String carName;

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

   private String extra; // 비고


}












































