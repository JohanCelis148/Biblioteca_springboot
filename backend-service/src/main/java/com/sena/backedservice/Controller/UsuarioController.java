package com.sena.backedservice.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sena.backedservice.Dto.ApiResponseDto;
import com.sena.backedservice.Entity.Usuario;
import com.sena.backedservice.IService.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/security/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @Operation(summary = "Obtener todas las personas", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de personas obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron personas")
    })
    @GetMapping
    public List<Usuario> all() throws Exception{
        return service.all();
    }

    @Operation(summary = "Obtener una persona por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Persona encontrada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    @GetMapping("{id}")
    public Optional<Usuario> show(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @Operation(summary = "Crear una nueva persona", responses = {
            @ApiResponse(responseCode = "201", description = "Persona creada")
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuario) throws Exception{
        return service.save(usuario);
    }

    @Operation(summary = "Actualizar una persona existente", responses = {
            @ApiResponse(responseCode = "200", description = "Persona actualizada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ApiResponseDto<Usuario>> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            service.update(id, usuario);
            return ResponseEntity.ok(new ApiResponseDto<Usuario>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Usuario>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Eliminar una persona existente", responses = {
        @ApiResponse(responseCode = "204", description = "Persona eliminada"),
        @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception{
        service.delete(id);
    }
    
    
    /**
     * Obtiene los datos para una tabla utilizando paginación y búsqueda.
     *
     * @param page            el número de página
     * @param size            el tamaño de página
     * @param columnOrder     el nombre de la columna para ordenar
     * @param columnDirection la dirección de ordenamiento de la columna (ascendente o descendente)
     * @param search          el término de búsqueda para filtrar los datos de la tabla (opcional)
     * @return ResponseEntity que contiene un objeto ApiResponseDto con los datos de la página y el estado de la respuesta
     */
    @GetMapping("/datatable")
    public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "column_order") String columnOrder,
            @RequestParam(name = "column_direction") String columnDirection,
            @RequestParam(name = "search", required = false) String search) {
        try {
            List<Order> orders = new ArrayList<>();

            orders.add(new Order(columnDirection == "asc" ? Direction.ASC : Direction.DESC, columnOrder));

            return ResponseEntity.ok(new ApiResponseDto<Page<?>>("Datos obtenidos",
                    service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Page<?>>(e.getMessage(), null, false));
        }
    }

}