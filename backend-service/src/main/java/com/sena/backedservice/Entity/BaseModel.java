package com.sena.backedservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import io.swagger.v3.oas.annotations.media.Schema;

@MappedSuperclass
public class BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID del registro")
	private Long id;
	
	@Column(name = "estado", nullable = false)
	@Schema(description = "Estado del registro")
	private Boolean estado;
	
	
	/**
	 * Retorna el ID del registro.
	 *
	 * @return el ID del registro
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID del registro.
	 *
	 * @param id el ID del registro a establecer
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retorna el estado del registro.
	 *
	 * @return el estado del registro
	 */
	public Boolean getEstado() {
		return estado;
	}

	/**
	 * Establece el estado del registro.
	 *
	 * @param state el estado del registro a establecer
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
}
