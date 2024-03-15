package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Plan;
import com.bogallery.bogalleryApp.service.imp.PlanImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/plan/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class PlanController {
@Autowired
    PlanImp planImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response=new HashMap<>();
        try{
            System.out.println("@@@@"+request);
            Plan plan= new Plan();
            //plan.setId(Long.parseLong(request.get("id").toString()));
            plan.setNombreP(request.get("nombreP").toString());
            plan.setDescripcionP(request.get("descripcionP").toString());
            plan.setPropietario_plan(request.get("propietario_plan").toString());
            plan.setTotalcuposP(request.get("totalcuposP").hashCode());
            plan.setPrecioP(request.get("precioP").hashCode());
            plan.setJornadaP(request.get("jornadaP").toString());
            //plan.setFechaP(request.get("fechaP").());
            //plan.setFechafinalP(request.get("fechafinalP").toString())

            this.planImp.create(plan);
            response.put("status","succes");
            response.put("data", "Registro Existoso");

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
