package com.sena.backedservice.IService;

import java.util.List;
import java.util.Optional;

import com.sena.backedservice.Entity.Prestamo;

public interface IPrestamoService {
	
    /**
     * Recupera todos los usuarios existentes.
     *
     * @return una lista de objetos User que representan todos los usuarios existentes
     */
    public List<Prestamo> all() throws Exception;

    /**
     * Recupera un usuario por su ID.
     *
     * @param id el ID del usuario a recuperar
     * @return el objeto User correspondiente al ID proporcionado, o un Optional vacío si no se encuentra ningún usuario con el ID proporcionado
     */
    public Optional<Prestamo> findById(Long id) throws Exception;

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param user el objeto User a guardar
     * @return el objeto User guardado en la base de datos
     */
    public Prestamo save(Prestamo prestamo) throws Exception;

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param id el ID del usuario a actualizar
     * @param user el objeto User con los datos actualizados
     * @return el objeto User actualizado
     */
    public void update(Long id, Prestamo prestamo) throws Exception;

    /**
     * Elimina un usuario existente de la base de datos.
     *
     * @param id el ID del usuario a eliminar
     */
    public void delete(Long id) throws Exception;
    
    
    public List<Prestamo> findByUsuarioId(Long id) throws Exception;

}
