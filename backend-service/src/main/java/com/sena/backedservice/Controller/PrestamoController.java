package com.sena.backedservice.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sena.backedservice.Dto.ApiResponseDto;
import com.sena.backedservice.Entity.Prestamo;
import com.sena.backedservice.Service.PrestamoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoService service;

    @Operation(summary = "Obtener todos los usuarios", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios")
    })
    @GetMapping
    public List<Prestamo> all() throws Exception{
        return service.all();
    }

    @Operation(summary = "Obtener un usuario por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("{id}")
    public Optional<Prestamo> show(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @Operation(summary = "Crear un nuevo usuario", responses = {
            @ApiResponse(responseCode = "201", description = "Usuario creado")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Prestamo save(@RequestBody Prestamo prestamo) throws Exception{
        return service.save(prestamo);
    }

    @Operation(summary = "Actualizar un usuario existente", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ApiResponseDto<Prestamo>> update(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        try {
            service.update(id, prestamo);
            return ResponseEntity.ok(new ApiResponseDto<Prestamo>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Prestamo>(e.getMessage(), null, false));
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception{
        service.delete(id);
    }
    
    
    @Operation(summary = "Consultar libros de un usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Libros encontrados"),
            @ApiResponse(responseCode = "404", description = "Libros no encontrados")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> getLibrosPrestadosPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<Prestamo> prestamos = service.findByUsuarioId(usuarioId);
            if (!prestamos.isEmpty()) {
                return ResponseEntity.ok(prestamos);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
