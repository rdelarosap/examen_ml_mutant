package com.adn.validacion.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.adn.validacion.modelo.ADNStatus;
import com.adn.validacion.repositorio.ADNStatusRepository;


/**
 * Utilizado para la verificaciòn de estadisticas de humanos y mutantes
 */
@Service
public class ADNStatusService {
	
	private static final Logger log = LoggerFactory.getLogger(ADNStatusService.class);

	@Autowired
	private ADNStatusRepository dnaStatusRepository;

	/**
	 * return the statistics of verifications the exposed method (humans, mutants
	 * and ratio).
	 * 
	 * @return statistics and ratio
	 */
	@CachePut("stats")
	public ADNStatus getStatistics() {
		log.debug("Realiza el proceso de estadistica de humanos y mutantes");
		return dnaStatusRepository.getSummaryStatus();
	}

}
