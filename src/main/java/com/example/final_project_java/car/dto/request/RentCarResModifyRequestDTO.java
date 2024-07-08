package com.example.final_project_java.car.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentCarResModifyRequestDTO {

//    @NotBlank
    private int carNo; // 렌트 pk

    private LocalDate updateRentDate; // 픽업 날짜

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Time rentTime; // 픽업시간

    private LocalDate updateTurninDate; // 반납 날짜

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Time turninTime; // 반납시간

    private String extra; // 비고

}
