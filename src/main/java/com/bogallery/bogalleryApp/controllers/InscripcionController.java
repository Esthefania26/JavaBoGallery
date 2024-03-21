package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Inscripcion;
import com.bogallery.bogalleryApp.service.imp.InscripcionImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/inscripcion/")
@CrossOrigin("*")
public class InscripcionController {
    @Autowired
    InscripcionImp inscripcionImp;


    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {

            System.out.println("@@@@"+request);
            Inscripcion inscripcion = new Inscripcion();
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
            inscripcion.setFecha_insc(LocalDate.parse(request.get("Fecha_insc").toString(), formatterFecha));
            inscripcion.setCantidad_personas(request.get("Cantidad_personas").hashCode());


            this.inscripcionImp.create(inscripcion);

            inscripcion.setCantidad_personas(request.get("cantidad_personas").hashCode());
            //inscripcion.setFechaInsc(request.get("fechaInsc").toString());

            inscripcion.setCantidad_personas(request.get("cantidad_personas").hashCode());
            //inscripcion.setFechaInsc(request.get("fechaInsc").toString());

            response.put("status","success");
            response.put("data","Registro Exitoso");
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Inscripcion inscripcion = this.inscripcionImp.findById(id);
            inscripcionImp.delete(inscripcion);
            response.put("status", "success");
            response.put("message", "Inscripción eliminada correctamente");
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
            Inscripcion inscripcion = this.inscripcionImp.findById(id);
            if (inscripcion == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Inscripción no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("Fecha_insc")) {
                DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                inscripcion.setFecha_insc(LocalDate.parse(request.get("Fecha_insc").toString(), formatterFechaL));
            }
            if (request.containsKey("Cantidad_personas")) {
                inscripcion.setCantidad_personas(Integer.parseInt(request.get("Cantidad_personas").toString()));
            }
            this.inscripcionImp.update(inscripcion);
            response.put("status", "success");
            response.put("data", "Inscripción actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
