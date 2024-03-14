package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Lugar;
import com.bogallery.bogalleryApp.service.imp.LugarImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/lugar/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class LugarController {

    @Autowired
    LugarImp lugarImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Objects> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Lugar lugar=new Lugar();
            lugar.setNombreL(request.get("NombreL").toString());
            lugar.setLocalidadL(request.get("LocalidadL").toString());
            lugar.setBarrioL(request.get("BarrioL").toString());
            lugar.setDireccionL(request.get("DireccionL").toString());
            lugar.setTipoL(request.get("TipoL").toString());
            lugar.setDescripcionL(request.get("DescripcionL").toString());

            DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
            lugar.setFechanPL(LocalDate.parse(request.get("Fecha_PublicacionL").toString(), formatterFechaL));

            this.lugarImp.create(lugar);
            response.put("status", "succes");
            response.put("data", "Registro Exitoso");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
         Lugar lugar = this.lugarImp.findById(id);

         lugarImp.delete(lugar);


            response.put("status", "success");
            response.put("message", "Lugar eliminado eliminado correctamente");
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
            Lugar lugar = this.lugarImp.findById(id);

            if (lugar == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Lugar no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

           if (request.containsKey("NombreL")) {
                lugar.setNombreL(request.get("NombreL").toString());
            }
            if (request.containsKey("LocalidadL")) {
                lugar.setLocalidadL(request.get("LocalidadL").toString());
            }
            if (request.containsKey("BarrioL")) {
                lugar.setBarrioL(request.get("BarrioL").toString());
            }
            if (request.containsKey("DireccionL")) {
                lugar.setDireccionL(request.get("DireccionL").toString());
            }
            if (request.containsKey("TipoL")) {
                lugar.setTipoL(request.get("TipoL").toString());
            }
            if (request.containsKey("DescripcionL")) {
                lugar.setDescripcionL(request.get("DescripcionL").toString());
            }
            if (request.containsKey("Fecha_PublicacionL")) {
                DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                lugar.setFechanPL(LocalDate.parse(request.get("Fecha_PublicacionL").toString(), formatterFechaL));
            }

            this.lugarImp.update(lugar);

            response.put("status", "success");
            response.put("data", "Lugar actualizado correctamente");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
