package com.example.final_project_java.charger.dto.response;

import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationChargerResponseDTO {

    // 예약 확인 -> 이름, 휴대폰번호, 예약 시간, 가격, 충전소명, 충전소 위치
    private String userName;
    private String phoneNumber;
    private int rentTime;
    private int rentChargePrice;
    private String stationName;
    private String address;

}
