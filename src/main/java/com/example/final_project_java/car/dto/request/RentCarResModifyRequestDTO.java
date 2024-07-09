package com.example.final_project_java.car.dto.request;

import com.example.final_project_java.car.entity.RentCar;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

//    private LocalDate updateRentDate; // 픽업 날짜

    private LocalDateTime rentTime; // 픽업시간

//    private LocalDate updateTurninDate; // 반납 날짜

    private LocalDateTime turninTime; // 반납시간

    private String extra; // 비고

    public RentCar toEntity() {
        return RentCar.builder()
//                .rentDate(updateRentDate)
                .rentTime(rentTime)
//                .turninDate(updateTurninDate)
                .turninTime(turninTime)
                .build();
    }

}
