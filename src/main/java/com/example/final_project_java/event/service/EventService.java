package com.example.final_project_java.event.service;

import com.example.final_project_java.event.Entity.Event;
import com.example.final_project_java.event.dto.request.EventCreateRequestDTO;
import com.example.final_project_java.event.dto.request.EventModifyRequestDTO;
import com.example.final_project_java.event.dto.response.EventDetailResponseDTO;
import com.example.final_project_java.event.dto.response.EventListResponseDTO;
import com.example.final_project_java.event.repository.EventRepository;
import com.example.final_project_java.userapi.entity.Role;
import com.example.final_project_java.userapi.entity.User;
import com.example.final_project_java.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    
    // 이벤트 목록 가져오기
    public EventListResponseDTO getList() {
        List<Event> entityList = eventRepository.findAll();

        List<EventDetailResponseDTO> dtoList = entityList.stream()
                .map(EventDetailResponseDTO::new)
                .toList();

        return EventListResponseDTO.builder()
                .events(dtoList)
                .build();
    }

    public EventListResponseDTO create(
            final EventCreateRequestDTO requestDTO,
            final String userId) {
        Optional<User> user = getUser(userId);

        // 관리자만 글을 쓸 수 있게 처리
        if (user.get().getRole() != Role.ADMIN) {
            log.warn("권한이 없습니다.");
            throw new RuntimeException("권한이 없습니다.");
        }

        eventRepository.save(requestDTO.toEntity());
        log.info("이벤트 저장 완료! 제목 : {}", requestDTO.getTitle());

        return getList();
    }

    public EventListResponseDTO delete(final int eventNo, final String userId) throws Exception {
        Optional<User> user = getUser(userId);

        // 관리자만 글을 삭제할 수 있게 처리
        if (user.get().getRole() != Role.ADMIN) {
            log.warn("권한이 없습니다.");
            throw new RuntimeException("권한이 없습니다.");
        }

        Event event = eventRepository.findByEventNo(eventNo).orElseThrow(
                () -> {
                    log.error("글 번호가 존재하지 않아 삭제에 실패했습니다. - no : {}", eventNo);
                    throw new RuntimeException("글 번호가 존재하지 않아 삭제에 실패했습니다.");
                }
        );
        eventRepository.deleteById(String.valueOf(eventNo));

        return getList();
    }

    public EventListResponseDTO update(final int eventNo, final EventModifyRequestDTO requestDTO, final String userId) throws Exception {
        Optional<User> user = getUser(userId);

        // 관리자만 글을 수정할 수 있게 처리
        if (user.get().getRole() != Role.ADMIN) {
            log.warn("권한이 없습니다.");
            throw new RuntimeException("권한이 없습니다.");
        }

        Optional<Event> targetEntity = eventRepository.findByEventNo(eventNo);

        targetEntity.ifPresent(event -> {
            event.setTitle(requestDTO.getTitle());
            event.setContent(requestDTO.getContent());
            eventRepository.save(event);
        });

        return getList();
    }

    public EventDetailResponseDTO detail(int eventNo) {

        Event event = eventRepository.findByEventNo(eventNo).orElseThrow(
                () -> {
                    log.warn("글 번호가 존재하지 않아 조회되지 않습니다. - no : {}", eventNo);
                    throw new RuntimeException("글 번호가 존재하지 않아 조회되지 않습니다.");
                }

        );
        return new EventDetailResponseDTO(event);

    }

    private Optional<User> getUser(String userId) {
        Optional<User> user = userRepository.findUserByUserIdOnly(userId);

        return user;
    }

}