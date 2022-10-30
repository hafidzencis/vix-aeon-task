package com.miniproject.aeon.domain.dao;

import com.miniproject.aeon.domain.common.BaseDao;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "karyawan_training")
@SQLDelete(sql = "UPDATE karyawan_training SET deleted_at = CURRET_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class TrainingKaryawan extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawantotraining;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
