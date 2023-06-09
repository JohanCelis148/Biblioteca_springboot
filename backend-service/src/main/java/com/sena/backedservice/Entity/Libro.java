package com.sena.backedservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase que representa un módulo en el sistema.
 * Extiende la clase BaseModel que proporciona los campos comunes.
 */
@Entity
@Table(name = "libro")
@Schema(description = "Entidad que representa un libro en el sistema")
public class Libro extends BaseModel {

	@Column(name = "codigo", nullable = false, unique = true)
	@Schema(description = "Código del libro", required = true)
	private String codigo;

	@Column(name = "descripcion", length = 150, unique = true)
	@Schema(description = "Descripcion del libro", maxLength = 150)
	private String descripcion;

	@Column(name = "autor", nullable = false, length = 100)
	@Schema(description = "Autor del libro", required = true, maxLength = 100)
	private String autor;
	
	@Column(name = "ejemplar", nullable = false, length = 100)
	@Schema(description = "ejemplares del libro", required = true, maxLength = 100)
	private String ejemplar;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(String ejemplar) {
		this.ejemplar = ejemplar;
	}

	
}