package com.example.final_project_java.car.dto.response;

import com.example.final_project_java.car.entity.ReviewCar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCarDetailResponseDTO {

   private int rCarNo;

   private String userId;

   private String carId;

   private String rentCarPicture;

   private int rating;

   private String title;

   private String carContent;

   private LocalDateTime contentDate;
   
   private LocalDateTime updateDate;

   public ReviewCarDetailResponseDTO(ReviewCar reviewCar) {
      this.rCarNo = reviewCar.getRCarNo();
      this.userId = reviewCar.getUser().getId();
      this.carId = reviewCar.getCar().getCarId();
      this.rentCarPicture = reviewCar.getRentCarPicture();
      this.rating = reviewCar.getRating();
      this.title = reviewCar.getTitle();
      this.carContent = reviewCar.getCarContent();
      this.updateDate = reviewCar.getContentDate();
      this.updateDate = reviewCar.getUpdateDate();
   }



}












































