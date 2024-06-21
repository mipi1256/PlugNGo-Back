package com.example.final_project_java.car.api;

import com.example.final_project_java.auth.TokenUserInfo;
import com.example.final_project_java.car.dto.request.ReviewCarCreateRequestDTO;
import com.example.final_project_java.car.dto.request.ReviewCarModifyRequestDTO;
import com.example.final_project_java.car.dto.response.ReviewCarListResponseDTO;
import com.example.final_project_java.car.service.ReviewCarService;
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
@RequestMapping("/car/review")
public class ReviewCarController {

   private final ReviewCarService reviewCarService;

   @PostMapping
   public ResponseEntity<?> createReview(@AuthenticationPrincipal TokenUserInfo userInfo,
                                         @Validated @RequestBody ReviewCarCreateRequestDTO requestDTO,
                                         BindingResult result) {
      log.info("/car/review POST! dto: {}", requestDTO);
      log.info("TokenUserInfo: {}", userInfo);
      ResponseEntity<List<FieldError>> validateResult = getValidatedResult(result);
      if (validateResult != null) return validateResult;

      log.info(userInfo.getUserId());
      log.info(userInfo.getEmail());
      log.info(String.valueOf(userInfo.getLoginMethod()));

      try {
         ReviewCarListResponseDTO responseDTO = reviewCarService.create(requestDTO, userInfo.getUserId());
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         log.info("Review 추가 실패!");
         return ResponseEntity.internalServerError().body(e.getMessage());
      }
   }

   // Review 목록 요청
   @GetMapping("/all")
   public ResponseEntity<?> getReviewList() {
      log.info("/car/review GET! 목록 조회!");
      ReviewCarListResponseDTO responseDTO = reviewCarService.getList();

      return ResponseEntity.ok().body(responseDTO);
   }

   // Review 보기
   @GetMapping("/{id}")
   public ResponseEntity<?> retrieveReviewCarInfo(@PathVariable("id") int rCarNo) {
      log.info("/car/review/{} GET INFO REQUEST!", rCarNo);

      try {
         ReviewCarListResponseDTO responseDTO = reviewCarService.retrieve(rCarNo);
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         log.info("Review 조회 에러 발생! id: {}", rCarNo);
         return ResponseEntity.internalServerError().body(e.getMessage());
      }
   }

   // Review 삭제
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteReview(@AuthenticationPrincipal TokenUserInfo userInfo,
                                         @PathVariable("id") int rCarNo) {
      log.info("/car/review/{} DELETE Review Request!", rCarNo);

      try {
         ReviewCarListResponseDTO responseDTO = reviewCarService.delete(rCarNo, userInfo.getUserId());
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }

   }

   // Review 수정
   @PatchMapping("/{id}")
   public ResponseEntity<?> updateReviewInfo(@AuthenticationPrincipal TokenUserInfo userInfo,
                                             @Validated @RequestBody ReviewCarModifyRequestDTO requestDTO,
                                             BindingResult result) {
      log.info("/car/review Update Review Request!");
      log.info("/car/review UPDATE DTO: {}", requestDTO);
      ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
      if (validatedResult != null) return validatedResult;

      try {
         ReviewCarListResponseDTO responseDTO = reviewCarService.update(requestDTO, userInfo.getUserId());
         return ResponseEntity.ok().body(responseDTO);
      } catch (Exception e) {
         return ResponseEntity.internalServerError().body(e.getMessage());
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












































