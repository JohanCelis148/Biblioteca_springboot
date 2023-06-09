package com.sena.backedservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase que representa a una usuario en el sistema.
 * Extiende la clase BaseModel que proporciona los campos comunes.
 */
@Entity
@Table(name = "prestamo")
@Schema(description = "Entidad que representa un usuario en el sistema")
public class Prestamo extends BaseModel {
	
	@Column(name = "fecha_prestamo", nullable = false, length = 100)
	@Schema(description = "Nombre de usuario")
	private Date fechaPrestamo;
	
	@Column(name = "fecha_entrega", nullable = false, length = 100)
	@Schema(description = "Contraseña del usuario")
	private Date fechaEntrega;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Identificador del usuario asociada al prestamo")
    private Usuario usuarioId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "libro_id", nullable = false)
    @Schema(description = "Identificador el libro asociado al prestamo")
    private Libro libroId;
	
	@Column(name = "total_pagar", nullable = false, length = 100)
	@Schema(description = "Total a pagar por el prestamo")
	private Integer totalPagar;

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Libro getLibroId() {
		return libroId;
	}

	public void setLibroId(Libro libroId) {
		this.libroId = libroId;
	}

	public Integer getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(Integer totalPagar) {
		this.totalPagar = totalPagar;
	}
	
	
}