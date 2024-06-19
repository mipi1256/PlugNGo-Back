package com.example.final_project_java.userapi.service;

import com.example.final_project_java.auth.TokenProvider;
import com.example.final_project_java.userapi.dto.response.GoogleLoginResponseDTO;
import com.example.final_project_java.userapi.dto.response.GoogleUserResponseDTO;
import com.example.final_project_java.userapi.entity.LoginMethod;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class GoogleService {

   public final UserRepository userRepository;
   public final TokenProvider tokenProvider;

   // 이메일 중복확인
   public boolean isDuplicate(String email) {
      if (userRepository.existsByEmailAndLoginMethod(email, LoginMethod.GOOGLE)) {
         log.info("중복된 이메일입니다. -> {}", email);
         return true;
      } else return false;
   }

   // AccessKey를 새롭게 발급받아 Map으로 포장해 주는 메서드.
   private Map<String, String> getTokenMap(User user) {
      String accessToken = tokenProvider.createAccessKey(user);

      Map<String, String> token = new HashMap<>();
      token.put("access-token", accessToken);
      return token;
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

//   @Value("${sns.google.scope}")
//   private String googleScope;

   public GoogleLoginResponseDTO googleService(String code) {
      String accessToken = getGoogleAccessToken(code);
      log.info("access_token: {}", code);

      // 토큰을 통해 사용자 정보 가져오기
      GoogleUserResponseDTO userResponseDTO = getGoogleUserInfo(accessToken);

      // 구글 로그인 이메일 중복 체크
      if (!isDuplicate(userResponseDTO.getGoogleEmail())) {
         User saved = userRepository.save(userResponseDTO.toEntity(accessToken));
      }

      User foundUser = userRepository.findByEmail(userResponseDTO.getGoogleEmail()).orElseThrow();

      Map<String, String> token = getTokenMap(foundUser);

      foundUser.changeAccessToken(accessToken);
      userRepository.save(foundUser);

      return new GoogleLoginResponseDTO(foundUser, token);

   }


   private GoogleUserResponseDTO getGoogleUserInfo(String accessToken) {

      String userInfoUri = "https://www.googleapis.com/oauth2/v3/userinfo";

      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", "Bearer " + accessToken);

      RestTemplate template = new RestTemplate();

      ResponseEntity<GoogleUserResponseDTO> responseEntity = template.exchange(
            userInfoUri,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            GoogleUserResponseDTO.class
      );

      GoogleUserResponseDTO responseJSON = responseEntity.getBody();
      if (responseJSON == null) {
         throw new RuntimeException("Failed to retrieved User info from Google");
      }
      log.info("응답 데이터 결과 : {}", responseJSON);

      return responseJSON;

   }


   private String getGoogleAccessToken(String code) {
      String decode = URLDecoder.decode(code, StandardCharsets.UTF_8);
      log.info("decode: {}", decode);

      // 요청 URI
      String requestUri = "https://oauth2.googleapis.com/token";

      // 요청 헤더 설정
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-type", "application/x-www-form-urlencoded");
//      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      // 코드를 디코드를 하기 위해 추가


      // 요청 바디에 파라미터 세팅
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("grant_type", "authorization_code");
      params.add("client_id", googleClientId);
      params.add("client_secret", googleClientSecret);
      params.add("redirect_uri", googleRedirectUri);
      params.add("code", code);

      // REST API를 호출하기 위한 RestTemplate 객체 생성
      RestTemplate template = new RestTemplate();

      // 헤더 정보와 파라미터를 하나로 묶기
      HttpEntity<Object> requestEntity = new HttpEntity<>(params, headers);

      ResponseEntity<Map> responseEntity;
      try {
         responseEntity = template.exchange(
               requestUri,
               HttpMethod.POST,
               requestEntity,
               Map.class
         );
      } catch (HttpClientErrorException e) {
         log.error("Failed to retrieve access token: " + e.getResponseBodyAsString());
         throw e; // Optionally rethrow or handle differently
      }

//            = template.exchange(requestUri, HttpMethod.POST, requestEntity, Map.class);

      // 응답 데이터에서 JSON 추출
//      Map<String, Object> responseJSON = (Map<String, Object>) responseEntity.getBody();
//      log.info("응답 JSON 데이터: {}", responseJSON);
//      // access token 추출
//      String accessToken = (String) responseJSON.get("access_token");
//      return accessToken;

      // Extract the access token from the response
      Map<String, Object> responseJSON = responseEntity.getBody();
      if (responseJSON != null && responseJSON.containsKey("access_token")) {
         return (String) responseJSON.get("access_token");
      } else {
         throw new RuntimeException("Failed to retrieve access token from Google");
      }


   }


   public void googleLogin(String code) {
      String accessToken = getGoogleAccessToken(code);
      log.info("access_token", accessToken);

      GoogleUserResponseDTO dto = getGoogleUserInfo(accessToken);

      String id = dto.getGoogleEmail() + "googleLogin";
      log.info("구글 로그인 아이디: {}", id);

      userRepository.save(User.builder()
            .email(id)
            .name(dto.getGoogleName())
            .email(dto.getGoogleEmail())
            .profilePicture(dto.getGoogleProfilePicture())
            .password(dto.getPassword())
            .loginMethod(LoginMethod.GOOGLE)
            .build());

   }

}














































