package com.miniproject.aeon.domain.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KaryawanDTO {
    private Long id;
    private String nama;
    private String jk;
    private Date dob;
    private String alamat;
    private String status;
    private DetailKaryawanDTO detailKaryawan;
}
