package com.example.final_project_java.car.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCarModifyRequestDTO {

   private int rCarNo;

   private String rentCarPicture;

   private int rating;

   private String title;

   private String carContent;

   private LocalDateTime updateDate;

}












































