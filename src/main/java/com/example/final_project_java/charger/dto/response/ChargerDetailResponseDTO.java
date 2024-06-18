package com.example.final_project_java.charger.dto.response;

import com.example.final_project_java.charger.Entity.ChargingStation;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargerDetailResponseDTO {

    private String chargerName; // 충전소명
    private String address; // 충전소 위치
    private String chargingSpeed; // 충전 스피드 (급속 / 완속)
    private String chargerType; // 충전기 타입
    private String chargerCompany; // 충전기 회사
    private int chargingPrice; // 가격
    private String facilities; // 이용 시설
    private String availability; // 이용자 제한 / 이용 가능
    private String latitude; // 위도
    private String longitude; // 경도

    // 엔터티 -> DTO
    public ChargerDetailResponseDTO(ChargingStation station) {
        this.chargerName = station.getStationName();
        this.address = station.getAddress();
        this.chargingSpeed = station.getSpeed();
        this.chargerType = station.getChargerType();
        this.chargerCompany = station.getManagement();
        this.chargingPrice = station.getChargingPrice();
        this.facilities = station.getAreaIn();
        this.availability = station.getAvailable();
        this.latitude = station.getLatitude();
        this.longitude = station.getLongitude();
    }

}