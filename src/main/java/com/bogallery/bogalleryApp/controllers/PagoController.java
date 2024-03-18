package com.bogallery.bogalleryApp.controllers;


import com.bogallery.bogalleryApp.entities.Pago;
import com.bogallery.bogalleryApp.service.imp.PagoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/pago/")
@CrossOrigin("*")
public class PagoController {
    @Autowired
    PagoImp pagoImp;

    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {
            System.out.println("@@@@"+request);
            Pago pago = new Pago();

            pago.setMontoTotalP(request.get("MontoTotalP").hashCode());
            pago.setCodidoComprobante(request.get("CodigoComprobante").hashCode());
            pago.setDescripcioP(request.get("DescripcionP").toString());
            pago.setTipoP(request.get("TipoP").toString());
            //pago.setArchivoComprobante(request.get("ArchivoComprobante").toString());

this.pagoImp.create(pago);

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
