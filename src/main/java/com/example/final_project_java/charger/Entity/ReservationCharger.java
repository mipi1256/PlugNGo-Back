package com.example.final_project_java.charger.Entity;

import com.example.final_project_java.userapi.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation_charger")
public class ReservationCharger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chargeNo;

    private int rentTime; // 예약 시간 (분 단위)

    private int rentChargePrice; // 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charge_station_id")
    private ChargingStation station;

}