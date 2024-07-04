package com.example.final_project_java.userapi.api;

import com.example.final_project_java.auth.TokenUserInfo;
import com.example.final_project_java.charger.dto.response.ChargerListResponseDTO;
import com.example.final_project_java.charger.dto.response.ReservationChargerResponseDTO;
import com.example.final_project_java.charger.service.ChargerService;
import com.example.final_project_java.userapi.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

   private final MyPageService myPageService;

   // 예약한 충전소 목록 불러오기
   @GetMapping
   public ResponseEntity<?> myReserveStation() {
      log.info("/mypage : GET!");

      List<ReservationChargerResponseDTO> responseDTO = myPageService.myReservedStation();
      return ResponseEntity.ok().body(responseDTO);
   }

   @DeleteMapping
   public ResponseEntity<?> cancelReservation(@RequestParam Integer reservationNo) {
      log.info("/mypage?reservationNo={} : DELETE!", reservationNo);
      System.out.println(reservationNo);

      myPageService.cancelStationReservation(reservationNo);
      return ResponseEntity.ok().build();
   }

}