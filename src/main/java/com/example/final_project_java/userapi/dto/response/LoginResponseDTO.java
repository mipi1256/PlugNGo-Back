package com.example.final_project_java.userapi.dto.response;

import com.example.final_project_java.userapi.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private String email;

    private String userName;

    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate joinDate;

    private Map<String, String> token; // 인증 토큰 (핵심)

    private String role; // 권한

    public LoginResponseDTO(User user, Map<String, String> token) {
        this.email = user.getEmail();
        this.userName = user.getName();
        this.joinDate = LocalDate.from(user.getJoinDate()); // LocalDateTime 타입이 다르면 from을 이용해서 넣어라!
        this.token = token;
        this.role = String.valueOf(user.getRole());
    }

}
