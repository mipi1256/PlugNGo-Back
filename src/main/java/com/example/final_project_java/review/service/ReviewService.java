package com.example.final_project_java.review.service;

import com.example.final_project_java.review.dto.request.ReviewCreateRequestDTO;
import com.example.final_project_java.review.dto.request.ReviewModifyRequestDTO;
import com.example.final_project_java.review.dto.response.ReviewDetailResponseDTO;
import com.example.final_project_java.review.dto.response.ReviewListResponseDTO;
import com.example.final_project_java.review.entity.Review;
import com.example.final_project_java.review.repository.ReviewRepository;
import com.example.final_project_java.userapi.entity.Role;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewListResponseDTO getList() {
        List<Review> entityList = reviewRepository.findAll();

        List<ReviewDetailResponseDTO> dtoList = entityList.stream()
                .map(ReviewDetailResponseDTO::new)
                .toList();

        return ReviewListResponseDTO.builder()
                .reviews(dtoList)
                .build();
    }

    public ReviewDetailResponseDTO detail(int reviewNo) {
        Review review = reviewRepository.findByReviewNo(reviewNo).orElseThrow(
                () -> {
                    log.warn("글 번호가 존재하지 않아 조회되지 않습니다. - no : {}", reviewNo);
                    throw new RuntimeException("글 번호가 존재하지 않아 조회되지 않습니다.");
                }
        );
        return new ReviewDetailResponseDTO(review);
    }

    public ReviewListResponseDTO create(
            final ReviewCreateRequestDTO requestDTO,
            final String userId) {
        User foundUser = userRepository.findUserByUserIdOnly(userId).orElseThrow();
        log.info("조회한 user: {}", foundUser.toString());

        Review review = requestDTO.toEntity(foundUser);
        log.info("완성된 Review: {}", review);

        reviewRepository.save(review);
        log.info("리뷰 작성 완료!");

        return getList();
    }

    public ReviewListResponseDTO delete(final int reviewNo, final String userId) throws Exception {
        Optional<User> user = getUser(userId);

        // 관리자와 작성자만 글을 삭제할 수 있게 처리
        if (user.get().getRole() != Role.ADMIN || user.get().getId() != userId) {
            log.warn("권한이 없습니다.");
            throw new RuntimeException("권한이 없습니다.");
        }

        Review review = reviewRepository.findByReviewNo(reviewNo).orElseThrow(
                () -> {
                    log.error("글 번호가 존재하지 않아 삭제에 실패했습니다. - no : {}", reviewNo);
                    throw new RuntimeException("글 번호가 존재하지 않아 삭제에 실패했습니다.");
                }
        );
        reviewRepository.deleteById(String.valueOf(reviewNo));

        return getList();
    }

    public ReviewListResponseDTO update(final int reviewNo, final ReviewModifyRequestDTO requestDTO, final String userId) throws Exception {
        Optional<User> user = getUser(userId);

        Optional<Review> targetEntity = reviewRepository.findByReviewNo(reviewNo);

        targetEntity.ifPresent(review -> {
            review.setContent(requestDTO.getContent());
            review.setPhoto(requestDTO.getPhoto());
            review.setRating(requestDTO.getRating());
        });

        return getList();
    }

    private Optional<User> getUser(String userId) {
        return userRepository.findUserByUserIdOnly(userId);
    }

}