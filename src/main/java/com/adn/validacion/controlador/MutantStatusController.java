package com.adn.validacion.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adn.validacion.modelo.ADNStatus;
import com.adn.validacion.servicio.ADNStatusService;

/**
 * REST controller para proveer estadisticas sobre si es humano o mutante.
 */
@RestController
@RequestMapping("/stats")
public class MutantStatusController {
	private static final Logger log = LoggerFactory.getLogger(MutantStatusController.class);

	@Autowired
	private ADNStatusService dnaStatusService;

	/**
	 * return the statistics of verifications the exposed method (humans, mutants
	 * and ratio).
	 * 
	 * @return status
	 */
	@GetMapping("/")
	public ResponseEntity<ADNStatus> statisticsDNAMutant() {
		log.debug("Solicitud para obtener estadisticas sobre si es humano o mutante.");
		ADNStatus status = dnaStatusService.getStatistics();
		return ResponseEntity.ok(status);
	}

}
