package com.sena.backedservice.IRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.backedservice.Entity.Prestamo;



public interface IPrestamoRepository extends JpaRepository<Prestamo, Long>{
	
}