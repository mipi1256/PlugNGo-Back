package com.example.final_project_java.review.dto.response;

import com.example.final_project_java.review.entity.Review;
import com.example.final_project_java.userapi.entity.User;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDetailResponseDTO {

    private int reviewNo;
    private String content;
    private String photo;
//    private String name;

    public ReviewDetailResponseDTO(Review review) {
        this.reviewNo = review.getReviewNo();
        this.content = review.getContent();
        this.photo = review.getPhoto();
//        this.name = user.getName();
    }

}