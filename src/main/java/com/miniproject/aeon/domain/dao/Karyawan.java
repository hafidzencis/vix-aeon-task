package com.miniproject.aeon.domain.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miniproject.aeon.domain.common.BaseDao;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "karyawan")
@SQLDelete(sql = "UPDATE karyawan SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Karyawan extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama",nullable = false)
    private String nama;

    @Column(name = "jk",nullable = false)
    private String jk;

    @Column(name = "dob",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(name = "alamat",nullable = false)
    private String alamat;

    @Column(name = "status")
    private String status;

//    @OneToOne
//    @JoinColumn(name = "id_karyawan")
//    private DetailKaryawan detailKaryawan;

//    @JsonIgnore
//    @OneToOne(mappedBy = "Karyawan")
//    private DetailKaryawan detailKaryawan;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "karyawan")
    private List<Rekening> rekeningList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "karyawantotraining")
    private List<TrainingKaryawan> karyawanTrainingList;


}
