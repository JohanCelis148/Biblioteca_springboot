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
import com.sena.backedservice.Entity.Libro;
import com.sena.backedservice.IService.ILibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/libro")
public class LibroController {

    @Autowired
    private ILibroService service;

    @Operation(summary = "Obtener todos los módulos", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de módulos obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron módulos")
    })
    @GetMapping
    public List<Libro> all() throws Exception{
        return service.all();
    }

    @Operation(summary = "Obtener un módulo por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Módulo encontrado"),
            @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    })
    @GetMapping("{id}")
    public Optional<Libro> show(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @Operation(summary = "Crear un nuevo módulo", responses = {
            @ApiResponse(responseCode = "201", description = "Módulo creado")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Libro save(@RequestBody Libro libro) throws Exception{
        return service.save(libro);
    }

    @Operation(summary = "Actualizar un módulo existente", responses = {
            @ApiResponse(responseCode = "200", description = "Módulo actualizado"),
            @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    })   
    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Libro>> update(@PathVariable Long id, @RequestBody Libro libro) {
        try {
            service.update(id, libro);
            return ResponseEntity.ok(new ApiResponseDto<Libro>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Libro>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Eliminar un módulo existente", responses = {
        @ApiResponse(responseCode = "204", description = "Módulo eliminado"),
        @ApiResponse(responseCode = "404", description = "Módulo no encontrado")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception{
        service.delete(id);
    }
}
