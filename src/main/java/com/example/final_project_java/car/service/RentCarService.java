package com.example.final_project_java.car.service;

import com.example.final_project_java.car.dto.request.CarReservationRequestDTO;
import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.entity.RentCar;
import com.example.final_project_java.car.repository.CarRepository;
import com.example.final_project_java.car.repository.RentCarRepository;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RentCarService {

   private final CarRepository carRepository;
   private final UserRepository userRepository;
   private final RentCarRepository rentCarRepository;

   // 예약하기
   public void lent (
           final CarReservationRequestDTO requestDTO,
           final String email,
           final String carId,
           final LocalDateTime rentDate,
           final LocalDateTime turninDate
           ) {
      User user = getUser(email);
      Car carInfo = getCarInfo(carId);
      LocalDateTime existsRentDate = requestDTO.getRentDate();
      LocalDateTime existsTurninDate = requestDTO.getTurninDate();

      // 유저가 동일한 날짜에 다른 차를 예약하려는지 확인
      if (rentCarRepository.existsByUserAndRentDateBetween(user, rentDate, turninDate)) {
         throw new IllegalStateException("이미 예약한 차가 있습니다.");
      }

   }

   // 차 정보 가져오기
   private Car getCarInfo(String carId) {
      Car carInfo = carRepository.findById(carId).orElseThrow(
              () -> new RuntimeException("차량 정보가 없습니다.")
      );
      return carInfo;
   }

   // 회원 찾기
   private User getUser(String email) {
      User user = userRepository.findByEmail(email).orElseThrow(
              () -> new RuntimeException("회원 정보가 없습니다.")
      );
      return user;
   }


}












































