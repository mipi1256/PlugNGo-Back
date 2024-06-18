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
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;

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
            final String userId,
            final int chargeId
            ) {

        User user = getUser(userId);
        ChargingStation station = getChargingStaion(chargeId);

        reservationRepository.save(requestDTO.toEntity(user, station));
        log.info("충전소 예약 완료!");

        return null;
    }







    private ChargingStation getChargingStaion(int chargeId) {
        ChargingStation station = chargerRepository.findById(String.valueOf(chargeId)).orElseThrow(
                () -> new RuntimeException("충전소 정보가 없습니다.")
        );
        return station;
    }

    private User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("회원 정보가 없습니다.")
        );
        return user;
    }

//    public ReservationChargerResponseDTO delete(final int chargeNo) throws Exception {
//        ReservationCharger reservation = reservationRepository.findById().orElseThrow(
//                () -> {
//                    log.error("예약번호가 존재하지 않습니다. - chargeNo : {}", chargeNo);
//                    throw new RuntimeException("예약번호가 존재하지 않아 삭제에 실패했습니다.");
//                }
//        );
//        reservationRepository.deleteById();
//
//        return null;
//    }

//    public ReservationChargerResponseDTO update(ReservationChargerModifyRequestDTO requestDTO) throws Exception {
//        reservationRepository.fi
//
//    }
}
