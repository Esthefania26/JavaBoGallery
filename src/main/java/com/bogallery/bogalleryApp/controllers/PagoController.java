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
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pago pago = this.pagoImp.findById(id);
            pagoImp.delete(pago);
            response.put("status", "success");
            response.put("message", "Pago eliminado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pago pago = this.pagoImp.findById(id);
            if (pago == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Pago no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("MontoTotalP")) {
                pago.setMontoTotalP(Integer.parseInt(request.get("MontoTotalP").toString()));
            }
            if (request.containsKey("CodigoComprobante")) {
                pago.setCodidoComprobante(Integer.parseInt(request.get("CodigoComprobante").toString()));
            }
            if (request.containsKey("DescripcionP")) {
                pago.setDescripcioP(request.get("DescripcionP").toString());
            }
            if (request.containsKey("TipoP")) {
                pago.setTipoP(request.get("TipoP").toString());
            }
            //if (request.containsKey("ArchivoComprobante")) {
            //    pago.setArchivoComprobante(request.get("ArchivoComprobante").toString());
            //}
            this.pagoImp.update(pago);
            response.put("status", "success");
            response.put("data", "Pago actualizado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
