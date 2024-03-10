package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Rol;
import com.bogallery.bogalleryApp.service.imp.RolImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/rol/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class RolController {

    @Autowired
    RolImp rolImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Objects> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Rol rol=new Rol();
            rol.setNombreR(request.get("Nombre_rol").toString());
            rol.setDescripcion_rol(request.get("Descripcion_rol").toString());
            /*DateTimeFormatter formatterFechaR = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
            rol.setFechaRegistroR(LocalDate.parse(request.get("Fecha_registroR").toString(), formatterFechaR));*/
            //rol.setEstado(request.get("Estado").charAt(0));


            this.rolImp.create(rol);

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
