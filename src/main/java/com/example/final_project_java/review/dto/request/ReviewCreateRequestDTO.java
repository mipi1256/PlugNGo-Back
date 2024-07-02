package com.example.final_project_java.review.dto.request;

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
public class ReviewCreateRequestDTO {

    @NotBlank
    private String content;

    private String photo;

    @NotNull
    private int rating;

    public Review toEntity(User user) {
        return Review.builder()
                .content(content)
                .photo(photo)
                .rating(rating)
                .user(user)
                .build();
    }

}