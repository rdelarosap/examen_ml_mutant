package com.adn.validacion.manejoexcepcion;

import org.springframework.http.HttpStatus;

/**
 * Cuando la secuencia de ADN es invalida.
 * <p>
 */
public class InvalidAdnBaseException extends ADNException{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 * caracteres validos: A, T, C e G.
	 */
	private static final String error = "adn.invalid.base";

	/**
	 * default args
	 * @param adnRow
	 */
	public InvalidAdnBaseException(String adnRow) {
		super(error, HttpStatus.BAD_REQUEST,
				"Caracteres Validos A, T, C e G. Caracteres invalidos encontrados en " + adnRow);
	}

}
