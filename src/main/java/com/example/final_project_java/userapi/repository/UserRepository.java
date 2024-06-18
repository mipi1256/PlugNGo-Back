package com.example.final_project_java.userapi.repository;

import com.example.final_project_java.userapi.entity.LoginMethod;
import com.example.final_project_java.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

   // 이메일 충복 체크
   // boolean existsByEmail(String email);   로컬 로그인 용

   // SNS 로그인 한 사람 회원가입 시킬지 말지 결정하는 메서드.
   boolean existsByEmailAndLoginMethod(String email, LoginMethod loginMethod);

   // 이메일 찾기
   Optional<User> findByEmail(String email);



}















































