package com.example.final_project_java.car.api;

import com.example.final_project_java.auth.TokenUserInfo;
import com.example.final_project_java.car.dto.request.RentCarRequestDTO;
import com.example.final_project_java.car.dto.request.RentCarResModifyRequestDTO;
import com.example.final_project_java.car.dto.response.RentCarDetailResponseDTO;
import com.example.final_project_java.car.dto.response.RentCarListResponseDTO;
import com.example.final_project_java.car.service.RentCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rentcar")
public class RentCarController {

   private final RentCarService rentCarService;

   // 예약, 수정, 삭제, 마이페이지예약내용 보기 (목록), 예약 상세보기

   // 전기차 목록 요청 (관리자가 모든 예약 확인)
   @GetMapping("/reslist")
   public ResponseEntity<?> getList() {
      log.info("/car GET! 목록 조회!!!");
      RentCarListResponseDTO responseDTO = rentCarService.getList();

      return ResponseEntity.ok().body(responseDTO);
   }


   // 예약하기
   @PostMapping("/reservation")
   public ResponseEntity<?> reservationRentCar(
           @AuthenticationPrincipal TokenUserInfo userInfo,
           @Validated @RequestBody RentCarRequestDTO requestDTO,
           BindingResult result
   ) {
      log.info("/rentCar/reservation : POST - dto : {}", requestDTO);
      log.info("TokenUserInfo : {}", userInfo);
      log.info("carInfo - {}", requestDTO.getCarName());

      ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
      if (validatedResult != null) return validatedResult;

      RentCarDetailResponseDTO responseDTO = rentCarService.reservation(
              requestDTO, userInfo.getEmail(),
              requestDTO.getCarId(), requestDTO.getCarName(),
              requestDTO.getRentDate(), requestDTO.getTurninDate(),
              requestDTO.getRentTime(), requestDTO.getTurninTime());
      return ResponseEntity.ok().body(responseDTO);
   }


   // 유저 예약 목록 보기 요청
   @GetMapping("/resInfo/user/{userId}")
   public ResponseEntity<?> getRentReservation(@PathVariable("userId") String userId) {
      log.info("/resInfo/{} GET! 유저 예약 목록 조회!", userId);
      RentCarListResponseDTO responseDTO = rentCarService.getRentListByUser(userId);
      log.info("responseDTO: {}" ,responseDTO);

      return ResponseEntity.ok().body(responseDTO);
   }


   // 전기차 예약 상세보기 요청
   @GetMapping("/resInfo/car/{carNo}")
   public ResponseEntity<?> rentReservationInfo (@PathVariable("carNo") int carNo) {
      log.info("/resInfo/{} GET request!", carNo);
      try {
         RentCarListResponseDTO responseDTO = rentCarService.reservationInfo(carNo);
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         log.info("조회 에러 발생! id: {}",carNo);
         return ResponseEntity.internalServerError().body(e.getMessage());
      }
   }

   // 예약 삭제하기
   @DeleteMapping("/delete/{carNo}")
   public ResponseEntity<?> deleteRentCar(@AuthenticationPrincipal TokenUserInfo userInfo,
           @PathVariable int carNo) {

      log.info("/rentcar/delete/{} DELETE!", carNo);

      try {
         RentCarListResponseDTO responseDTO = rentCarService.delete(carNo, userInfo.getEmail());
         return ResponseEntity.ok(responseDTO);
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }

   }


   // 예약 수정하기 (픽업/반납 시간)
   @PatchMapping("/{carNo}")
   public ResponseEntity<?> updateResInfo(@AuthenticationPrincipal TokenUserInfo userInfo,
                                          @PathVariable("carNo") int carNo,
                                          @Validated @RequestBody RentCarResModifyRequestDTO requestDTO,

                                          BindingResult result
                                          ) {
      log.info("/car PATCH!! 수정");
      log.info("/requestDTO: {}", requestDTO);
      log.info("requestDTO rentDate: {}", requestDTO.getUpdateRentDate()); // 픽업날짜 수정
      log.info("requestDTO rentTime: {}", requestDTO.getRentTime()); // 픽업시간 수정
      log.info("requestDTO TurninDate: {}", requestDTO.getUpdateTurninDate()); // 반납날짜 수정
      log.info("requestDTO turninTime: {}", requestDTO.getTurninTime()); // 반납시간 수정
      log.info("requestDTO extra: {}", requestDTO.getExtra()); // 비고 수정

      // 요청 데이터 검증 결과 처리
      ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
      if (validatedResult != null) return validatedResult;

      try {
         // 서비스 메서드 호출
         RentCarListResponseDTO responseDTO = rentCarService.update(requestDTO, carNo, userInfo.getEmail());
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.internalServerError()
                 .body(e.getMessage());
      }

   }

   // 해당 차의 예약 날짜들을 가져온다.
   @GetMapping("/{carId}")
   public ResponseEntity<List<LocalDate>> getReservedDatesForCar(@PathVariable("carId") String carId){
      List<LocalDate> searchedDate = rentCarService.searchDate(carId);

      return ResponseEntity.ok().body(searchedDate);
   }

   // 입력값 검증(Validation)의 결과를 처리해 주는 전역 메서드
   public static ResponseEntity<List<FieldError>> getValidatedResult(BindingResult result) {
      if (result.hasErrors()) { // 입력값 검증 단계에서 문제가 있었다면 true
         List<FieldError> fieldErrors = result.getFieldErrors();
         fieldErrors.forEach(err -> {
            log.warn("invalid client data - {}", err.toString());
         });
         return ResponseEntity
                 .badRequest()
                 .body(fieldErrors);
      }
      return null;
   }

}












































