package com.example.final_project_java.review.repository;

import com.example.final_project_java.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {

    Optional<Review> findByReviewNo(int reviewNo);

}