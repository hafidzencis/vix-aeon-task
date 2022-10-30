package com.miniproject.aeon.repository;

import com.miniproject.aeon.domain.dao.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface TrainingRepository extends PagingAndSortingRepository<Training, Long> {

    @Query("SELECT t FROM Training t WHERE t.namaPengajar LIKE %:nama% ")
    Page<Training> listTrainingKaryawan(@RequestParam(name = "nama") String nama, Pageable pageable);
}
