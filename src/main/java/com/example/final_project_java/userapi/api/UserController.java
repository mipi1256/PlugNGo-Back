package com.example.final_project_java.userapi.api;

import com.example.final_project_java.userapi.dto.request.GoogleLoginRequestDTO;
import com.example.final_project_java.userapi.dto.request.UserSignUpRequestDTO;
import com.example.final_project_java.userapi.dto.response.GoogleLoginResponseDTO;
import com.example.final_project_java.userapi.dto.response.LoginResponseDTO;
import com.example.final_project_java.userapi.dto.response.UserSignUpResponseDTO;
import com.example.final_project_java.userapi.service.GoogleService;
import com.example.final_project_java.userapi.service.KakaoService;
import com.example.final_project_java.userapi.service.NaverService;
import com.example.final_project_java.userapi.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

   private final UserService userService;

   private final KakaoService kakaoService;
   private final NaverService naverService;


   private final GoogleService googleService;


   // 카카오 로그인
   @GetMapping("/kakaoLogin")
   public ResponseEntity<?> kakaoLogin(String code) {
      log.info("/api/auth/kakoLogin - GET code: {}", code);
      LoginResponseDTO responseDTO = kakaoService.kakaoService(code);

      return ResponseEntity.ok().body(responseDTO);
   }
  
   // 네이버 로그인
   @GetMapping("/naverLogin")
   public ResponseEntity<?> naverLogin(String code, String state) {
      log.info("/api/auth/kakoLogin - GET code: {}, state: {}", code, state);
      LoginResponseDTO responseDTO = naverService.naverService(code, state);

      return ResponseEntity.ok().body(responseDTO);
   }


   @PostMapping("/googleLogin")
   public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequestDTO request) {
      log.info("/api/auth/googleLogin - CODE: {}", request.getCode());
      GoogleLoginResponseDTO responseDTO = googleService.googleService(request.getCode());

      return ResponseEntity.ok().body(responseDTO);

   }

   @PostMapping
   public ResponseEntity<?> signUp(
         @Validated @RequestPart("user") UserSignUpRequestDTO dto,
         @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
         BindingResult result
   ) {
      log.info("/api/auth POST! - {}", dto);

      ResponseEntity<FieldError> resultEntity = getFieldErrorResponseEntity(result);
      if (resultEntity != null) return resultEntity;

      try {
         String uploadedFilePath = null;
         if (profileImage != null) {
            log.info("attached file name: {}", profileImage.getOriginalFilename());
            // 전달받은 프로필 이미지를 먼저 지정된 경로에 저장한 후 저장 경로를 DB에 세팅하자.
            uploadedFilePath = userService.uploadProfileImage(profileImage);
         }

         UserSignUpResponseDTO responseDTO = userService.create(dto, uploadedFilePath);
         return ResponseEntity.ok().body(responseDTO);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }



   private static ResponseEntity<FieldError> getFieldErrorResponseEntity(BindingResult result) {
      if (result.hasErrors()) {
         log.warn(result.toString());
         return ResponseEntity.badRequest()
               .body(result.getFieldError());
      }
      return null;
   }








}












































