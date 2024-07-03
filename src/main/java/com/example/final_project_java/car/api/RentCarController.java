package com.example.final_project_java.car.api;

import com.example.final_project_java.car.dto.request.RentCarResModifyRequestDTO;
import com.example.final_project_java.car.dto.response.RentCarDetailResponseDTO;
import com.example.final_project_java.car.dto.response.RentCarListResponseDTO;
import com.example.final_project_java.car.service.RentCarService;
import com.example.final_project_java.userapi.entity.UserId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rentcar")
public class RentCarController {

   private final RentCarService rentCarService;

   // 예약, 수정, 삭제, 마이페이지예약내용 보기 (목록), 예약 상세보기 나중에 디자인 생각하기
   // 카 컨트롤러, 노티 컨트롤러 참고하기?

   // 예약 목록 보기 요청
   @GetMapping("/resInfo/{userId}")
   public ResponseEntity<?> getRentReservation(@PathVariable("userId") String userId) {
      log.info("/resInfo GET! 목록 조회!");
      RentCarListResponseDTO responseDTO = rentCarService.getRentListByUser(userId);

      return ResponseEntity.ok().body(responseDTO);
   }

   // 전기차 예약 상세보기 요청
   @GetMapping("/resInfo/{carNo}")
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

   // 예약내용 삭제하기
   @DeleteMapping("/delete/{carNo}")
   public ResponseEntity<?> deleteRentCar(
           @PathVariable int carNo,
           @RequestParam String userId) {
      RentCarListResponseDTO responseDTO = rentCarService.delete(carNo, userId);
      return ResponseEntity.ok(responseDTO);
   }

   // 예약 수정하기 (픽업/반납 시간)
   @PostMapping("/{carNo}")
   public ResponseEntity<?> updateResInfo(@PathVariable("carNo") int carNo,
                                          @Validated @RequestBody RentCarResModifyRequestDTO requestDTO,
                                          BindingResult result
                                          ) {
      log.info("/car PATCH!! 수정");
      log.info("/requestDTO: {}", requestDTO);
      log.info("requestDTO rentTime: {}", requestDTO.getRentTime()); // 픽업시간 수정
      log.info("requestDTO turninTime: {}", requestDTO.getTurninTime()); // 반납시간 수정
      ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);

      if (validatedResult != null) return validatedResult;

      try {
         RentCarListResponseDTO responseDTO = rentCarService.update(requestDTO, carNo);
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.internalServerError()
                 .body(e.getMessage());
      }

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












































