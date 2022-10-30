package com.miniproject.aeon.repository;

import com.miniproject.aeon.domain.dao.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface KaryawanRepository extends PagingAndSortingRepository<Karyawan, Long> {

    @Query("SELECT k FROM Karyawan k WHERE k.nama LIKE %:nama%")
    Page<Karyawan> getListKaryawan(@RequestParam(name = "nama") String nama, Pageable pageable);


//    @Query("SELECT k FROM Karyawan k WHERE k.nama = :nama ")
//    List<Karyawan> listKaryawanByName(@RequestParam(name = "nama") String nama, Pageable pageable);


}
