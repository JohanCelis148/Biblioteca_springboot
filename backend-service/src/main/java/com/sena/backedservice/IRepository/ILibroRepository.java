package com.sena.backedservice.IRepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.backedservice.Dto.ILibroDto;
import com.sena.backedservice.Entity.Libro;

public interface ILibroRepository extends JpaRepository<Libro, Long>  {

	@Query(value = "SELECT "
			+ " 	count(id) as quantity "
			+ " FROM "
			+ " 	libro "
			+ " WHERE codigo = :codigo "
			+ " OR descripcion = :descripcion ", nativeQuery = true)
    Optional<ILibroDto> getValidate(String codigo, String descripcion);
	
}
