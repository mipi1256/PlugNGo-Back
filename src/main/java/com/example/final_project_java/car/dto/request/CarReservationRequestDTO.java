package com.example.final_project_java.car.dto.request;

import com.example.final_project_java.car.dto.response.CarReservationResponseDTO;
import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.entity.RentCar;
import com.example.final_project_java.userapi.entity.User;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarReservationRequestDTO {

    // 전기차 예약하기 -> 회원이름, 전화번호, 생일, 차 id, 차 이름, 픽업 날짜, 픽업 시간, 반납 날짜, 반납 시간

    private String name;
    private String phoneNumber;
    private LocalDate birthday;
    private int carNo; // 렌트 프라이머키
    private Car car; // 차 이름
    private LocalDateTime rentDate;
    private Time rentTime;
    private LocalDateTime turninDate;
    private Time turninTime;
    private int rentCarPrice;

    public RentCar toEntity (User user) {
        return RentCar.builder()
                .user(user)
                .phoneNumber(this.phoneNumber)
                .birthday(this.birthday)
                .carNo(this.carNo)
                .car(this.car)
                .rentDate(this.rentDate)
                .rentTime(this.rentTime)
                .turninDate(this.turninDate)
                .turninTime(this.turninTime)
                .build();
    }


}
