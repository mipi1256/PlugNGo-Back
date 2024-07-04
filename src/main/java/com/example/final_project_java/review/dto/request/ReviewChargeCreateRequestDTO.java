package com.example.final_project_java.review.dto.request;

import com.example.final_project_java.charger.Entity.ChargingStation;
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
public class ReviewChargeCreateRequestDTO {

    @NotBlank
    private String content;

    private String photo;

    @NotNull
    private int rating;

    private String stationId;

    private String email;

    public Review toEntity(User user, ChargingStation station) {
        return Review.builder()
                .content(content)
                .photo(photo)
                .rating(rating)
                .name(user.getName())
                .email(user.getEmail())
                .stationId(station.getStationId())
                .stationName(station.getStationName())
                .build();
    }

}