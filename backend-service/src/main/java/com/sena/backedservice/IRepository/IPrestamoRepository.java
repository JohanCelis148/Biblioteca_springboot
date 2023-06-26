package com.sena.backedservice.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.backedservice.Entity.Prestamo;



public interface IPrestamoRepository extends JpaRepository<Prestamo, Long>{
	@Query(value = "SELECT * FROM prestamo WHERE usuario_id = :usuarioId", nativeQuery = true)
	List<Prestamo> getLibrosPrestadosPorUsuario(Long usuarioId);
}