package com.sena.backedservice.IService;

import java.util.List;
import java.util.Optional;
import com.sena.backedservice.Entity.Libro;

public interface ILibroService {

    /**
     * Recupera todos los módulos existentes.
     *
     * @return una lista de objetos Module que representan todos los módulos existentes
     */
    public List<Libro> all() throws Exception;

    /**
     * Recupera un módulo por su ID.
     *
     * @param id el ID del módulo a recuperar
     * @return el objeto Module correspondiente al ID proporcionado, o un Optional vacío si no se encuentra ningún módulo con el ID proporcionado
     */
    public Optional<Libro> findById(Long id) throws Exception;

    /**
     * Guarda un módulo en la base de datos.
     *
     * @param module el objeto Module a guardar
     * @return el objeto Module guardado en la base de datos
     */
    public Libro save(Libro libro) throws Exception;

    /**
     * Actualiza un módulo existente en la base de datos.
     *
     * @param id el ID del módulo a actualizar
     * @param module el objeto Module con los datos actualizados
     * @return el objeto Module actualizado
     */
    public void update(Long id, Libro libro) throws Exception;
    
    /**
     * Elimina un módulo existente de la base de datos.
     *
     * @param id el ID del módulo a eliminar
     */
    public void delete(Long id) throws Exception;
}
