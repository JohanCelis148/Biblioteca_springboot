package com.sena.backedservice.Dto;

public interface ILibroDto {
	
	/**
     * Obtiene el código del modulo.
     *
     * @return el estado del modulo
     */
    String getCodigo();
    
    /**
     * Obtiene la ruta del modulo.
     *
     * @return la ruta del modulo
     */
    String getDescripcion();
    
    
    /**
     * Obtiene la cantidad del modulo.
     *
     * @return la cantidad del modulo
     */
    Integer getState();
    
    /**
     * Obtiene la cantidad del modulo.
     *
     * @return la cantidad del modulo
     */
    Integer getQuantity();
}
