package com.miniproject.aeon.domain.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miniproject.aeon.domain.common.BaseDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "training")
@SQLDelete(sql = "UPDATE training SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")

public class Training extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_pengajar",nullable = false)
    private String namaPengajar;

    @Column(name = "tema",nullable = false)
    private String tema;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "training")
    private List<TrainingKaryawan> karyawanTrainingList;



}
