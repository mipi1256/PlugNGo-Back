package com.example.final_project_java.charger.dto.request;

import com.example.final_project_java.charger.Entity.ChargingStation;
import com.example.final_project_java.charger.Entity.ReservationCharger;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.entity.UserId;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationChargerRequestDTO {

    // 충전소 예약하기 -> 회원이름, 휴대폰번호, 충전소 ID, 시간

    private String email;
    private String phoneNumber;
    private int chargeId;
    private LocalDateTime rentTime;

    public ReservationCharger toEntity(User user, ChargingStation charge) {
        return ReservationCharger.builder()
                .user(user)
                .station(charge)
                .phoneNumber(user.getPhoneNumber())
                .rentTime(this.rentTime)
                .build();
    }

}
