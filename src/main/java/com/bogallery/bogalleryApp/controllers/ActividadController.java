package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Actividad;
import com.bogallery.bogalleryApp.service.imp.ActividadImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            /*DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Ajusta el formato según tus necesidades
            actividad.setFechaInicioACT(LocalDateTime.parse(request.get("Fecha_inicioACT").toString(), formatterFecha));
            */
           // actividad.setFechaFinACT(LocalDateTime.parse(request.get("Fecha_finACT").toString(), formatterFecha));

            /*JornadaEnum jornada = JornadaEnum.valueOf(request.get("Jornada").toString());
            actividad.setJornada(jornada);*/
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
}
