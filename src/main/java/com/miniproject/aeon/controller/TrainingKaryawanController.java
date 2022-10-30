package com.miniproject.aeon.controller;

import com.miniproject.aeon.domain.dao.Training;
import com.miniproject.aeon.domain.dto.TrainingKaryawanDTO;
import com.miniproject.aeon.service.TrainingKaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/training-karyawan")
public class TrainingKaryawanController {

    @Autowired
    private TrainingKaryawanService trainingKaryawanService;


    @PostMapping(value = " ")
    public ResponseEntity<Object> createTrainingKaryawan(@RequestBody TrainingKaryawanDTO request) {
        return trainingKaryawanService.addTrainingKaryawan(request);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> listTrainingKaryawan(@RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size,
                                                       @RequestParam(required = false) String nama_karyawan,
                                                       @RequestParam(required = false) String nama_pengajar)
    {
        return trainingKaryawanService.listTrainigKaryawan(page, size, nama_karyawan, nama_pengajar);
    }
}
