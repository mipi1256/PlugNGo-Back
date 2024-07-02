package com.example.final_project_java.userapi.service;

import com.example.final_project_java.charger.Entity.ReservationCharger;
import com.example.final_project_java.charger.dto.response.ReservationChargerResponseDTO;
import com.example.final_project_java.charger.repository.ReservationChargerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    private final ReservationChargerRepository reservationRepository;

    public List<ReservationChargerResponseDTO> myReservedStation() {
        List<ReservationCharger> entityList = reservationRepository.findAll();

        return entityList.stream()
                .map(ReservationChargerResponseDTO::new)
                .toList();
    }

    public void cancelStationReservation(Integer reservationNo) {
        reservationRepository.deleteByReservationNo(reservationNo);
        log.info("예약번호 '{}' 예약 취소됨.", reservationNo);
    }


}