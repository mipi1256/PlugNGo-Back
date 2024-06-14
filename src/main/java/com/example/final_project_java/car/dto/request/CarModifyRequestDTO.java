package com.example.final_project_java.car.dto.request;

import com.example.final_project_java.car.entity.CarOptions;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarModifyRequestDTO {

   private String carId;

   @NotBlank
   private String carName;

   @NotBlank
   private String carCompany;

   @NotBlank
   private int maximumPassenger;

   @NotBlank
   private Year carYear;

   @NotBlank
   private int carPrice;

   private String carPicture;

   @NotBlank
   private CarOptions carOptions;



}












































