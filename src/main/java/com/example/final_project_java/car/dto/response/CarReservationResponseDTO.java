package com.example.final_project_java.car.dto.response;

import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.entity.RentCar;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter @Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarReservationResponseDTO {

    // 전기차 예약확인-> 예약번호, 회원이름, 전화번호, 생일, 차 id, 차 이름, 픽업 날짜, 픽업 시간, 반납 날짜, 반납 시간

    private String name;
    private String phoneNumber;
    private LocalDate birthday;
    private int reservationNo; // 예약번호
    private int carNo; // 렌트 프라이머키
    private Car car; // 차 이름
    private LocalDateTime rentDate;
    private Time rentTime;
    private LocalDateTime turninDate;
    private Time turninTime;
    private int rentCarPrice;

    public CarReservationResponseDTO(RentCar rentCar) {
        this.name = rentCar.getUser().getName();
        this.phoneNumber = rentCar.getPhoneNumber();
        this.birthday = rentCar.getBirthday();
        this.reservationNo = rentCar.getReservationNo();
        this.carNo = rentCar.getCarNo();
        this.car = rentCar.getCar();
        this.rentDate = rentCar.getRentDate();
        this.rentTime = rentCar.getRentTime();
        this.turninDate = rentCar.getTurninDate();
        this.turninTime = rentCar.getTurninTime();
        this.rentCarPrice = rentCar.getRentCarPrice();

    }

}
