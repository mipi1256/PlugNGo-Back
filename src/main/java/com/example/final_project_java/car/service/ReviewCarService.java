package com.example.final_project_java.car.service;

import com.example.final_project_java.car.repository.ReviewCarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReviewCarService {

   private final ReviewCarRepository reviewCarRepository;

}












































