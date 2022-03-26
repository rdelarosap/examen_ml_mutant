package com.adn.validacion.controlador;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adn.validacion.modelo.ADNSequence;
import com.adn.validacion.servicio.MutantService;


/**
 * REST controller Detecta si es humano o mutante.
 */
@RestController
@RequestMapping("/mutant")
public class MutantController {
	
	private static final Logger log = LoggerFactory.getLogger(MutantController.class);

	@Autowired
	private MutantService mutantService;
	
	@Autowired
    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

	/**
	 * Detecta si es humano o mutante.
	 * 
	 * @param dnaSequence
	 * @return
	 * @return 200 if human is a mutant., else 403
	 */
	@PostMapping("/")
	public ResponseEntity<Void> isMutant(@RequestBody @Valid ADNSequence dnaSequence) {
		log.debug("Solicitud para detectar si es humano o mutante: {}", dnaSequence);

		boolean isMutant = mutantService.isMutantDNA(dnaSequence);
		if (isMutant) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}
