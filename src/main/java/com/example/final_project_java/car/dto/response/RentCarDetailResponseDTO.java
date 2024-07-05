package com.example.final_project_java.car.dto.response;

import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.entity.RentCar;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Setter @Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentCarDetailResponseDTO {

    // 전기차 예약확인-> 예약번호, 회원이름, 전화번호, 차 이름, 픽업 날짜, 픽업 시간, 반납 날짜, 반납 시간, 비고

    private int carNo;
    private int reservationNo; // 예약번호
    private String userName;
    private String phoneNumber;
    private String carName; // 차 이름
    private LocalDateTime rentDate;
    private Time rentTime;
    private LocalDateTime turninDate;
    private Time turninTime;
    private int rentCarPrice;
    private String extra; // 비고

    public RentCarDetailResponseDTO(RentCar rentCar) {
        this.reservationNo = rentCar.getReservationNo();
        this.userName = rentCar.getUserName();
        this.phoneNumber = rentCar.getPhoneNumber();
        this.carName = rentCar.getCarName();
        this.rentDate = rentCar.getRentDate();
        this.rentTime = rentCar.getRentTime();
        this.turninDate = rentCar.getTurninDate();
        this.turninTime = rentCar.getTurninTime();
        this.rentCarPrice = rentCar.getRentCarPrice();
        this.extra = rentCar.getExtra();
    }



}
