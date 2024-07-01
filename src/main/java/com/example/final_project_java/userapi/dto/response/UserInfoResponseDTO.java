package com.example.final_project_java.userapi.dto.response;

import com.example.final_project_java.userapi.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponseDTO {

    private String email;
    private String userName;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate birthDay;

    public UserInfoResponseDTO(User user) {
        this.email = user.getEmail();
        this.userName = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.birthDay = LocalDate.from(user.getBirthday());
    }
}
