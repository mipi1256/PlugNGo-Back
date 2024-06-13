package com.example.final_project_java.car.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

import static jakarta.persistence.EnumType.STRING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "car")
public class CarEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private String carId;

   @Column(nullable = false)
   private String carName;

   @Column(nullable = false)
   private String carCompany;

   @Column(nullable = false)
   private String maximumPassenger;

   @Column(nullable = false)
   private Year carYear;

   @Column(nullable = false)
   private int carPrice;

   private String carPicture;

   @Enumerated(STRING)
   @Column(nullable = false)
   private CarOptions carOptions;


}












































