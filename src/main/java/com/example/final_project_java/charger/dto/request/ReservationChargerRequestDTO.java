package com.example.final_project_java.charger.dto.request;

import com.example.final_project_java.charger.Entity.ReservationCharger;
import com.example.final_project_java.userapi.entity.User;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationChargerRequestDTO {

    // 충전소 예약하기 -> 회원이름, 휴대폰번호, 충전소 ID, 차량 ID, 시간(분 단위)

    private String userId;
    private String phoneNumber;
    private String StationId;
    private String carId;
    private int rentTime;

    public ReservationCharger toEntity(User user) {
        return ReservationCharger.builder()
                .user(user)
                .rentTime(rentTime)
                .build();
    }

}
