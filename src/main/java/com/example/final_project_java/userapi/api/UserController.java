package com.example.final_project_java.userapi.api;

import com.example.final_project_java.userapi.dto.response.GoogleLoginResponseDTO;
import com.example.final_project_java.userapi.dto.response.LoginResponseDTO;
import com.example.final_project_java.userapi.service.GoogleService;
import com.example.final_project_java.userapi.service.KakaoService;
import com.example.final_project_java.userapi.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;
import retrofit2.http.POST;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

   private final UserService userService;

   private final KakaoService kakaoService;

   private final GoogleService googleService;

   @GetMapping("/kakaoLogin")
   public ResponseEntity<?> kakaoLogin(String code) {
      log.info("/api/auth/kakoLogin - GET code: {}", code);
      LoginResponseDTO responseDTO = kakaoService.kakaoService(code);

      return ResponseEntity.ok().body(responseDTO);
   }

   // 구글 토큰 받을때 필요한 것
   @Value("${sns.google.login.url}")
   private String googleLogin;

   @Value("${sns.google.redirect.uri}")
   private String googleRedirectUri;

   @Value("${sns.google.client.id}")
   private String googleClientId;

   @Value("${sns.google.client.secret}")
   private String googleClientSecret;


   @GetMapping("/googleLogin")
   public ResponseEntity<?> googleLogin(String code, PasswordEncoder encoder) {
      log.info("/api/auth/googleLogin - CODE: {}", code);
      GoogleLoginResponseDTO responseDTO = googleService.googleService(code, encoder);

      return ResponseEntity.ok().body(responseDTO);

   }








}












































