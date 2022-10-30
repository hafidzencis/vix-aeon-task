package com.miniproject.aeon.service;

import com.miniproject.aeon.domain.dao.Karyawan;
import com.miniproject.aeon.domain.dao.Training;
import com.miniproject.aeon.domain.dao.TrainingKaryawan;
import com.miniproject.aeon.domain.dto.KaryawanToTrainingDTO;
import com.miniproject.aeon.domain.dto.TrainingDTO;
import com.miniproject.aeon.domain.dto.TrainingKaryawanDTO;
import com.miniproject.aeon.repository.KaryawanRepository;
import com.miniproject.aeon.repository.TrainingKaryawanRepository;
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

@Slf4j
@Service
public class TrainingKaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingKaryawanRepository repository;

    public ResponseEntity<Object> addTrainingKaryawan(TrainingKaryawanDTO request){
        try {



            Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(request.getKaryawanToTrainingDTO().getId());
            if (optionalKaryawan.isEmpty()){
                return ResponseUtil.build("FAILED",null, HttpStatus.BAD_REQUEST);
            }

            Optional<Training> optionalTraining = trainingRepository.findById(request.getTrainingDTO().getId());
            if (optionalTraining.isEmpty()){
                return ResponseUtil.build("FAILED",null, HttpStatus.BAD_REQUEST);
            }


            TrainingKaryawan trainingKaryawan = TrainingKaryawan.builder()
                    .karyawantotraining(optionalKaryawan.get())
                    .training(optionalTraining.get())
                    .build();

            repository.save(trainingKaryawan);

            TrainingKaryawanDTO trainingKaryawanDTO = TrainingKaryawanDTO.builder()
                    .id(trainingKaryawan.getId())
                    .karyawanToTrainingDTO(KaryawanToTrainingDTO.builder()
                            .id(trainingKaryawan.getKaryawantotraining().getId())
                            .nama(trainingKaryawan.getKaryawantotraining().getNama())
                            .jk(trainingKaryawan.getKaryawantotraining().getJk())
                            .dob(trainingKaryawan.getKaryawantotraining().getDob())
                            .alamat(trainingKaryawan.getKaryawantotraining().getAlamat())
                            .status(trainingKaryawan.getKaryawantotraining().getStatus())
                            .build())
                    .trainingDTO(TrainingDTO.builder()
                            .id(trainingKaryawan.getTraining().getId())
                            .namaPengajar(trainingKaryawan.getTraining().getNamaPengajar())
                            .tema(trainingKaryawan.getTraining().getTema())
                            .build())
                    .build();
            return ResponseUtil.build("SUCCESS",trainingKaryawanDTO,HttpStatus.OK);

        }catch (Exception e){
            return ResponseUtil.build("FAILED",e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> listTrainigKaryawan(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size,
                                                      @RequestParam(required = false) String nama_karyawan,
                                                      @RequestParam(required = false) String nama_pengajar ){
        List<TrainingKaryawanDTO> trainingKaryawanListDTO = new ArrayList<>();
        try {

            if (page != null && size != null && nama_karyawan != null && nama_pengajar != null ){
              log.info("Get list karyawan by using pagination");
              Page<Karyawan> karyawanPage = karyawanRepository.getListKaryawan(nama_karyawan,
                        (PageRequest.of(page,size)));

              Page<Training> trainingPage = trainingRepository.listTrainingKaryawan(nama_pengajar,
                      (PageRequest.of(page,size)));

              List<TrainingKaryawan> trainingKaryawanList = (List<TrainingKaryawan>) repository.findAll();

              List<TrainingKaryawanDTO> getListTrainingKaryawanByPagination = new ArrayList<>();

              for(TrainingKaryawan tk : trainingKaryawanList){

                  for(Karyawan karyawan: karyawanPage){

                      for (Training training : trainingPage){

                          if (tk.getKaryawantotraining().getId() == karyawan.getId() &&
                                  tk.getTraining().getId() == training.getId()){

                              getListTrainingKaryawanByPagination.add(TrainingKaryawanDTO.builder()
                                      .id(tk.getId())
                                      .karyawanToTrainingDTO(KaryawanToTrainingDTO.builder()
                                              .id(tk.getKaryawantotraining().getId())
                                              .nama(tk.getKaryawantotraining().getNama())
                                              .jk(tk.getKaryawantotraining().getJk())
                                              .dob(tk.getKaryawantotraining().getDob())
                                              .alamat(tk.getKaryawantotraining().getAlamat())
                                              .status(tk.getKaryawantotraining().getStatus())
                                              .build())
                                      .trainingDTO(TrainingDTO.builder()
                                              .id(tk.getTraining().getId())
                                              .namaPengajar(tk.getTraining().getNamaPengajar())
                                              .tema(tk.getTraining().getTema())
                                              .build())
                                      .build());

                          }
                      }

                  }

              }

              if (getListTrainingKaryawanByPagination.isEmpty()){
                  log.info("Training Karyawan get size is null");
                  return ResponseUtil.build("FAILED",null,HttpStatus.BAD_REQUEST);
              }

                return ResponseUtil.build("SUCCESS",getListTrainingKaryawanByPagination,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                log.info("List Training Karyawan");
                List<TrainingKaryawan> listTrainingKaryawanDao = (List<TrainingKaryawan>) repository.findAll();


                for (TrainingKaryawan tk: listTrainingKaryawanDao) {
                    trainingKaryawanListDTO.add(TrainingKaryawanDTO.builder()
                                    .id(tk.getId())
                                    .karyawanToTrainingDTO(KaryawanToTrainingDTO.builder()
                                            .id(tk.getKaryawantotraining().getId())
                                            .nama(tk.getKaryawantotraining().getNama())
                                            .jk(tk.getKaryawantotraining().getJk())
                                            .dob(tk.getKaryawantotraining().getDob())
                                            .alamat(tk.getKaryawantotraining().getAlamat())
                                            .status(tk.getKaryawantotraining().getStatus())
                                            .build())
                                    .trainingDTO(TrainingDTO.builder()
                                            .id(tk.getTraining().getId())
                                            .namaPengajar(tk.getTraining().getNamaPengajar())
                                            .tema(tk.getTraining().getTema())
                                            .build())
                            .build());
                }

                return ResponseUtil.build("SUCCESS",trainingKaryawanListDTO,HttpStatus.OK);

            }


        }catch (Exception e){
            log.error("Get an error by get list training karyawan");
            return ResponseUtil.build("FAILED",e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
