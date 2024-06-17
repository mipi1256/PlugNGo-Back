package com.example.final_project_java.charger.Entity;

import com.example.final_project_java.userapi.entity.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chargeNo; // 예약 순서

//    @GeneratedValue(strategy = GenerationType.UUID)
    private String reservationNo; // 예약 번호

    private LocalDateTime rentTime; // 예약 날짜와 시간

    private int rentChargePrice; // 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 회원 ID, 핸드폰 번호

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ChargingStation station; // 충전소 ID

}