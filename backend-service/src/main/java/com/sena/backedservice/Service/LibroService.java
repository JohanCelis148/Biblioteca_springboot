package com.sena.backedservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.backedservice.Dto.ILibroDto;
import com.sena.backedservice.Entity.Libro;
import com.sena.backedservice.IRepository.ILibroRepository;
import com.sena.backedservice.IService.ILibroService;
import com.sena.backedservice.Utils.GlobalConstants;

/**
 * Esta clase proporciona la implementación para la interfaz IModuleService.
 * Está anotada con @Service para indicar que es un componente de servicio de Spring.
 */
@Service
public class LibroService implements ILibroService {

    /**
     * Inyecta una instancia de IModuleRepository en esta clase.
     */
    @Autowired
    public ILibroRepository repository;

    /**
     * Devuelve todas las entidades Module de la base de datos.
     *
     * @return una lista de todas las entidades Module
     */
    @Override
    public List<Libro> all() throws Exception {
        return repository.findAll();
    }

    /**
     * Devuelve una entidad Module por su ID.
     *
     * @param id el ID de la entidad Module a recuperar
     * @return un Optional que contiene la entidad Module con el ID especificado, o un Optional vacío si no se encuentra
     * @throws Exception si no se encuentra la entidad Module
     */
    @Override
    public Optional<Libro> findById(Long id) throws Exception {
        Optional<Libro> op = repository.findById(id);

        if (op.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        return op;
    }

    /**
     * Guarda una entidad Module en la base de datos.
     *
     * @param module la entidad Module a guardar
     * @return la entidad Module guardada
     */
    @Override
    public Libro save(Libro libro) throws Exception {
    	Optional<ILibroDto> op = repository.getValidate(libro.getCodigo(),libro.getDescripcion());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe un libro registrado.");
        }
    	
    	return repository.save(libro);
    }

    /**
     * Modifica una entidad Module en la base de datos.
     *
     * @param id     el ID de la entidad Module a modificar
     * @param module la entidad Module modificada
     * @return la entidad Module modificada
     * @throws Exception si no se encuentra la entidad Module
     */
    @Override
    public void update(Long id, Libro libro) throws Exception {
    	Optional<Libro> optionalLibro = this.repository.findById(id);

        if (optionalLibro.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        Libro libroToUpdate = optionalLibro.get();
        BeanUtils.copyProperties(libro, libroToUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

        this.repository.save(libroToUpdate);
    }

    /**
     * Elimina una entidad Module de la base de datos por su ID.
     *
     * @param id el ID de la entidad Module a eliminar
     */
    @Override
    public void delete(Long id) throws Exception {
        Optional<Libro> op = repository.findById(id);

        if (op.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        repository.deleteById(id);
    }
}
