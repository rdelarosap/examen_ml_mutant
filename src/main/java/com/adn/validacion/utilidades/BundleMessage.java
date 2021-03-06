package com.adn.validacion.utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

/**
 * Clase utilitaria que encapsula los llamados para acrivar los mensajes
 */
@Service
public class BundleMessage {
	/**
	 * retrieve the messages from the messages.properties
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * load message
	 *
	 * @param message code
	 * @param args    parameters
	 * @return message
	 */
	public String getMessage(String message, Object... args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

	/**
	 * load message from {@link FieldError}
	 *
	 * @param fieldError Encapsulates a field error
	 * @return message
	 */
	public String getMessage(FieldError fieldError) {
		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}

	/**
	 * load message
	 * 
	 * @param message code
	 * @param args    code args
	 * @return message
	 */
	public String getMessageProperties(String message, Object... args) {
		int length = args != null ? args.length : 0;
		Object[] messages = new Object[length];
		for (int i = 0; i < length; i++) {
			messages[i] = this.getMessage(args[i].toString());
		}
		return this.getMessage(message, messages);
	}

}
