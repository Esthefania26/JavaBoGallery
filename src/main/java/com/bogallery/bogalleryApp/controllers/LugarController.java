package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Lugar;
import com.bogallery.bogalleryApp.service.imp.LugarImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            /*
            DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
lugar.setFechaPublicacionL(LocalDate.parse(request.get("Fecha_PublicacionL").toString(), formatterFechaL));
             */
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
}
