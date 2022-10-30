package com.miniproject.aeon.service;

import com.miniproject.aeon.domain.dao.Training;
import com.miniproject.aeon.domain.dto.TrainingDTO;
import com.miniproject.aeon.repository.TrainingRepository;
import com.miniproject.aeon.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    public ResponseEntity<Object> addTraining(TrainingDTO request){
        try {
            Training training = Training.builder()
                    .id(request.getId())
                    .namaPengajar(request.getNamaPengajar())
                    .tema(request.getTema())
                    .build();

            trainingRepository.save(training);

            TrainingDTO trainingDTO = TrainingDTO.builder()
                    .id(training.getId())
                    .namaPengajar(training.getNamaPengajar())
                    .tema(training.getTema())
                    .build();
            return ResponseUtil.build("SUCCESS",trainingDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build("FAILED",e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateTraining(Long id, TrainingDTO request){
            try {
                Optional<Training> trainingOptional = trainingRepository.findById(id);

                if (trainingOptional.isEmpty()){
                    return ResponseUtil.build("FAILED",null,HttpStatus.BAD_REQUEST);
                }

                Training training = trainingOptional.get();
                training.setNamaPengajar(request.getNamaPengajar());
                training.setTema(request.getTema());

                trainingRepository.save(training);

                TrainingDTO trainingDTO = TrainingDTO.builder()
                        .id(training.getId())
                        .namaPengajar(training.getNamaPengajar())
                        .tema(training.getTema())
                        .build();

                return ResponseUtil.build("SUCCESS",trainingDTO,HttpStatus.OK);

            }catch (Exception e){
                return ResponseUtil.build("FAILED", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public ResponseEntity<Object> getByIdTraining(Long id){
        try {
            Optional<Training> trainingOptional = trainingRepository.findById(id);
            if (trainingOptional.isEmpty()) return ResponseUtil.build("FAILED",null,HttpStatus.BAD_REQUEST);

            TrainingDTO trainingDTO = TrainingDTO.builder()
                    .id(trainingOptional.get().getId())
                    .namaPengajar(trainingOptional.get().getNamaPengajar())
                    .tema(trainingOptional.get().getTema())
                    .build();

            return ResponseUtil.build("SUCCESS",trainingDTO,HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build("FAILED",e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> listTrainingByNama(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     @RequestParam(required = false) String nama ) {
        try {
            List<TrainingDTO> trainingDTOList = new ArrayList<>();

            if (nama != null && page != null && size != null){
                Page<Training> trainingPage = trainingRepository.listTrainingKaryawan(nama,(PageRequest.of(page,size)));

                for (Training training:trainingPage) {
                    trainingDTOList.add(TrainingDTO.builder()
                                    .id(training.getId())
                                    .namaPengajar(training.getNamaPengajar())
                                    .tema(training.getTema())
                            .build());
                }
                return ResponseUtil.build("SUCCESS",trainingDTOList,HttpStatus.OK);
            }

            else {
                List<Training> trainingList = (List<Training>) trainingRepository.findAll();

                for (Training training: trainingList) {
                    trainingDTOList.add(TrainingDTO.builder()
                                    .id(training.getId())
                                    .namaPengajar(training.getNamaPengajar())
                                    .tema(training.getTema())
                            .build());

                }

                return ResponseUtil.build("SUCCESS",trainingDTOList, HttpStatus.OK);
            }


        }catch (Exception e){
            return ResponseUtil.build("FAILED", e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
