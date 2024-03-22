package com.bogallery.bogalleryApp.controllers;



import com.bogallery.bogalleryApp.entities.Novedad;
import com.bogallery.bogalleryApp.service.imp.NovedadImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/novedad/")
@CrossOrigin("*")
public class NovedadController {

    @Autowired
    NovedadImp novedadImp;
    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {
            System.out.println("@@@@"+request);
            Novedad novedad = new Novedad();
            novedad.setDescripcionN(request.get("DescripcionN").toString());
            novedad.setEstadoN(request.get("EstadoN").toString());

          this.novedadImp.create(novedad);

            novedad.setDescripcionN(request.get("descripcionN").toString());
            novedad.setEstadoN(request.get("estadoN").toString());


            novedad.setDescripcionN(request.get("descripcionN").toString());
            novedad.setEstadoN(request.get("estadoN").toString());


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
            Novedad novedad = novedadImp.findById(id);
            if (novedad == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Novedad no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            novedadImp.delete(novedad);
            response.put("status", HttpStatus.OK);
            response.put("message", "Novedad eliminada correctamente");
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
            Novedad novedad = novedadImp.findById(id);
            if (novedad == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Novedad no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("DescripcionN")) {
                novedad.setDescripcionN(request.get("DescripcionN").toString());
            }
            if (request.containsKey("EstadoN")) {
                novedad.setEstadoN(request.get("EstadoN").toString());
            }


            novedadImp.update(novedad);

            response.put("status", HttpStatus.OK);
            response.put("message", "Novedad actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
