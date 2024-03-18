package com.bogallery.bogalleryApp.controllers;


import com.bogallery.bogalleryApp.entities.Promocion;
import com.bogallery.bogalleryApp.service.imp.PromocionImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/promocion/")
@CrossOrigin("*")
public class PromocionController {
    @Autowired
    PromocionImp promocionImp;

    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {
            System.out.println("@@@@"+request);
            Promocion promocion = new Promocion();
            promocion.setCondicionesPro(request.get("CondicionesPro").toString());
            promocion.setDescripcionPro(request.get("DescripcionPro").toString());
          //  promocion.setDisponibilidad(request.get("Disponibilidad").toString());
            promocion.setTipoPro(request.get("TipoPro").toString());
          //  promocion.setFechainiciopro(request.get("Fechainiciopro").toString());
            //  promocion.setFechafinpro(request.get("Fechafinpro").toString());


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
