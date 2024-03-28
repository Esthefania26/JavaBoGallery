package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Fotografia;
import com.bogallery.bogalleryApp.entities.Lugar;
import com.bogallery.bogalleryApp.service.imp.FotografiaImp;
import com.bogallery.bogalleryApp.service.imp.LugarImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/fotografia/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class FotografiaController {

    @Autowired
    FotografiaImp fotografiaImp;

    @Autowired
    private LugarImp lugarImp;

    @PostMapping("create")

    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Fotografia fotografia=new Fotografia();

            fotografia.setDescripcionF(request.get("DescripcionF").toString());

            if(request.containsKey("fotografia") && request.get("fotografia") !=null){
                fotografia.setFotografia(request.get("fotografia").toString().getBytes());
            }

           Lugar lugar = lugarImp.findById(Long.parseLong(request.get("Id_lugar").toString()));
            fotografia.setLugar(lugar);



            this.fotografiaImp.create(fotografia);
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
            List<Fotografia> fotografiaList = this.fotografiaImp.findAll();
            response.put("status", "success");
            response.put("data", fotografiaList);
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
            Fotografia fotografia = this.fotografiaImp.findById(id);

            if (fotografia == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Fotografía no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", "success");
            response.put("data", fotografia);

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
            Fotografia fotografia = fotografiaImp.findById(id);
            if (fotografia == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Fotografía no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            fotografiaImp.delete(fotografia);
            response.put("status", HttpStatus.OK);
            response.put("message", "Fotografía eliminada correctamente");
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
            Fotografia fotografia = fotografiaImp.findById(id);
            if (fotografia == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Fotografía no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("DescripcionF")) {
                fotografia.setDescripcionF(request.get("DescripcionF").toString());
            }
            // Aquí puedes agregar más campos para actualizar según tu necesidad

            fotografiaImp.update(fotografia);

            response.put("status", HttpStatus.OK);
            response.put("message", "Fotografía actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
