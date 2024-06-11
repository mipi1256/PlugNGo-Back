package com.example.final_project_java.userapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.Year;

import static com.example.final_project_java.userapi.entity.Role.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_user")
public class User {

   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.UUID)
   private String id;

   private String name;

   private String nickName;

   @Email
   private String email;

   private String password;

   private int phoneNumber;

   @Enumerated(EnumType.STRING)
   @Builder.Default
   private Role role = COMMON;

   @CreationTimestamp
   private LocalDateTime joinDate;

   private String profilePicture;

   private int carNumber;

   private Year birthYear;






}












































