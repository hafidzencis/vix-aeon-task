package com.miniproject.aeon.repository;

import com.miniproject.aeon.domain.dao.TrainingKaryawan;
import com.miniproject.aeon.domain.dto.TrainingKaryawanDTO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrainingKaryawanRepository extends PagingAndSortingRepository<TrainingKaryawan, Long> {

}
