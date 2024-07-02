package com.example.final_project_java.event.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventModifyRequestDTO {

    @NotNull
    private String title;

    @NotNull
    private String content;

}