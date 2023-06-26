package com.sena.backedservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.backedservice.Entity.Prestamo;
import com.sena.backedservice.IRepository.IPrestamoRepository;
import com.sena.backedservice.IService.IPrestamoService;
import com.sena.backedservice.Utils.GlobalConstants;

@Service
public class PrestamoService implements IPrestamoService {
	
	@Autowired
    public IPrestamoRepository repository;

	@Override
	public List<Prestamo> all() throws Exception {
		return repository.findAll();
	}

	@Override
	public Optional<Prestamo> findById(Long id) throws Exception {
		Optional<Prestamo> op = repository.findById(id);

        if (op.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        return op;
	}

	@Override
	public Prestamo save(Prestamo prestamo) throws Exception {
    	return repository.save(prestamo);
	}

	@Override
	public void update(Long id, Prestamo prestamo) throws Exception {
		Optional<Prestamo> optionalPerson = this.repository.findById(id);

        if (optionalPerson.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        Prestamo prestamoToUpdate = optionalPerson.get();
        BeanUtils.copyProperties(prestamo, prestamoToUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

        this.repository.save(prestamoToUpdate);
		
	}

	@Override
	public void delete(Long id) throws Exception {
		 Optional<Prestamo> op = repository.findById(id);

	        if (op.isEmpty()) {
	            throw new Exception("No se encontró registro");
	        }

	        repository.deleteById(id);
	}

	@Override
	public List<Prestamo> findByUsuarioId(Long usuarioId) throws Exception {
		List<Prestamo> op = repository.getLibrosPrestadosPorUsuario(usuarioId);

        if (op.isEmpty()) {
            throw new Exception("No se encontraron libros prestados");
        }

        return op;
	}

	
}
