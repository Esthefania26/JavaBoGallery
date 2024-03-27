package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Rol;
import com.bogallery.bogalleryApp.service.imp.RolImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/rol/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class RolController {

    @Autowired
    RolImp rolImp;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Rol rol = new Rol();
            rol.setNombreR(request.get("Nombre_rol").toString());
            rol.setDescripcion_rol(request.get("Descripcion_rol").toString());
            DateTimeFormatter formatterFechaR = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            rol.setFecha_registroR(LocalDate.parse(request.get("Fecha_registroR").toString(), formatterFechaR));

            rol.setEstado(request.get("Estado").toString().charAt(0));


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
    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Rol> roles = rolImp.findAll();
            response.put("status", "success");
            response.put("data", roles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Rol rol = rolImp.findById(id);
            if (rol == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Rol no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            rolImp.delete(rol);
            response.put("status", HttpStatus.OK);
            response.put("message", "Rol eliminado correctamente");
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
            Rol rol = rolImp.findById(id);
            if (rol == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Rol no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("Nombre_rol")) {
                rol.setNombreR(request.get("Nombre_rol").toString());
            }
            if (request.containsKey("Descripcion_rol")) {
                rol.setDescripcion_rol(request.get("Descripcion_rol").toString());
            }
            if (request.containsKey("Fecha_registroR")) {
                DateTimeFormatter formatterFechaR = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                rol.setFecha_registroR(LocalDate.parse(request.get("Fecha_registroR").toString(), formatterFechaR));
            }
            if (request.containsKey("Estado")) {
                rol.setEstado(request.get("Estado").toString().charAt(0));
            }
            // Puedes agregar más campos para actualizar según sea necesario

            rolImp.update(rol);

            response.put("status", HttpStatus.OK);
            response.put("message", "Rol actualizado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}

