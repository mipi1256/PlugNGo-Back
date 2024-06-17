package com.example.final_project_java.userapi.dto.response;

import com.example.final_project_java.userapi.entity.LoginMethod;
import com.example.final_project_java.userapi.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.Year;

@Setter
@Getter
@ToString
public class NaverUserDTO {

    @JsonProperty("response")
    private NaverAccount naverAccount;

    @Setter @Getter
    @ToString
    public static class NaverAccount {

        private String id;

        private String name;

        private String email;

        private String nickname;

        @JsonProperty("profile_image")
        private String profileImageUrl;

        private String birthday;

        private String birthYear;

        private String mobile;

    }

    public User toEntity(String accessToken) {
        return User.builder()
                .email(this.naverAccount.email)
                .name(this.naverAccount.name)
                .nickName(this.naverAccount.nickname)
                .password("password")
                .profilePicture(this.naverAccount.profileImageUrl)
                .birthYear(this.naverAccount.birthYear == null ? Year.now() : Year.parse(this.naverAccount.birthYear))
                .phoneNumber(this.naverAccount.mobile)
                .accessToken(accessToken)
                .loginMethod(LoginMethod.NAVER)
                .build();
    }


}
