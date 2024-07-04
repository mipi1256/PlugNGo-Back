package com.example.final_project_java.car.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Time;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentCarResModifyRequestDTO {

    @NotBlank
    private int carNo; // 렌트 pk

    private Time rentTime; // 픽업시간

    private Time turninTime; // 반납시간

//    private String extra; // 비고 (에러 수정하고 꼭 추가하기!!!!!!!!)

}
