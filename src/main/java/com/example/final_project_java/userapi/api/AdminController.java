package com.example.final_project_java.userapi.api;

import com.example.final_project_java.charger.dto.response.ReservationChargerResponseDTO;
import com.example.final_project_java.userapi.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MyPageService myPageService;

    @GetMapping
    public ResponseEntity<?> myReserveStation() {
        log.info("/admin : GET!");

        List<ReservationChargerResponseDTO> responseDTO = myPageService.myReservedStation();
        return ResponseEntity.ok().body(responseDTO);
    }
}
