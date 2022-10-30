package com.miniproject.aeon.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TrainingDTO {
    private Long id;
    private String namaPengajar;
    private String tema;
}
