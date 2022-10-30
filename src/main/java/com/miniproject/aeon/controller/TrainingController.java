package com.miniproject.aeon.controller;

import com.miniproject.aeon.domain.dao.Training;
import com.miniproject.aeon.domain.dto.TrainingDTO;
import com.miniproject.aeon.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping(value = "")
    public ResponseEntity<Object> addTraining(@RequestBody TrainingDTO trainingDTO){
        return trainingService.addTraining(trainingDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getTrainingById(@PathVariable Long id){
        return trainingService.getByIdTraining(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateTraining(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO){
        return trainingService.updateTraining(id, trainingDTO);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> listTraining(
                                                @RequestParam(required = false) Integer page,
                                                @RequestParam(required = false) Integer size,
                                                @RequestParam(required = false) String nama
    ){
        return trainingService.listTrainingByNama(page, size, nama);
    }

}
