package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Inscripcion;
import com.bogallery.bogalleryApp.service.imp.InscripcionImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/inscripcion/")
@CrossOrigin("*")
public class InscripcionController {
    @Autowired
    InscripcionImp inscripcionImp;


    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {

            System.out.println("@@@@"+request);
            Inscripcion inscripcion = new Inscripcion();

            inscripcion.setCantidad_personas(request.get("cantidad_personas").hashCode());
            //inscripcion.setFechaInsc(request.get("fechaInsc").toString());

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
