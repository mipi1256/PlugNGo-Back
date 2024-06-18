package com.example.final_project_java.charger.service;

import com.example.final_project_java.charger.Entity.ChargingStation;
import com.example.final_project_java.charger.Entity.ReservationCharger;
import com.example.final_project_java.charger.dto.request.ReservationChargerModifyRequestDTO;
import com.example.final_project_java.charger.dto.request.ReservationChargerRequestDTO;
import com.example.final_project_java.charger.dto.response.ChargerDetailResponseDTO;
import com.example.final_project_java.charger.dto.response.ChargerListResponseDTO;
import com.example.final_project_java.charger.dto.response.ReservationChargerResponseDTO;
import com.example.final_project_java.charger.repository.ChargerRepository;
import com.example.final_project_java.charger.repository.ReservationChargerRepository;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.entity.UserId;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChargerService {

    private final UserRepository userRepository;
    private final ChargerRepository chargerRepository;
    private final ReservationChargerRepository reservationRepository;

    // 전국 전기차 충전소 목록
    public ChargerListResponseDTO retrieve() {
        List<ChargingStation> entityList = chargerRepository.findAll();

        List<ChargerDetailResponseDTO> dtoList = entityList.stream()
                .map(ChargerDetailResponseDTO::new)
                .toList();

        return ChargerListResponseDTO.builder()
                .chargers(dtoList)
                .build();
    }

    // 전기차 충전소 예약
    public ReservationChargerResponseDTO reservation(
            final ReservationChargerRequestDTO requestDTO,
            final String email,
            final int chargeId
            ) {

        User user = getUser(email);
        ChargingStation station = getChargingStation(chargeId);

        reservationRepository.save(requestDTO.toEntity(user, station));
        log.info("충전소 예약 완료!");

        return null;
    }

    // 충전소 예약 취소
    public ReservationChargerResponseDTO delete(final String chargeNo) throws Exception {
        ReservationCharger reservation = reservationRepository.findById(chargeNo).orElseThrow(
                () -> {
                    log.error("ID가 존재하지 않아 삭제에 실패하였습니다. 예약번호 : {}", chargeNo);
                    throw new RuntimeException("ID가 존재하지 않아 삭제에 실패했습니다.");
                }
        );
        reservationRepository.deleteById(chargeNo);

        return null;
    }

    // 충전소 예약 수정
    public ReservationChargerResponseDTO update(ReservationChargerModifyRequestDTO requestDTO, final String chargeNo) throws Exception {
        Optional<ReservationCharger> targetEntity = reservationRepository.findById(chargeNo);

        targetEntity.ifPresent(reservation -> {
            reservation.setRentTime(requestDTO.getRentTime());
            reservationRepository.save(reservation);
        });

        return null;
    }

    private ChargingStation getChargingStation(int chargeId) {
        ChargingStation station = chargerRepository.findById(String.valueOf(chargeId)).orElseThrow(
                () -> new RuntimeException("충전소 정보가 없습니다.")
        );
        return station;
    }

    private User getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("회원 정보가 없습니다.")
        );
        return user;
    }

}
