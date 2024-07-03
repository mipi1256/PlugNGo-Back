package com.example.final_project_java.review.dto.request;

import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.review.entity.Review;
import com.example.final_project_java.userapi.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCarCreateRequestDTO {

    @NotBlank
    private String content;

    private String photo;

    @NotNull
    private int rating;

    private String carId;

    private String email;

    public Review toEntity(User user, Car car) {
        return Review.builder()
                .content(content)
                .photo(photo)
                .rating(rating)
                .name(user.getName())
                .email(user.getEmail())
                .carId(car.getCarId())
                .carName(car.getCarName())
                .build();
    }

}