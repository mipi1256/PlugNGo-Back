package com.example.final_project_java.car.entity;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
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

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private String userId;

   @OneToOne(fetch = FetchType.LAZY)
   private String carId;

   @CreationTimestamp
   private LocalDateTime rentDate;

   private Time rentTime;

   private LocalDateTime turninDate;

   private Time turninTime;

   private int rentCarPrice;

   private String phoneNumber;


}












































