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

   /* public UsuarioController(UsuarioImp usuarioImp) {
        this.usuarioImp = usuarioImp;
    }*/

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@" + request);
            Usuario usuario = new Usuario();
            //usuario.setId(Long.parseLong(request.get("Id_usu").toString()));
            usuario.setNombre_usu(request.get("nombre_usu").toString());
            usuario.setApellido_uso(request.get("apellido_uso").toString()); // Aquí estaba el error de tipografía en "Apellido_uso"
            usuario.setEdad(Integer.parseInt(request.get("edad").toString()));
            usuario.setDireccion_usu(request.get("direccion_usu").toString());
            usuario.setFecha_usu(LocalDate.parse(request.get("fecha_usu").toString()));
            usuario.setTelefono_usu(Integer.parseInt(request.get("telefono_usu").toString()));
            usuario.setCorreo_usu(request.get("correo_usu").toString());
            usuario.setPassword_usu(request.get("password_usu").toString());
            usuario.setPrimer_idioma(request.get("primer_idioma").toString());
            usuario.setSegundo_idioma(request.get("segundo_idioma").toString());
            usuario.setGenero_usu(request.get("genero_usu").toString());

            this.usuarioImp.create(usuario);

            response.put("status", "success");
            response.put("data", "Registro Exitoso");
        } catch (Exception e) {
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

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = this.usuarioImp.findById(id);

            if (usuario == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("error", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            response.put("status", "success");
            response.put("data", usuario);

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
            if (request.containsKey("nombre_usu")) {
                usuario.setNombre_usu(request.get("nombre_usu").toString());
            }
            if (request.containsKey("apellido_uso")) {
                usuario.setApellido_uso(request.get("apellido_uso").toString());
            }
            if (request.containsKey("edad")) {
                usuario.setEdad(Integer.parseInt(request.get("edad").toString()));
            }
            if (request.containsKey("direccion_usu")) {
                usuario.setDireccion_usu(request.get("direccion_usu").toString());
            }
            if (request.containsKey("fecha_usu"))
            {
                usuario.setFecha_usu(LocalDate.parse(request.get("fecha_usu").toString()));

            }
            if (request.containsKey("telefono_usu")) {
                usuario.setTelefono_usu(Integer.parseInt(request.get("telefono_usu").toString()));

            }
            if (request.containsKey("correo_usu")) {
                usuario.setCorreo_usu(request.get("correo_usu").toString());
            }
            if (request.containsKey("password_usu")) {
                usuario.setPassword_usu(request.get("password_usu").toString());
            }
            if (request.containsKey("primer_idioma")) {
                usuario.setPrimer_idioma(request.get("primer_idioma").toString());
            }
            if (request.containsKey("segundo_idioma")) {
                usuario.setSegundo_idioma(request.get("segundo_idioma").toString());
            }
            if (request.containsKey("genero_usu")){
                usuario.setGenero_usu(request.get("genero_usu").toString());
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
