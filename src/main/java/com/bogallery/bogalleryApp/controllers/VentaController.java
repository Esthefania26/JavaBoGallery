package com.bogallery.bogalleryApp.controllers;


import com.bogallery.bogalleryApp.entities.Venta;
import com.bogallery.bogalleryApp.service.imp.VentaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/venta/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class VentaController {
    @Autowired
    VentaImp ventaImp;
    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {
            System.out.println("@@@@"+request);
            Venta venta = new Venta();
            venta.setComision_venta(request.get("Comision_venta").hashCode());
            venta.setTotal_ventas(request.get("Total_ventas").hashCode());
            DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
            venta.setFecha_venta(LocalDateTime.parse(request.get("Fecha_venta").toString(), formatterFechaL));

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
            Venta venta = this.ventaImp.findById(id);
            ventaImp.delete(venta);
            response.put("status", "success");
            response.put("message", "Venta eliminada correctamente");
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
            Venta venta = this.ventaImp.findById(id);
            if (venta == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Venta no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("Comision_venta")) {
                venta.setComision_venta(Integer.parseInt(request.get("Comision_venta").toString()));
            }
            if (request.containsKey("Total_ventas")) {
                venta.setTotal_ventas(Integer.parseInt(request.get("Total_ventas").toString()));
            }
            if (request.containsKey("Fecha_venta")) {
                DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                venta.setFecha_venta(LocalDateTime.parse(request.get("Fecha_venta").toString(), formatterFechaL));
            }
            this.ventaImp.update(venta);
            response.put("status", "success");
            response.put("data", "Venta actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
