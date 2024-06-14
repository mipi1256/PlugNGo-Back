package com.example.final_project_java.car.service;

import com.example.final_project_java.car.dto.request.CarCreateRequestDTO;
import com.example.final_project_java.car.dto.request.CarModifyRequestDTO;
import com.example.final_project_java.car.dto.response.CarDetailResponseDTO;
import com.example.final_project_java.car.dto.response.CarListResponseDTO;
import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.repository.CarRepository;
import com.example.final_project_java.userapi.entity.Role;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CarService {

   private final CarRepository carRepository;
   private final UserRepository userRepository;

   public CarListResponseDTO create(final CarCreateRequestDTO requestDTO, final String userId) {
      User user = getUser(userId);

      if (user.getRole() == Role.ADMIN) {
         carRepository.save(requestDTO.toEntity(user));
      }

      return retrieve(userId);

   }

   // 전기차 목록
   public CarListResponseDTO getList() {

      List<Car> entityList = carRepository.findAll();

      List<CarDetailResponseDTO> dtoList = entityList.stream()
            .map(CarDetailResponseDTO::new)
            .collect(Collectors.toList());

      return CarListResponseDTO.builder()
            .carList(dtoList)
            .build();

   }



   // 전기차 상세보기 가져오기
   public CarListResponseDTO retrieve(String carId) {

      Car car = getCar(carId);

      List<Car> entityList = carRepository.findAll();

      List<CarDetailResponseDTO> dtoList = entityList.stream()
            .map(CarDetailResponseDTO::new)
            .collect(Collectors.toList());

      return CarListResponseDTO.builder()
            .carList(dtoList)
            .build();

   }

   // 전기차 아이디 통해서 불러오기
   private Car getCar(String carId) {
      Car car = carRepository.findById(carId).orElseThrow(
            () -> new RuntimeException("전기차 정보가 없습니다.")
      );
      return car;
   }

   // 사용자 Role 통해서 정보 불러오기
   public User getUser(String userId) {
      User user = userRepository.findById(userId).orElseThrow(
            () -> new RuntimeException("회원 정보가 없습니다.")
      );
      return user;
   }

   // 전기차 삭제
   public CarListResponseDTO delete(final String carId, final String userId){

      Car car = carRepository.findById(carId).orElseThrow(
            () -> {
               log.error("id가 존재하지 않습니다! ID: {}", carId);
               throw new RuntimeException("id가 존재하지 않아 삭제에 실패했습니다.");
            }
      );
      carRepository.deleteById(carId);

      return retrieve(userId);


   }


   public CarListResponseDTO update(final CarModifyRequestDTO requestDTO, final String userId) {
      Optional<Car> targetEntity = carRepository.findById(requestDTO.getCarId());

      targetEntity.ifPresent(carRepository::save);

      return retrieve(userId);


   }
}












































