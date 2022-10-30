package com.miniproject.aeon.domain.dao;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "detail_karyawan")
@SQLDelete(sql = "UPDATE detail_karyawan SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class DetailKaryawan  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nik")
    private String nik;

    @Column(name = "npwp")
    private String npwp;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_karyawan",referencedColumnName = "id")
    private Karyawan karyawan;

}
