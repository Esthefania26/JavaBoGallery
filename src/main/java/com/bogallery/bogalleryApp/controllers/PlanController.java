package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Categoria;
import com.bogallery.bogalleryApp.entities.Empresa;
import com.bogallery.bogalleryApp.entities.Lugar;
import com.bogallery.bogalleryApp.entities.Plan;
import com.bogallery.bogalleryApp.service.imp.CategoriaImp;
import com.bogallery.bogalleryApp.service.imp.EmpresaImp;
import com.bogallery.bogalleryApp.service.imp.LugarImp;
import com.bogallery.bogalleryApp.service.imp.PlanImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/plan/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class PlanController {
@Autowired
    PlanImp planImp;

    @Autowired
    private LugarImp lugarImp;

    @Autowired
    private EmpresaImp empresaImp;

    @Autowired
    private CategoriaImp categoriaImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response=new HashMap<>();
        try{
            System.out.println("@@@@"+request);
            Plan plan= new Plan();
            plan.setNombreP(request.get("nombreP").toString());
            plan.setDescripcionP(request.get("descripcionP").toString());
            plan.setPropietario_plan(request.get("propietario_plan").toString());
            plan.setTotalcuposP(request.get("totalcuposP").hashCode());
            plan.setPrecioP(request.get("precioP").hashCode());
            plan.setJornadaP(request.get("jornadaP").toString());

            DateTimeFormatter formatterFechaP = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            plan.setFechaP(LocalDate.parse(request.get("FechaP").toString(), formatterFechaP));

            DateTimeFormatter formatterFechafinalP = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            plan.setFechafinalP(LocalDate.parse(request.get("FechafinalP").toString(), formatterFechafinalP));


            Lugar lugar = lugarImp.findById(Long.parseLong(request.get("Id_lugar").toString()));
            plan.setLugar(lugar);

            Empresa empresa = empresaImp.findById(Long.parseLong(request.get("Id_empresa").toString()));
            plan.setEmpresa(empresa);

            Categoria categoria = categoriaImp.findById(Long.parseLong(request.get("Id_categorias").toString()));
            plan.setCategoria(categoria);

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

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Plan> planList = this.planImp.findAll();
            response.put("status", "success");
            response.put("data", planList);
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
            Plan plan = this.planImp.findById(id);
            if (plan == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Plan no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("status", "success");
            response.put("data", plan);
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
            Plan plan = this.planImp.findById(id);
            planImp.delete(plan);
            response.put("status", "success");
            response.put("message", "Plan eliminado correctamente");
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
            Plan plan = this.planImp.findById(id);
            if (plan == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Plan no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (request.containsKey("nombreP")) {
                plan.setNombreP(request.get("nombreP").toString());
            }
            if (request.containsKey("descripcionP")) {
                plan.setDescripcionP(request.get("descripcionP").toString());
            }
            if (request.containsKey("propietario_plan")) {
                plan.setPropietario_plan(request.get("propietario_plan").toString());
            }
            if (request.containsKey("totalcuposP")) {
                plan.setTotalcuposP(Integer.parseInt(request.get("totalcuposP").toString()));
            }
            if (request.containsKey("precioP")) {
                plan.setPrecioP(Integer.parseInt(request.get("precioP").toString()));
            }
            if (request.containsKey("jornadaP")) {
                plan.setJornadaP(request.get("jornadaP").toString());
            }
            if (request.containsKey("FechaP")) {
                DateTimeFormatter formatterFechaP = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                plan.setFechaP(LocalDate.parse(request.get("FechaP").toString(), formatterFechaP));
            }
            if (request.containsKey("FechafinalP")) {
                DateTimeFormatter formatterFechafinalP = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                plan.setFechafinalP(LocalDate.parse(request.get("FechafinalP").toString(), formatterFechafinalP));
            }
            this.planImp.update(plan);
            response.put("status", "success");
            response.put("data", "Plan actualizado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
