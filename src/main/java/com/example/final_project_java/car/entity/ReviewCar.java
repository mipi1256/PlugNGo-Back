package com.example.final_project_java.car.entity;

import com.example.final_project_java.charger.Entity.ChargingStation;
import com.example.final_project_java.userapi.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "review_car")
public class ReviewCar {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int rCarNo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumns({
           @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
           @JoinColumn(name = "email", referencedColumnName = "email"),
           @JoinColumn(name = "login_method", referencedColumnName = "login_method")
   })
   private User user;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "car_id")
   private Car car;

   @ManyToOne
   @JoinColumn(name = "charge_id")
   private ChargingStation chargingStation;

   private String rentCarPicture;

   @Column(nullable = false)
   private int rating;

   @Column(nullable = false)
   private String title;

   @Column(nullable = false)
   private String carContent;

   @CreationTimestamp
   private LocalDateTime contentDate;

   @UpdateTimestamp
   private LocalDateTime updateDate;






}












































