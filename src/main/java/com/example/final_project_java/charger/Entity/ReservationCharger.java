package com.example.final_project_java.charger.Entity;

import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.entity.UserId;
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

    @Builder.Default
    private int reservationNo  = (int)(Math.random() * 89999) + 100000; // 예약 번호

    private LocalDateTime rentTime; // 예약 날짜와 시간

    private int rentChargePrice; // 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "email", referencedColumnName = "email"),
            @JoinColumn(name = "login_method", referencedColumnName = "login_method")
    })
    private User user;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ChargingStation station; // 충전소 ID

}