package com.example.final_project_java.car.api;

import com.example.final_project_java.auth.TokenUserInfo;
import com.example.final_project_java.car.dto.request.CarCreateRequestDTO;
import com.example.final_project_java.car.dto.request.CarModifyRequestDTO;
import com.example.final_project_java.car.dto.response.CarDetailResponseDTO;
import com.example.final_project_java.car.dto.response.CarListResponseDTO;
import com.example.final_project_java.car.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

   private final CarService carService;

   // 전기차 추가
   @PostMapping
   public ResponseEntity<?> createCar(@AuthenticationPrincipal TokenUserInfo userInfo,
                                      @Validated @RequestBody CarCreateRequestDTO requestDTO,
                                      BindingResult result
   ) {
      log.info("/car GET! - dto: {}", requestDTO);
      log.info("TokenUserInfo: {}", userInfo);
      ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
      if (validatedResult != null) return validatedResult;

      CarListResponseDTO responseDTO = carService.create(requestDTO, String.valueOf(userInfo.getRole()));
      return ResponseEntity.ok().body(responseDTO);
   }

   // 전기차 목록 요청
   @GetMapping
   public ResponseEntity<?> getCarList() {
      CarListResponseDTO responseDTO = carService.getList();

      return ResponseEntity.ok().body(responseDTO);
   }

   // 전기차 상세보기 요청
   @GetMapping("/{id}")
   public ResponseEntity<?> retrieveCarInfo(String carId) {
      log.info("/car/{} GET request!", carId);
      CarListResponseDTO responseDTO = carService.retrieve(carId);
      return ResponseEntity.ok().body(responseDTO);
   }

   // 전기차 삭제
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal TokenUserInfo userInfo,
                                       @PathVariable("id") String carId) {
      log.info("/car/{} DELETE request!", carId);

      if (carId == null || carId.trim().equals("")) {
         return ResponseEntity.badRequest()
               .body("ID를 전달해 주세요!");
      }

      try {
         CarListResponseDTO responseDTO = carService.delete(carId, String.valueOf(userInfo.getRole()));
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }

   }

   // 전기차 수정
   @PatchMapping
   public ResponseEntity<?> updateCarInfo(@AuthenticationPrincipal TokenUserInfo userInfo,
                                          @Validated @RequestBody CarModifyRequestDTO requestDTO,
                                          BindingResult result) {
      ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
      if (validatedResult != null) return validatedResult;

      try {
         return ResponseEntity.ok().body(carService.update(requestDTO, String.valueOf(userInfo.getRole())));
      } catch (Exception e) {
         return ResponseEntity.internalServerError()
               .body(e.getMessage());
      }

   }


   // 입력값 검증(Validation)의 결과를 처리해 주는 전역 메서드
   private static ResponseEntity<List<FieldError>> getValidatedResult(BindingResult result) {
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












































