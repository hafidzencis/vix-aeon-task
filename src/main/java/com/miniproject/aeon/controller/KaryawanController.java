package com.miniproject.aeon.controller;

import com.miniproject.aeon.domain.dao.Karyawan;
import com.miniproject.aeon.domain.dto.KaryawanDTO;
import com.miniproject.aeon.service.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/v1/karyawan")
public class KaryawanController {

    @Autowired
    private KaryawanService karyawanService;

    @PreAuthorize("hasAuthority('[ADMIN]')")
    @PostMapping(value = "")
    public ResponseEntity<Object> addKaryawan(@RequestBody KaryawanDTO karyawanDTO){
        return karyawanService.addKaryawan(karyawanDTO);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getByIdKaryawan(@PathVariable Long id){
        return karyawanService.getByIdKaryawan(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateKaryawan(@PathVariable Long id,@RequestBody KaryawanDTO request){
        return karyawanService.updateKaryawann(id,request);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> listKaryawan(@RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) Integer size,
                                               @RequestParam(required = false) String nama){

        return karyawanService.getListKaryawan(page,size,nama);
    }

}
