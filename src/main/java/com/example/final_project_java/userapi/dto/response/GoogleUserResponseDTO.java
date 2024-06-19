package com.example.final_project_java.userapi.dto.response;

import com.example.final_project_java.userapi.entity.LoginMethod;
import com.example.final_project_java.userapi.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Setter @Getter
@ToString
public class GoogleUserResponseDTO {

   String googleEmail;
   String googleName;
   String googleProfilePicture;

   @JsonProperty("connected_at")
   private LocalDateTime connectedAt;

   private String password = "googlePassword123!";

   public User toEntity(String accessToken) {
      return User.builder()
            .email(googleEmail)
            .name(googleName)
            .password(password)
            .profilePicture(googleProfilePicture)
            .loginMethod(LoginMethod.GOOGLE)
            .accessToken(accessToken)
            .build();
   }


}












































