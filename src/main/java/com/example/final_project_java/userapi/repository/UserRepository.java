package com.example.final_project_java.userapi.repository;

import com.example.final_project_java.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

   // 이메일 충복 체크
   boolean existsByEmail(String email);

   // 이메일 찾기
   Optional<User> findByEmail(String email);



}















































