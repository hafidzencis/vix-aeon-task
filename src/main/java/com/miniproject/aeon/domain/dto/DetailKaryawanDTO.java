package com.miniproject.aeon.domain.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailKaryawanDTO {
    private Long id;
    private String nik;
    private String npwp;

}
