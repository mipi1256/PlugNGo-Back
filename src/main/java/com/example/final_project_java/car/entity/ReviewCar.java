package com.example.final_project_java.car.entity;

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
   @JoinColumn(name = "user_id")
   private User userId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "car_id")
   private Car carId;

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












































