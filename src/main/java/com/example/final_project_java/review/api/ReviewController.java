package com.example.final_project_java.review.api;

import com.example.final_project_java.auth.TokenUserInfo;
import com.example.final_project_java.review.dto.request.ReviewCarCreateRequestDTO;
import com.example.final_project_java.review.dto.request.ReviewChargeCreateRequestDTO;
import com.example.final_project_java.review.dto.request.ReviewModifyRequestDTO;
import com.example.final_project_java.review.dto.response.ReviewDetailResponseDTO;
import com.example.final_project_java.review.dto.response.ReviewListResponseDTO;
import com.example.final_project_java.review.service.ReviewService;
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
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 목록 요청
    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        log.info("/review/list - GET");

        ReviewListResponseDTO responseDTO = reviewService.getList();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 리뷰 상세보기 요청
    @GetMapping("/list/{no}")
    public ResponseEntity<?> retrieve(
            @PathVariable("no") int reviewNo
    ) {
        log.info("/review/list/{} - GET", reviewNo);

        try {
            ReviewDetailResponseDTO responseDTO = reviewService.detail(reviewNo);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            log.info("조회 에러 발생! no : {}", reviewNo);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // 렌트카 리뷰 등록 요청
    @PostMapping("/car")
    public ResponseEntity<?> createReview(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @Validated @RequestBody ReviewCarCreateRequestDTO requestDTO,
            BindingResult result
    ) {
        log.info("/review/car - POST, dto : {}", requestDTO);
        log.info("Email: {}", userInfo.getEmail());

        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
        if (validatedResult != null) return validatedResult;

        ReviewListResponseDTO responseDTO = reviewService.createCar(requestDTO, userInfo.getUserId(), requestDTO.getCarId());
        return ResponseEntity.ok().body(responseDTO);
    }

    // 충전소 리뷰 등록 요청
    @PostMapping("/charge")
    public ResponseEntity<?> createReview(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @Validated @RequestBody ReviewChargeCreateRequestDTO requestDTO,
            BindingResult result
    ) {
        log.info("/review - POST, dto : {}", requestDTO);

        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
        if (validatedResult != null) return validatedResult;

        ReviewListResponseDTO responseDTO = reviewService.createCharge(requestDTO, userInfo.getEmail(), requestDTO.getStationId());
        return ResponseEntity.ok().body(responseDTO);
    }

    // 리뷰 삭제 요청
    @DeleteMapping("/{no}")
    public ResponseEntity<?> deleteReview(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @PathVariable("no") int reviewNo
    ) {
        log.info("/review/{} - DELETE", reviewNo);
        log.info("userInfo - {}", userInfo);

        try {
            ReviewListResponseDTO responseDTO = reviewService.delete(reviewNo, userInfo.getEmail());
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 리뷰 수정 요청
    @PatchMapping("/{no}")
    public ResponseEntity<?> updateReview(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @Validated @RequestBody ReviewModifyRequestDTO requestDTO,
            @PathVariable("no") int reviewNo,
            BindingResult result
    ) {
        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
        if (validatedResult != null) return validatedResult;

        log.info("/review/{} - PATCH", reviewNo);

        try {
            return ResponseEntity.ok().body(reviewService.update(reviewNo, requestDTO, userInfo.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // 입력값 검증(Validation)의 결과를 처리해 주는 전역 메서드
    private static ResponseEntity<List<FieldError>> getValidatedResult(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(err -> {
                log.warn("invalid client data - {}", err.toString());
            });
            return ResponseEntity.badRequest().body(fieldErrors);
        }
        return null;
    }

}