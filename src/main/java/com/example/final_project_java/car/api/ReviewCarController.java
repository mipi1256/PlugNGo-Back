package com.example.final_project_java.car.api;

import com.example.final_project_java.car.entity.ReviewCar;
import com.example.final_project_java.car.service.ReviewCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/car/review")
public class ReviewCarController {

   private final ReviewCarService reviewCarService;

}












































