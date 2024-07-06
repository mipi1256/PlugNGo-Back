package com.example.final_project_java.review.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCarModifyRequestDTO {

    @NotNull
    private String content;

    private int rating;

}