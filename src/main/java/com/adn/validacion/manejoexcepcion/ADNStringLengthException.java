package com.adn.validacion.manejoexcepcion;

import org.springframework.http.HttpStatus;


/**
 * Cuando la longitud de la secuencia ADN es incosistente.
 */
public class ADNStringLengthException extends ADNException{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 * <p>
	 * The length of the DNA sequences must be the same size
	 */
	private static final String error = "adn.sequence.inconsistent.length";

	/**
	 * default args
	 * 
	 * @param expected
	 */
	public ADNStringLengthException(int expected, int found) {
		super(error, HttpStatus.BAD_REQUEST);
		String msgError = "Las longitudes de las secuencias del ADN deben ser del mismo tamaño. Excepcio " + expected + ", encontrada "
				+ found;
		super.setErroMessage(msgError);
	}


}
