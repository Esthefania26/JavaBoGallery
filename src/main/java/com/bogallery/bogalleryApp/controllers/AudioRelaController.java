package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Audio_Relato;
import com.bogallery.bogalleryApp.service.imp.ActividadImp;
import com.bogallery.bogalleryApp.service.imp.AudioRelaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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
            DateTimeFormatter formatterFechaAR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            audio_relato.setFechaPublicacionAR(LocalDateTime.parse(request.get("Fecha_publicacionAR").toString(), formatterFechaAR));
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

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Audio_Relato> audioRelatoList = this.audioRelaImp.findAll();
            response.put("status", "success");
            response.put("data", audioRelatoList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {

          Audio_Relato audio_relato = this.audioRelaImp.findById(id);

          audioRelaImp.delete(audio_relato);

            response.put("status", "success");
            response.put("message", "Audio eliminado correctamente");
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
            Audio_Relato audio_relato = this.audioRelaImp.findById(id);

            if (audio_relato == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Audio_Relato no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

           if (request.containsKey("NombreAR")) {
                audio_relato.setNombreAR(request.get("NombreAR").toString());
            }
            if (request.containsKey("DescripcionAR")) {
                audio_relato.setDescripcionAR(request.get("DescripcionAR").toString());
            }
            if (request.containsKey("URL_AR")) {
                audio_relato.setUrlAR(request.get("URL_AR").toString());
            }
            if (request.containsKey("Fecha_publicacionAR")) {
                DateTimeFormatter formatterFechaAR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                audio_relato.setFechaPublicacionAR(LocalDateTime.parse(request.get("Fecha_publicacionAR").toString(), formatterFechaAR));
            }
            if (request.containsKey("Calificacion")) {
                audio_relato.setCalificacion(Integer.parseInt(request.get("Calificacion").toString()));
            }

            this.audioRelaImp.update(audio_relato);

            response.put("status", "success");
            response.put("data", "Audio_Relato actualizado correctamente");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
