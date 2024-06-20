package com.example.final_project_java.car.service;

import com.example.final_project_java.car.dto.request.ReviewCarCreateRequestDTO;
import com.example.final_project_java.car.dto.request.ReviewCarModifyRequestDTO;
import com.example.final_project_java.car.dto.response.ReviewCarDetailResponseDTO;
import com.example.final_project_java.car.dto.response.ReviewCarListResponseDTO;
import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.entity.ReviewCar;
import com.example.final_project_java.car.repository.CarRepository;
import com.example.final_project_java.car.repository.ReviewCarRepository;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReviewCarService {

   private final ReviewCarRepository reviewCarRepository;
   private final CarRepository carRepository;
   private final UserRepository userRepository;

   public ReviewCarListResponseDTO create(final ReviewCarCreateRequestDTO requestDTO, final String userId) {
      User user = getUser(userId);

      reviewCarRepository.save(requestDTO.toEntity(user));
      log.info("할 일 추가 완료! title: {}", requestDTO.getTitle());
      log.info("할 일 추가 완료! content: {}", requestDTO.getCarContent());
      log.info("할 일 추가 완료! rating: {}", requestDTO.getRating());

      return getList();

   }

   public ReviewCarListResponseDTO getList() {
      List<ReviewCar> entityList = reviewCarRepository.findAll();

      List<ReviewCarDetailResponseDTO> dtoList = entityList.stream()
            .map(ReviewCarDetailResponseDTO::new)
            .collect(Collectors.toList());

      return ReviewCarListResponseDTO.builder()
            .reviewList(dtoList)
            .build();

   }

   public ReviewCarListResponseDTO delete(final int rCarNo, final String userId) {
      ReviewCar reviewCar = reviewCarRepository.findById(rCarNo).orElseThrow(
            () -> {
               log.error("id가 존재하지 않습니다! ID: {}", rCarNo);
               throw new RuntimeException("id가 존재하지 않아 삭제에 실패했습니다");
            }
      );
      reviewCarRepository.deleteById(rCarNo);
      return getList();
   }

   public ReviewCarListResponseDTO update(final ReviewCarModifyRequestDTO requestDTO, final String userId) {
      ReviewCar reviewCar = reviewCarRepository.findById(requestDTO.getRCarNo()).orElseThrow(
            () -> {
               log.warn("수정할 댓글 없습니다!");
               throw new RuntimeException("수정할 수 없습니다.");
            }
      );

      reviewCar.setRentCarPicture(requestDTO.getRentCarPicture());
      reviewCar.setRating(requestDTO.getRating());
      reviewCar.setTitle(requestDTO.getTitle());
      reviewCar.setCarContent(requestDTO.getCarContent());

      reviewCarRepository.save(reviewCar);

      return getList();

   }

   // Review 보기
   public ReviewCarListResponseDTO retrieve(int rCarNo) {
      ReviewCar reviewCar = reviewCarRepository.findById(rCarNo).orElseThrow(
            () -> {
               log.info("ID 없습니다, ID : {}", rCarNo);
               throw new RuntimeException("Reviewiwth the given ID not Found");
            }
      );

      ReviewCarDetailResponseDTO reviewCarDetailResponseDTO = new ReviewCarDetailResponseDTO(reviewCar);

      List<ReviewCarDetailResponseDTO> dtoList = Collections.singletonList(reviewCarDetailResponseDTO);

      return ReviewCarListResponseDTO.builder()
            .reviewList(dtoList)
            .build();

   }


   // 전기차 아이디 통해서 불러오기
   private Car getCar(String carId) {
      return carRepository.findById(carId).orElseThrow(
            () -> new RuntimeException("조회 차 없습니다" + carId)
      );
   }


   // 사용자 Role 통해서 정보 불러오기
   private User getUser(String userId) {
      User user = userRepository.findById(userId).orElseThrow(
            () -> new RuntimeException("회원 정보가 없습니다.")
      );
      return user;
   }


}












































