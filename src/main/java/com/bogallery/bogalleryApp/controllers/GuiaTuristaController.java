package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Empresa;
import com.bogallery.bogalleryApp.entities.GuiaTurista;
import com.bogallery.bogalleryApp.entities.PerfilGuia;
import com.bogallery.bogalleryApp.entities.Plan;
import com.bogallery.bogalleryApp.service.imp.EmpresaImp;
import com.bogallery.bogalleryApp.service.imp.GuiaTuristaImp;
import com.bogallery.bogalleryApp.service.imp.PerfilGuiImp;
import com.bogallery.bogalleryApp.service.imp.PlanImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/guiaTurista/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class GuiaTuristaController {
    @Autowired
    GuiaTuristaImp guiaTuristaImp;
//Foraneas
    @Autowired
    private PerfilGuiImp perfilGuiImp;

    @Autowired
    private EmpresaImp empresaImp;

    @Autowired
    private PlanImp planImp;



    @PostMapping("create")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,Object> request){
        Map<String,Object> response=new HashMap<>();

        try {

            System.out.println("@@@@"+request);
            GuiaTurista guiaTurista = new GuiaTurista();

            guiaTurista.setDescripcionGt(request.get("DescripcionGt").toString());
//Foraneas
            PerfilGuia perfilGuia = perfilGuiImp.findById(Long.parseLong(request.get("Id_perfilGuia").toString()));
            guiaTurista.setPerfilGuia(perfilGuia);
            Empresa empresa = empresaImp.findById(Long.parseLong(request.get("Id_empresa").toString()));
            guiaTurista.setEmpresa(empresa);
            Plan plan = planImp.findById(Long.parseLong(request.get("Id_planes").toString()));
            guiaTurista.setPlan(plan);

            this.guiaTuristaImp.create(guiaTurista);


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
            List<GuiaTurista> guiaTuristaList = this.guiaTuristaImp.findAll();
            response.put("status", "success");
            response.put("data", guiaTuristaList);
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
            GuiaTurista guiaTurista = this.guiaTuristaImp.findById(id);
            if (guiaTurista == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Guía de turista no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("status", "success");
            response.put("data", guiaTurista);
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
            GuiaTurista guiaTurista = this.guiaTuristaImp.findById(id);
            guiaTuristaImp.delete(guiaTurista);
            response.put("status", "success");
            response.put("message", "Guía de turista eliminada correctamente");
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
            GuiaTurista guiaTurista = this.guiaTuristaImp.findById(id);
            if (guiaTurista == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Guía de turista no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("DescripcionGt")) {
                guiaTurista.setDescripcionGt(request.get("DescripcionGt").toString());
            }
            this.guiaTuristaImp.update(guiaTurista);
            response.put("status", "success");
            response.put("data", "Guía de turista actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
