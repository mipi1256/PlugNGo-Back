package com.example.final_project_java.car.api;

import com.example.final_project_java.car.service.RentCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rentcar")
public class RentCarController {

   private final RentCarService rentCarService;

   // 예약, 수정, 삭제, 마이페이지예약내용 보기 (목록), 예약 상세보기 나중에 디자인 생각하기
   // 카 컨트롤러, 노티 컨트롤러 참고하기?

}












































