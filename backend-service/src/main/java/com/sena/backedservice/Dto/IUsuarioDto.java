package com.sena.backedservice.Dto;

public interface IUsuarioDto {
	
	public enum DocumentType { CE, CC, TI, PP, DNI }
	
	String getNombre();
	
	String getApellido();
	
	DocumentType getTipoDocumento();
	
	String getDocumento();
	
	Byte getEdad();
	
	Boolean getGenero();
	
	String getCorreo();
	
	String getTelefono();
	
	String getTipo();
	
	String getContrasenia();
	
	String getDireccion();
	
	Integer getQuantity();
}
