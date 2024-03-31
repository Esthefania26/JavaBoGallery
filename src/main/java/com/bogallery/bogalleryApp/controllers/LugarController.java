package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Lugar;
import com.bogallery.bogalleryApp.entities.Usuario;
import com.bogallery.bogalleryApp.service.imp.LugarImp;
import com.bogallery.bogalleryApp.service.imp.UsuarioImp;
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
@RequestMapping(path = "/api/lugar/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class LugarController {

    @Autowired
    LugarImp lugarImp;

    @Autowired
    private UsuarioImp usuarioImp;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Lugar lugar=new Lugar();
            lugar.setNombreL(request.get("nombreL").toString());
            lugar.setLocalidadL(request.get("localidadL").toString());
            lugar.setBarrioL(request.get("barrioL").toString());
            lugar.setDireccionL(request.get("direccionL").toString());
            lugar.setTipoL(request.get("tipoL").toString());
            lugar.setDescripcionL(request.get("descripcionL").toString());
            lugar.setFecha_PublicacionL(LocalDate.parse(request.get("fecha_PublicacionL").toString()));
            Usuario usuario = usuarioImp.findById(Long.parseLong(request.get("Id_usu").toString()));
            lugar.setUsuario(usuario);
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

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Lugar> lugarList = this.lugarImp.findAll();
            response.put("status", "success");
            response.put("data", lugarList);
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
            Lugar lugar = this.lugarImp.findById(id);

            if (lugar == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Lugar no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", "success");
            response.put("data", lugar);

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

           if (request.containsKey("nombreL")) {
                lugar.setNombreL(request.get("nombreL").toString());
            }
            if (request.containsKey("localidadL")) {
                lugar.setLocalidadL(request.get("localidadL").toString());
            }
            if (request.containsKey("barrioL")) {
                lugar.setBarrioL(request.get("barrioL").toString());
            }
            if (request.containsKey("direccionL")) {
                lugar.setDireccionL(request.get("direccionL").toString());
            }
            if (request.containsKey("tipoL")) {
                lugar.setTipoL(request.get("tipoL").toString());
            }
            if (request.containsKey("descripcionL")) {
                lugar.setDescripcionL(request.get("descripcionL").toString());
            }
            if (request.containsKey("fecha_PublicacionL")) {
                lugar.setFecha_PublicacionL(LocalDate.parse(request.get("fecha_PublicacionL").toString()));
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
