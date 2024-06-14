package com.example.final_project_java.auth;

import com.example.final_project_java.userapi.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TokenProvider {

//   @Value("${jwt.secret}")
   private String SECRET_KEY;

   /**
    * JSON Web Token을 생성하는 메서드
    * @param userEntity - 토큰의 내용(클레임)에 포함될 유저 정보
    * @return - 생성된 JSON을 암호화 한 토큰값
    */
   public String createToken(User userEntity) {

      Date expiry = Date.from(
            Instant.now().plus(1, ChronoUnit.DAYS)
      );

      // 토큰 생성
      Map<String, String> claims = new HashMap<>();
      claims.put("email", userEntity.getEmail());
      claims.put("role", userEntity.getRole().toString());


      return Jwts.builder()
            // token Header
            .signWith(
                  Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),
                  SignatureAlgorithm.HS512
            )
            .setIssuer("Page운영자") // iss: 발급자 정보
            .setIssuedAt(new Date()) // iat: 발급 시간
            .setExpiration(expiry) // exp: 만료 시간
            .setSubject(userEntity.getId())
            .setClaims(claims)
            .compact();

   }

}












































