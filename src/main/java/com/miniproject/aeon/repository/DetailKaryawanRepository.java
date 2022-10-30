package com.miniproject.aeon.repository;

import com.miniproject.aeon.domain.dao.DetailKaryawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface DetailKaryawanRepository extends PagingAndSortingRepository<DetailKaryawan, Long> {

    @Query(value = "SELECT * FROM detail_karyawan WHERE id_karyawan = :id",nativeQuery = true)
    Optional<DetailKaryawan> getIdDetailKaryawan(@RequestParam(name = "id") Long id);

    @Query(value = "SELECT * FROM detail_karyawan",nativeQuery = true)
    List<DetailKaryawan> listDetailKaryawan();

//    @Query(value = "SELECT * FROM detail_karyawan",nativeQuery = true)
//    Page<DetailKaryawan> listDetailKaryawanUsingPage();

    //Page<DetailKaryawan> listDetailKaryawanByIdKaryawan(Pageable pageable);
}
