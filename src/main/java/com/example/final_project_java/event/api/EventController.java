package com.example.final_project_java.event.api;

import com.example.final_project_java.auth.TokenUserInfo;
import com.example.final_project_java.event.dto.request.EventCreateRequestDTO;
import com.example.final_project_java.event.dto.request.EventModifyRequestDTO;
import com.example.final_project_java.event.dto.response.EventDetailResponseDTO;
import com.example.final_project_java.event.dto.response.EventListResponseDTO;
import com.example.final_project_java.event.service.EventService;
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
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    // 이벤트 목록 요청
    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        log.info("/events/list - GET");

        EventListResponseDTO responseDTO = eventService.getList();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 이벤트 글 상세보기 요청
    @GetMapping("/{no}")
    public ResponseEntity<?> retrieve(
            @PathVariable("no") int eventNo
    ) {
        log.info("/events/list/{} - GET", eventNo);

        try {
            EventDetailResponseDTO responseDTO = eventService.detail(eventNo);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            log.info("조회 에러 발생! no : {}", eventNo);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // 이벤트 등록 요청
    @PostMapping
    public ResponseEntity<?> createEvent(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @Validated @RequestBody EventCreateRequestDTO requestDTO,
            BindingResult result
    ) {
        log.info("/events - POST , dto : {}", requestDTO);
        log.info("userInfo : {}", userInfo);
        log.info("userInfo.id : {}", userInfo.getUserId());

        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
        if (validatedResult != null) return validatedResult;

        EventListResponseDTO responseDTO = eventService.create(requestDTO, userInfo.getUserId());
        return ResponseEntity.ok().body(responseDTO);
    }

    // 이벤트 삭제 요청
    @DeleteMapping("/{no}")
    public ResponseEntity<?> deleteEvent(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @PathVariable("no") int eventNo
    ) {
        log.info("/events/{} - DELETE", eventNo);

        try {
            EventListResponseDTO responseDTO = eventService.delete(eventNo, userInfo.getUserId());
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 이벤트 수정 요청
    @PatchMapping("/{no}")
    public ResponseEntity<?> updateEvent(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @Validated @RequestBody EventModifyRequestDTO requestDTO,
            @PathVariable("no") int eventNo,
            BindingResult result
    ) {
        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
        if (validatedResult != null) return validatedResult;

        log.info("/events/{} - PATCH", eventNo);

        try {
            return ResponseEntity.ok().body(eventService.update(eventNo, requestDTO, userInfo.getUserId()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
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