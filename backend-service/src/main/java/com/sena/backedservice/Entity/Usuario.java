package com.sena.backedservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase que representa a una persona en el sistema.
 * Extiende la clase BaseModel que proporciona los campos comunes.
 */
@Entity
@Table(name = "usuario")
@Schema(description = "Entidad que representa un persona en el sistema")
public class Usuario extends BaseModel {

	public enum TipoDocumento { CE, CC, TI, PP, DNI }

	@Column(name = "nombre", nullable = false, length = 50)
	@Schema(description = "nombre de la persona", required = true, maxLength = 50)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 50)
	@Schema(description = "apellido de la persona", required = true, maxLength = 50)
	private String apellido;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_documento", nullable = false)
	@Schema(description = "Tipo de documento de la persona", required = true)
	private TipoDocumento tipoDocumento;

	@Column(name = "documento", nullable = false, unique = true, length = 12)
	@Schema(description = "Número de documento de la persona", required = true, maxLength = 12)
	private String documento;

	@Column(name = "edad", nullable = false)
	@Schema(description = "Edad de la persona", required = true)
	private Byte edad;

	@Column(name = "genero")
	@Schema(description = "Género de la persona")
	private Boolean genero;

	@Column(name = "correo", nullable = false, length = 30, unique = true)
	@Schema(description = "Correo electrónico de la persona", required = true, maxLength = 30)
	private String correo;
	
	@Column(name = "contrasenia", nullable = false, length = 30, unique = true)
	@Schema(description = "Correo electrónico de la persona", required = true, maxLength = 30)
	private String contrasenia;
	
	@Column(name = "tipo", nullable = false, length = 30, unique = true)
	@Schema(description = "Correo electrónico de la persona", required = true, maxLength = 30)
	private String tipo;

	@Column(name = "celular", nullable = false, length = 10)
	@Schema(description = "Número de teléfono de la persona", required = true, maxLength = 10)
	private String celular;

	@Column(name = "direccion", nullable = false, length = 30)
	@Schema(description = "Dirección de la persona", required = true, maxLength = 30)
	private String direccion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Byte getEdad() {
		return edad;
	}

	public void setEdad(Byte edad) {
		this.edad = edad;
	}

	public Boolean getGenero() {
		return genero;
	}

	public void setGenero(Boolean genero) {
		this.genero = genero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}