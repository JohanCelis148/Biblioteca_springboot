package com.sena.backedservice.IRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.backedservice.Dto.ILoginDto;
import com.sena.backedservice.Dto.IUsuarioDto;
import com.sena.backedservice.Entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	@Query(value = "SELECT "
			+ " 	count(id) as quantity "
			+ " FROM "
			+ " 	usuario "
			+ " WHERE documento = :documento "
			+ " OR nombre = :nombre ", nativeQuery = true)
    Optional<IUsuarioDto> getValidate(String document, String nombre);

	@Query(value = " SELECT  "		
			+ "  u.estado estado, "
			+ "  u.correo correo"
			+ "FROM  "
			+ "	usuario u  "			
			+ "WHERE  "
			+ "	u.user = :user AND u.password = :password  AND u.estado = TRUE ", nativeQuery = true)
Optional<ILoginDto> getLogin(String user, String password);
}
