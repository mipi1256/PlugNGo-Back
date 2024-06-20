package com.example.final_project_java.car.dto.request;

import com.example.final_project_java.car.entity.ReviewCar;
import com.example.final_project_java.userapi.entity.User;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ReviewCarCreateRequestDTO {

   private String rentCarPicture;

   @NotNull
   @Min(value = 1)
   @Max(value = 5)
   private int rating;

   @NotBlank
   private String title;

   @NotBlank
   private String carContent;

   public ReviewCar toEntity(User user) {
      return ReviewCar.builder()
            .user(user)
            .rentCarPicture(rentCarPicture)
            .rating(rating)
            .title(title)
            .carContent(carContent)
            .build();
   }


}












































