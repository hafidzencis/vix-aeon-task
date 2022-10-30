package com.miniproject.aeon.service;

import com.miniproject.aeon.domain.dao.DetailKaryawan;
import com.miniproject.aeon.domain.dao.Karyawan;
import com.miniproject.aeon.domain.dto.DetailKaryawanDTO;
import com.miniproject.aeon.domain.dto.KaryawanDTO;
import com.miniproject.aeon.repository.DetailKaryawanRepository;
import com.miniproject.aeon.repository.KaryawanRepository;
import com.miniproject.aeon.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private DetailKaryawanRepository detailKaryawanRepository;

    public ResponseEntity<Object> addKaryawan(KaryawanDTO request){
        try{
            Karyawan karyawan = Karyawan.builder()
                    .nama(request.getNama())
                    .jk(request.getJk())
                    .dob(request.getDob())
                    .status(request.getStatus())
                    .alamat(request.getAlamat())
                    .build();
            karyawanRepository.save(karyawan);

            DetailKaryawan detailKaryawan = DetailKaryawan.builder()
                    .nik(request.getDetailKaryawan().getNik())
                    .npwp(request.getDetailKaryawan().getNpwp())
                    .build();
            detailKaryawan.setKaryawan(karyawan);

            detailKaryawanRepository.save(detailKaryawan);

            KaryawanDTO karyawanDTO = KaryawanDTO.builder()
                    .id(karyawan.getId())
                    .nama(karyawan.getNama())
                    .jk(karyawan.getJk())
                    .dob(karyawan.getDob())
                    .alamat(karyawan.getAlamat())
                    .status(karyawan.getStatus())
                    .detailKaryawan(DetailKaryawanDTO.builder()
                            .id(detailKaryawan.getId())
                            .nik(detailKaryawan.getNik())
                            .npwp(detailKaryawan.getNpwp())
                            .build())
                    .build();
            return ResponseUtil.build("SUCCESS",karyawanDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build("FAILED",e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public ResponseEntity<Object> getByIdKaryawan(Long request){
        try {
            Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(request);
            if (optionalKaryawan.isEmpty()){
                return ResponseUtil.build("FAILED, ID NOT FOUND",null,HttpStatus.BAD_REQUEST);
            }

            Karyawan karyawan = optionalKaryawan.get();

            Optional<DetailKaryawan> detailKaryawanOptional = detailKaryawanRepository.getIdDetailKaryawan(karyawan.getId());
            if (detailKaryawanOptional.isEmpty()){
                return ResponseUtil.build("FAILED, ID NOT FOUND",null,HttpStatus.BAD_REQUEST);
            }

            DetailKaryawan detailKaryawan = detailKaryawanOptional.get();

            KaryawanDTO karyawanDTO = KaryawanDTO.builder()
                    .id(karyawan.getId())
                    .nama(karyawan.getNama())
                    .dob(karyawan.getDob())
                    .jk(karyawan.getJk())
                    .alamat(karyawan.getAlamat())
                    .status(karyawan.getStatus())
                    .detailKaryawan(DetailKaryawanDTO.builder()
                            .id(detailKaryawan.getId())
                            .nik(detailKaryawan.getNik())
                            .npwp(detailKaryawan.getNpwp())
                            .build())
                    .build();

            return ResponseUtil.build("SUCCESS",karyawanDTO,HttpStatus.OK);

        }catch (Exception e){
            return ResponseUtil.build("FAILED",e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateKaryawann(Long id,KaryawanDTO request){
         try{
             Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(id);
             if (optionalKaryawan.isEmpty()){
                 return ResponseUtil.build("FAILED, ID NOT FOUND",null,HttpStatus.BAD_REQUEST);
             }

             Karyawan karyawankk = optionalKaryawan.get();
             karyawankk.setNama(request.getNama());
             karyawankk.setDob(request.getDob());
             karyawankk.setJk(request.getJk());
             karyawankk.setAlamat(request.getAlamat());
             karyawankk.setStatus(request.getStatus());
             karyawanRepository.save(karyawankk);

             Optional<DetailKaryawan> detailKaryawanOptional = detailKaryawanRepository
                     .getIdDetailKaryawan(karyawankk.getId());
             if (detailKaryawanOptional.isEmpty()){
                 return ResponseUtil.build("FAILED, ID NOT FOUND",null,HttpStatus.BAD_REQUEST);
             }

             DetailKaryawan detailKaryawandk = detailKaryawanOptional.get();
             detailKaryawandk.setNik(request.getDetailKaryawan().getNik());
             detailKaryawandk.setNpwp(request.getDetailKaryawan().getNpwp());

             detailKaryawanRepository.save(detailKaryawandk);

             KaryawanDTO karyawanDTO = KaryawanDTO.builder()
                     .id(karyawankk.getId())
                     .nama(karyawankk.getNama())
                     .jk(karyawankk.getJk())
                     .dob(karyawankk.getDob())
                     .alamat(karyawankk.getAlamat())
                     .status(karyawankk.getStatus())
                     .detailKaryawan(DetailKaryawanDTO.builder()
                             .id(detailKaryawandk.getId())
                             .nik(detailKaryawandk.getNik())
                             .npwp(detailKaryawandk.getNpwp())
                             .build())
                     .build();

             return ResponseUtil.build("SUCCESS",karyawanDTO,HttpStatus.OK);
         }  catch (Exception e){
             return ResponseUtil.build("FAILED",e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }


        public ResponseEntity<Object> getListKaryawan( @RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size,
                                                       @RequestParam(required = false) String nama )
        {
            try {
                List<KaryawanDTO> karyawanDTOList = new ArrayList<>();

                if (nama != null && page != null && size != null){
                    Page<Karyawan> karyawanPage = karyawanRepository.getListKaryawan(nama,(PageRequest.of(page,size)));

                    List<DetailKaryawan> detailKaryawans = detailKaryawanRepository.listDetailKaryawan();
                    List<DetailKaryawanDTO> detailKaryawanDTOS = new ArrayList<>();
                            //.listDetailKaryawanByIdKaryawan((PageRequest.of(page,size)));

                    for (Karyawan karyawan : karyawanPage) {
                        log.info("karyawan nama : {}",karyawan.getNama());
                        karyawanDTOList.add(KaryawanDTO.builder()
                                .id(karyawan.getId())
                                .nama(karyawan.getNama())
                                .jk(karyawan.getJk())
                                .dob(karyawan.getDob())
                                .alamat(karyawan.getAlamat())
                                .status(karyawan.getStatus())
                                .build());

                    }
                    for (KaryawanDTO kar: karyawanDTOList) {
                        for (DetailKaryawan detkar:detailKaryawans) {
                            if (kar.getId().byteValue() == detkar.getId().byteValue()){
                                detailKaryawanDTOS.add(DetailKaryawanDTO.builder()
                                                .id(detkar.getId())
                                                .npwp(detkar.getNpwp())
                                                .nik(detkar.getNik())
                                        .build());
                            }

                        }
                    }

                    for (int i = 0; i < karyawanDTOList.size(); i++) {
                        karyawanDTOList.get(i).setDetailKaryawan(DetailKaryawanDTO.builder()
                                        .id(detailKaryawanDTOS.get(i).getId())
                                        .nik(detailKaryawanDTOS.get(i).getNik())
                                        .npwp(detailKaryawanDTOS.get(i).getNpwp())
                                .build());
                    }

                    if (karyawanDTOList.isEmpty()){
                        return ResponseUtil.build("FAILED",null,HttpStatus.BAD_REQUEST);
                    }

                    return ResponseUtil.build("SUCCESS",karyawanDTOList,HttpStatus.OK);
                }

                else {
                    List<KaryawanDTO> karyawanDTOLists = new ArrayList<>();

                    List<Karyawan> karyawanList = (List<Karyawan>) karyawanRepository.findAll();
                    List<DetailKaryawan> detailKaryawanList =  detailKaryawanRepository.
                            listDetailKaryawan();

                    for (Karyawan karyawan : karyawanList) {
                            karyawanDTOLists.add(KaryawanDTO.builder()
                                    .id(karyawan.getId())
                                    .nama(karyawan.getNama())
                                    .jk(karyawan.getJk())
                                    .dob(karyawan.getDob())
                                    .alamat(karyawan.getAlamat())
                                    .status(karyawan.getStatus())
                                    .build());
                    }

                    for (KaryawanDTO kar :karyawanDTOLists) {
                            for (DetailKaryawan detailKaryawan : detailKaryawanList) {
                                if (kar.getId().equals(detailKaryawan.getId())){
                                    karyawanDTOLists.get(detailKaryawan.getId().byteValue() - 1)
                                            .setDetailKaryawan(DetailKaryawanDTO.
                                            builder()
                                                    .id(detailKaryawan.getId())
                                                    .npwp(detailKaryawan.getNpwp())
                                                    .nik(detailKaryawan.getNik())
                                            .build());
                                }
                        }
                    }

                    return ResponseUtil.build("SUCCESS",karyawanDTOLists,HttpStatus.OK);
                }

            }catch (Exception e){
                return ResponseUtil.build("FAILED",e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
