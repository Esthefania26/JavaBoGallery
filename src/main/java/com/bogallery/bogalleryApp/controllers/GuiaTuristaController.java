package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.GuiaTurista;
import com.bogallery.bogalleryApp.service.imp.GuiaTuristaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/guiaTurista/")
@CrossOrigin("*")
public class GuiaTuristaController {
    @Autowired
    GuiaTuristaImp guiaTuristaImp;
    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {

            System.out.println("@@@@"+request);
            GuiaTurista guiaTurista = new GuiaTurista();

            guiaTurista.setDescripcionGt(request.get("DescripcionGt").toString());
            this.guiaTuristaImp.create(guiaTurista);

            response.put("status","success");
            response.put("data","Registro Exitoso");
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
