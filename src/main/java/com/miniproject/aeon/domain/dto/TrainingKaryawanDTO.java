package com.miniproject.aeon.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingKaryawanDTO {
    private Long id;
    private KaryawanToTrainingDTO karyawanToTrainingDTO;
    private TrainingDTO trainingDTO;
}
