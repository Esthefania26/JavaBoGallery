package com.bogallery.bogalleryApp.controllers;

import com.bogallery.bogalleryApp.entities.Usuario;
import com.bogallery.bogalleryApp.service.imp.UsuarioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/usuario/", method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.HEAD})
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioImp usuarioImp;

    public UsuarioController(UsuarioImp usuarioImp) {
        this.usuarioImp = usuarioImp;
    }
    @PostMapping("create")

    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request){
Map<String,Object> response=new HashMap<>();


        try{

            System.out.println("@@@"+request);
            Usuario usuario=new Usuario();
            usuario.setNombre(request.get("Nombre_usu").toString());
            usuario.setApellido(request.get("Apellido_usu").toString());
            usuario.setEdad(Integer.parseInt(request.get("Edad").toString()));
            usuario.setDireccion(request.get("Direccion_usu").toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaUsu = LocalDate.parse(request.get("Fecha_usu").toString(), formatter);
            usuario.setFecha_usu(fechaUsu);
            usuario.setTelefono(Integer.parseInt(request.get("Telefono_usu").toString()));
            usuario.setCoreo(request.get("Correo_usu").toString());
            usuario.setPasswaord(request.get("Password_usu").toString());
            usuario.setPrimerI(request.get("Primer_idioma").toString());
            usuario.setSegundoI(request.get("Segundo_idioma").toString());
            usuario.setGenero(request.get("Genero_usu").toString());


            this.usuarioImp.create(usuario);

            response.put("status", "success");
            response.put("data", "Registro Exitoso");

        }catch (Exception e)
        {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
    Map<String,Object> response=new HashMap<>();


        try {
            List<Usuario> usuarioList = this.usuarioImp.findAll();
            response.put("status", "succes");
            response.put("data", usuarioList);

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
           Usuario usuario = this.usuarioImp.findById(id);

            usuarioImp.delete(usuario);
            response.put("status", "success");
            response.put("message", "Usuario eliminado correctamente");
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
            Usuario usuario = this.usuarioImp.findById(id);

            if (usuario == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Actualizar los campos del usuario con los datos recibidos en el cuerpo de la solicitud
            if (request.containsKey("Nombre_usu")) {
                usuario.setNombre(request.get("Nombre_usu").toString());
            }
            if (request.containsKey("Apellido_uso")) {
                usuario.setApellido(request.get("Apellido_uso").toString());
            }
            if (request.containsKey("Edad")) {
                usuario.setEdad(Integer.parseInt(request.get("Edad").toString()));
            }
            if (request.containsKey("Direccion_usu")) {
                usuario.setDireccion(request.get("Direccion_usu").toString());
            }
            if (request.containsKey("Fecha_usu")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaUsu = LocalDate.parse(request.get("Fecha_usu").toString(), formatter);
                usuario.setFecha_usu(fechaUsu);
            }
            if (request.containsKey("Telefono_usu")) {
                usuario.setTelefono(Integer.parseInt(request.get("Telefono_usu").toString()));
            }
            if (request.containsKey("Correo_usu")) {
                usuario.setCoreo(request.get("Correo_usu").toString());
            }
            if (request.containsKey("Password_usu")) {
                usuario.setPasswaord(request.get("Password_usu").toString());
            }
            if (request.containsKey("Primer_idioma")) {
                usuario.setPrimerI(request.get("Primer_idioma").toString());
            }
            if (request.containsKey("Segundo_idioma")) {
                usuario.setSegundoI(request.get("Segundo_idioma").toString());
            }

            this.usuarioImp.update(usuario);

            response.put("status", "success");
            response.put("data", "Usuario actualizado correctamente");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
