package com.example.final_project_java.charger.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation_charger")
public class ReservationCharger {

    @Id
    private String chargingStationId; // 충전소 ID

    private String carId; // 차 ID

    private LocalDateTime rentTime; // 예약 시간

    private int rentChargePrice; // 가격

}