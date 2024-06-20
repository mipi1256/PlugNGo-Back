package com.example.final_project_java.car.repository;

import com.example.final_project_java.car.entity.Car;
import com.example.final_project_java.car.entity.CarOptions;
import com.example.final_project_java.car.entity.ReviewCar;
import com.example.final_project_java.userapi.entity.LoginMethod;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class ReviewCarRepositoryTest {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private CarRepository carRepository;

   @Autowired
   private ReviewCarRepository reviewCarRepository;

   @Test
   public void userAdd() {
      User user = User.builder()
            .name("John Doe")
            .email("john.doe@example.com")
            .loginMethod(LoginMethod.GOOGLE)
            .build();
      userRepository.save(user);
   }


   @Test
   @DisplayName("Review 추가")
   void testReviewInsert() {
      // given
//      User user = User.builder()
//            .name("Review 추가")
//            .email("review.test1@example.com")
//            .loginMethod(LoginMethod.GOOGLE)
//            .build();
//      userRepository.save(user);
//
//      Car car = Car.builder()
//            .carName("testReview1")
//            .carCompany("testReview1")
//            .maximumPassenger(4)
//            .carYear(Year.of(2020))
//            .carPrice(100)
//            .carOptions(CarOptions.SAFETY)
//            .build();

      ReviewCar reviewCar = ReviewCar.builder()
            .rating(4)
            .title("review title3")
            .carContent("review content3")
            .build();

      reviewCarRepository.save(reviewCar);

      // then
   }

   @Test
   @DisplayName("Review 전체 조회")
   void findAllReviews() {
      // given

      // when
      List<ReviewCar> all = reviewCarRepository.findAll();
      // then
      assertEquals(all.size(), 3);

   }

   @Test
   @DisplayName("Review Id 통해 탐색")
   void findOneReview() {
      // given
      int id = 2;
      // when
      ReviewCar reviewCar = reviewCarRepository.findById(id).orElseThrow();

      // then
      assertEquals(reviewCar.getCarContent(), "review content1");
   }

   @Test
   @DisplayName("Review 삭제")
   void deleteReview() {
      // given
      int id = 2;
      // when
      reviewCarRepository.deleteById(id);
      // then
   }

}





































