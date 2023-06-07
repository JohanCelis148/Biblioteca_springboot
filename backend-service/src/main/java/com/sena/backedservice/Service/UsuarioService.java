package com.sena.backedservice.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sena.backedservice.Dto.ILoginDto;
import com.sena.backedservice.Dto.IUsuarioDto;  
import com.sena.backedservice.Entity.Usuario;
import com.sena.backedservice.Utils.GlobalConstants;

import com.sena.backedservice.IRepository.IUsuarioRepository;

import com.sena.backedservice.IService.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	/**
     * Inyecta una instancia de IPersonRepository en esta clase.
     */
    @Autowired
    public IUsuarioRepository repository;

    /**
     * Devuelve todas las entidades Person de la base de datos.
     *
     * @return una lista de todas las entidades Person
     */
    @Override
    public List<Usuario> all() throws Exception {
        return repository.findAll();
    }

    /**
     * Devuelve una entidad Person por su ID.
     *
     * @param id el ID de la entidad Person a recuperar
     * @return un Optional que contiene la entidad Person con el ID especificado, o un Optional vacío si no se encuentra
     * @throws Exception si no se encuentra la entidad Person
     */
    @Override
    public Optional<Usuario> findById(Long id) throws Exception {
        Optional<Usuario> op = repository.findById(id);

        if (op.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        return op;
    }

    /**
     * Guarda una entidad Person en la base de datos.
     *
     * @param person la entidad Person a guardar
     * @return la entidad Person guardada
     */
    @Override
    public Usuario save(Usuario usuario) throws Exception{
    	Optional<IUsuarioDto> op = repository.getValidate(usuario.getDocumento(),usuario.getNombre());
    	if (op.get().getQuantity()>=1) 
            throw new Exception("Validar datos, persona con número de documento o mail ya existe.");
            	
    	return repository.save(usuario);
    }

    /**
     * Modifica una entidad Person en la base de datos.
     *
     * @param id     el ID de la entidad Person a modificar
     * @param person la entidad Person modificada
     * @return la entidad Person modificada
     * @throws Exception si no se encuentra la entidad Person
     */
    @Override
    public void update(Long id, Usuario person) throws Exception {
    	Optional<Usuario> optionalPerson = this.repository.findById(id);

        if (optionalPerson.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        Usuario personToUpdate = optionalPerson.get();
        BeanUtils.copyProperties(person, personToUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

        this.repository.save(personToUpdate);
    }

    /**
     * Elimina una entidad Person de la base de datos por su ID.
     *
     * @param id el ID de la entidad Person a eliminar
     */
    @Override
    public void delete(Long id) throws Exception {
        Optional<Usuario> op = repository.findById(id);

        if (op.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        repository.deleteById(id);
    }
	
    
    /**
     * Obtiene los detalles de inicio de sesión de un usuario y contraseña especificados.
     *
     * @param user     el nombre de usuario
     * @param password la contraseña del usuario
     * @return un Optional que contiene un objeto ILoginDto que representa los detalles de inicio de sesión del usuario,
     *         o un Optional vacío si no se encontró el usuario o la contraseña no coincide
     * @throws Exception si ocurre un error al obtener los detalles de inicio de sesión
     */
    @Override
    public Optional<ILoginDto> getLogin(String correo, String contrasenia) throws Exception {
        return repository.getLogin(correo, contrasenia);
    }

	@Override
	public Page<IUsuarioDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}    
    
}
