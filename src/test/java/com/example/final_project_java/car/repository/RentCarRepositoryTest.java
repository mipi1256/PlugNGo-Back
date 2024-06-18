package com.example.final_project_java.car.repository;

import com.example.final_project_java.car.entity.RentCar;
import com.example.final_project_java.userapi.entity.LoginMethod;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class RentCarRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentCarRepository rentCarRepository;

    @Test
    public void testRentCarUserRelationship() {
        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .loginMethod(LoginMethod.GOOGLE)
                .build();
        userRepository.save(user);

        RentCar rentCar = RentCar.builder()
                .user(user)
                .rentDate(LocalDateTime.now())
                .rentTime(Time.valueOf("10:00:00"))
                .turninDate(LocalDateTime.now().plusDays(1))
                .turninTime(Time.valueOf("10:00:00"))
                .rentCarPrice(100)
                .phoneNumber("123-456-7890")
                .build();
        rentCarRepository.save(rentCar);
    }


}