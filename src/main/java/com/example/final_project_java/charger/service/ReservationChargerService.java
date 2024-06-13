package com.example.final_project_java.charger.service;

import com.example.final_project_java.charger.dto.request.ReservationChargerRequestDTO;
import com.example.final_project_java.charger.dto.response.ReservationChargerResponseDTO;
import com.example.final_project_java.charger.repository.ReservationChargerRepository;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationChargerService {

    private final ReservationChargerRepository repository;
    private final UserRepository userRepository;

    public ReservationChargerResponseDTO create(final ReservationChargerRequestDTO dto,
                                                final String userId) {
//        User user =

        return null;
    }

}
