package com.bogallery.bogalleryApp.controllers;


import com.bogallery.bogalleryApp.entities.Pago;
import com.bogallery.bogalleryApp.entities.Venta;
import com.bogallery.bogalleryApp.service.imp.PagoImp;
import com.bogallery.bogalleryApp.service.imp.VentaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/pago/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class PagoController {
    @Autowired
    PagoImp pagoImp;

    @Autowired
    VentaImp ventaImp;

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
            if(request.containsKey("ArchivoComprobante") && request.get("ArchivoComprobante") !=null){
                pago.setArchivoComprobante(request.get("ArchivoComprobante").toString().getBytes());
            }

            Venta venta = ventaImp.findById(Long.parseLong(request.get("Id_ventas").toString()));
            pago.setVenta(venta);


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

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Pago> pagoList = this.pagoImp.findAll();
            response.put("status", "success");
            response.put("data", pagoList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pago pago = this.pagoImp.findById(id);
            if (pago == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Pago no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("status", "success");
            response.put("data", pago);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pago pago = pagoImp.findById(id);
            if (pago == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Pago no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            pagoImp.delete(pago);
            response.put("status", HttpStatus.OK);
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
            Pago pago = pagoImp.findById(id);
            if (pago == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Pago no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("MontoTotalP")) {
                pago.setMontoTotalP(request.get("MontoTotalP").hashCode());
            }
            if (request.containsKey("CodigoComprobante")) {
                pago.setCodidoComprobante(request.get("CodigoComprobante").hashCode());
            }
            if (request.containsKey("DescripcionP")) {
                pago.setDescripcioP(request.get("DescripcionP").toString());
            }
            if (request.containsKey("TipoP")) {
                pago.setTipoP(request.get("TipoP").toString());
            }
            if (request.containsKey("ArchivoComprobante") && request.get("ArchivoComprobante") != null) {
                pago.setArchivoComprobante(request.get("ArchivoComprobante").toString().getBytes());
            }

            pagoImp.update(pago);

            response.put("status", HttpStatus.OK);
            response.put("message", "Pago actualizado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
