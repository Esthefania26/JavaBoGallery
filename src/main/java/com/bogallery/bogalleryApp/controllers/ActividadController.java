package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Actividad;
import com.bogallery.bogalleryApp.service.imp.ActividadImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/actividad/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class ActividadController {
    @Autowired
    ActividadImp actividadImp;


    @PostMapping("create")

    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Objects> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Actividad actividad = new Actividad();
          //actividad.setId(Long.parseLong(request.get("Id_actividades").toString()));
            actividad.setNombreACT(request.get("NombreACT").toString());
            actividad.setDescripcionACT(request.get("DescripcionACT").toString());
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            actividad.setFechaInicioACT(LocalDateTime.parse(request.get("Fecha_inicioACT").toString(), formatterFecha));

            actividad.setFechaFinACT(LocalDateTime.parse(request.get("Fecha_finACT").toString(), formatterFecha));

           // JornadaEnum jornada = JornadaEnum.valueOf(request.get("Jornada").toString());
            //actividad.setJornada(jornada);

            actividad.setValor(Integer.parseInt(request.get("Valor").toString()));

            actividad.setUrlACT(request.get("URL_ACT").toString());
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

           if (request.containsKey("NombreACT")) {
                actividad.setNombreACT(request.get("NombreACT").toString());
            }
            if (request.containsKey("DescripcionACT")) {
                actividad.setDescripcionACT(request.get("DescripcionACT").toString());
            }
            if (request.containsKey("Fecha_inicioACT")) {
                DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                actividad.setFechaInicioACT(LocalDateTime.parse(request.get("Fecha_inicioACT").toString(), formatterFecha));
            }
            if (request.containsKey("Fecha_finACT")) {
                DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                actividad.setFechaFinACT(LocalDateTime.parse(request.get("Fecha_finACT").toString(), formatterFecha));
            }
            if (request.containsKey("Valor")) {
                actividad.setValor(Integer.parseInt(request.get("Valor").toString()));
            }
            if (request.containsKey("URL_ACT")) {
                actividad.setUrlACT(request.get("URL_ACT").toString());
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
