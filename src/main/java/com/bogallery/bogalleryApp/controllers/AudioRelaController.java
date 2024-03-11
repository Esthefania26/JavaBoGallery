package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Audio_Relato;
import com.bogallery.bogalleryApp.service.imp.ActividadImp;
import com.bogallery.bogalleryApp.service.imp.AudioRelaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/audiorela/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class AudioRelaController {

    @Autowired
    AudioRelaImp audioRelaImp;
    @PostMapping("create")

    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Objects> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Audio_Relato audio_relato=new Audio_Relato();
            audio_relato.setNombreAR(request.get("NombreAR").toString());
            audio_relato.setDescripcionAR(request.get("DescripcionAR").toString());
            audio_relato.setUrlAR(request.get("URL_AR").toString());
            /*
            DateTimeFormatter formatterFechaAR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Ajusta el formato según tus necesidades
audioRelato.setFechaPublicacionAR(LocalDateTime.parse(request.get("Fecha_publicacionAR").toString(), formatterFechaAR));

             */
            audio_relato.setCalificacion(Integer.parseInt(request.get("Calificacion").toString()));

            response.put("status", "succes");
            response.put("data", "Registro Exitoso");
            this.audioRelaImp.create(audio_relato);


        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
