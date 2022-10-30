package com.miniproject.aeon.domain.dao;


import com.miniproject.aeon.domain.common.BaseDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rekening")
@SQLDelete(sql = "UPDATE rekening SET deleted_at = CURRENT_TIMESTAMP WHERE id = ? ")
@Where(clause = "deleted_at IS NULL")
public class Rekening extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama",nullable = false)
    private String nama;

    @Column(name = "nomor",nullable = false)
    private String nomor;

    @Column(name = "jenis",nullable = false)
    private String jenis;

    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawan;
}
