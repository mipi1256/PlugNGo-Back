package com.example.final_project_java.userapi.service;


import com.example.final_project_java.auth.TokenProvider;
import com.example.final_project_java.userapi.dto.request.UserSignUpRequestDTO;
import com.example.final_project_java.userapi.dto.response.UserSignUpResponseDTO;
import com.example.final_project_java.userapi.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private TokenProvider tokenProvider;

   @Value("${upload.path}")
   private String uploadRootPath;

   public boolean isDuplicateByEmail(String email) {
      if (userRepository.existsByEmail(email)) {
         log.warn("이메일이 중복되었습니다. - {}", email);
         return true;
      } else return false;
   }

   public UserSignUpResponseDTO create (final UserSignUpRequestDTO dto, final String uploadRootPath) {
      return null;
   }


   public String uploadProfileImage(MultipartFile profileImage) {
      return null;
   }
}












































