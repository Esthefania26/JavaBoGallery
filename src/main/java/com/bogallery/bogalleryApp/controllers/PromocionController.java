package com.bogallery.bogalleryApp.controllers;


import com.bogallery.bogalleryApp.entities.Plan;
import com.bogallery.bogalleryApp.entities.Promocion;
import com.bogallery.bogalleryApp.service.imp.PlanImp;
import com.bogallery.bogalleryApp.service.imp.PromocionImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/promocion/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class PromocionController {
    @Autowired
    PromocionImp promocionImp;
    @Autowired
    private PlanImp planImp;

    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {
            System.out.println("@@@@"+request);
            Promocion promocion = new Promocion();
            promocion.setCondicionesPro(request.get("CondicionesPro").toString());
            promocion.setDescripcionPro(request.get("DescripcionPro").toString());
            promocion.setDisponibilidad(request.get("Disponibilidad").toString().charAt(0));
            promocion.setTipoPro(request.get("TipoPro").toString());
            DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus necesidades
            promocion.setFechainiciopro(LocalDateTime.parse(request.get("Fechainiciopro").toString(), formatterFechaL));
            promocion.setFechafinpro(LocalDateTime.parse(request.get("Fechafinpro").toString(), formatterFechaL));

            Plan plan = planImp.findById(Long.parseLong(request.get("Id_planes").toString()));
            promocion.setPlan(plan);
            this.promocionImp.create(promocion);
            response.put("status","success");
            response.put("data","Registro Exitoso");
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Promocion> promociones = promocionImp.findAll();
            response.put("status", "success");
            response.put("data", promociones);
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
            Promocion promocion = promocionImp.findById(id);
            if (promocion == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Promoción no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("status", "success");
            response.put("data", promocion);
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
            Promocion promocion = this.promocionImp.findById(id);
            promocionImp.delete(promocion);
            response.put("status", "success");
            response.put("message", "Promoción eliminada correctamente");
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
            Promocion promocion = this.promocionImp.findById(id);
            if (promocion == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Promoción no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("CondicionesPro")) {
                promocion.setCondicionesPro(request.get("CondicionesPro").toString());
            }
            if (request.containsKey("DescripcionPro")) {
                promocion.setDescripcionPro(request.get("DescripcionPro").toString());
            }
            if (request.containsKey("TipoPro")) {
                promocion.setTipoPro(request.get("TipoPro").toString());
            }
            if (request.containsKey("Fechainiciopro")) {
                DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                promocion.setFechainiciopro(LocalDateTime.parse(request.get("Fechainiciopro").toString(), formatterFechaL));
            }
            if (request.containsKey("Fechafinpro")) {
                DateTimeFormatter formatterFechaL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                promocion.setFechafinpro(LocalDateTime.parse(request.get("Fechafinpro").toString(), formatterFechaL));
            }
            this.promocionImp.update(promocion);
            response.put("status", "success");
            response.put("data", "Promoción actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
