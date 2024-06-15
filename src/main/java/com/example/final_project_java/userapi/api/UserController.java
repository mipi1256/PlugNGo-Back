package com.example.final_project_java.userapi.api;

import com.example.final_project_java.userapi.dto.response.LoginResponseDTO;
import com.example.final_project_java.userapi.service.KakaoService;
import com.example.final_project_java.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;
import retrofit2.http.POST;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

   private final UserService userService;

   private final KakaoService kakaoService;

   @GetMapping("/kakaoLogin")
   public ResponseEntity<?> kakaoLogin(String code) {
      log.info("/api/auth/kakoLogin - GET code: {}", code);
      LoginResponseDTO responseDTO = kakaoService.kakaoService(code);

      return ResponseEntity.ok().body(responseDTO);
   }









}












































