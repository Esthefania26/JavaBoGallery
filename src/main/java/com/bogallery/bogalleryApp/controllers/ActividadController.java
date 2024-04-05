package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Actividad;
import com.bogallery.bogalleryApp.entities.Lugar;
import com.bogallery.bogalleryApp.service.imp.ActividadImp;
import com.bogallery.bogalleryApp.service.imp.LugarImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/actividad/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class ActividadController {
    @Autowired
    ActividadImp actividadImp;

    @Autowired
    private LugarImp lugarImp;


    @PostMapping("create")

    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Actividad actividad = new Actividad();
            actividad.setNombreACT(request.get("nombreACT").toString());
            actividad.setDescripcionACT(request.get("descripcionACT").toString());
            actividad.setFecha_inicioACT(LocalDate.parse(request.get("fecha_inicioACT").toString()));
            actividad.setFecha_finACT(LocalDate.parse(request.get("fecha_finACT").toString()));
            actividad.setJornada(request.get("jornada").toString());
            actividad.setValor(Integer.parseInt(request.get("valor").toString()));
            actividad.setURL_ACT(request.get("url_act").toString());
            Lugar lugar = lugarImp.findById(Long.parseLong(request.get("Id_lugar").toString()));
            actividad.setLugar(lugar);
            this.actividadImp.create(actividad);


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
            List<Actividad> actividadList = this.actividadImp.findAll();
            response.put("status", "success");
            response.put("data", actividadList);
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
            Actividad actividad = this.actividadImp.findById(id);

            if (actividad == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Actividad no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", "success");
            response.put("data", actividad);

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

            Actividad actividad = this.actividadImp.findById(id);

            actividadImp.delete(actividad);

            response.put("status", "success");
            response.put("message", "Usuario eliminado correctamente");
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
            Actividad actividad = this.actividadImp.findById(id);

            if (actividad == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Actividad no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

           if (request.containsKey("nombreACT")) {
                actividad.setNombreACT(request.get("nombreACT").toString());
            }
            if (request.containsKey("descripcionACT")) {
                actividad.setDescripcionACT(request.get("descripcionACT").toString());
            }
            if (request.containsKey("jornada")) {
                actividad.setJornada(request.get("jornada").toString());
            }
            if (request.containsKey("fecha_inicioACT")) {

                actividad.setFecha_inicioACT(LocalDate.parse(request.get("fecha_inicioACT").toString()));
            }
            if (request.containsKey("fecha_finACT")) {

                actividad.setFecha_finACT(LocalDate.parse(request.get("fecha_finACT").toString()));
            }
            if (request.containsKey("valor")) {
                actividad.setValor(Integer.parseInt(request.get("valor").toString()));
            }
            if (request.containsKey("url_act")) {
                actividad.setURL_ACT(request.get("url_act").toString());
            }

            this.actividadImp.update(actividad);

            response.put("status", "success");
            response.put("data", "Actividad actualizada correctamente");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
