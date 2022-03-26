package com.adn.validacion.servicio;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adn.validacion.configuracion.MutantProperties;
import com.adn.validacion.manejoexcepcion.ADNStringLengthException;
import com.adn.validacion.manejoexcepcion.InvalidAdnBaseException;
import com.adn.validacion.modelo.ADNResult;
import com.adn.validacion.modelo.ADNSequence;
import com.adn.validacion.repositorio.ADNResultRepository;
import com.adn.validacion.servicio.detector.DetectorMutant;

@Service
public class MutantService {
	
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(MutantService.class);
	/**
	 * Nitrogenous base pattern
	 */
	private static final Pattern BASE_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

	@Autowired
	private MutantProperties mutantProperties;

	@Autowired
	private ADNResultRepository dnaResultRepository;
	
	
	/**
	 * Process Singleton Instance
	 */
	private MutantService() {}

	private static class LazyHolder {
		static final MutantService INSTANCE = new MutantService();
	}

	public static MutantService getInstance() {
	return LazyHolder.INSTANCE;
	}

	/**
	 * Process and return if dna sequence belongs a mutent or human
	 * 
	 * @param adn
	 * @return true if find the count DNA sequences needed to consider a Mutant,
	 *         else false so Human
	 */
	public boolean isMutantDNA(ADNSequence dna) {
		boolean dnaMudant = isDNAMutant(dna);

		ADNResult result = new ADNResult();
		result.setAdn(dna);
		result.setMutant(dnaMudant);
		this.dnaResultRepository.save(result);

		return dnaMudant;

	}

	/**
	 * processing to decide if a dna Sequence belongs a mutant
	 * 
	 * @param dnaSequence NDA
	 * @return true if find the count DNA sequences needed to consider a Mutant
	 */
	private boolean isDNAMutant(ADNSequence dnaSequence) {
		log.debug("Start processin to decote if a dnaSequence '{}' is a mutant' DNA", dnaSequence);

		if (dnaSequence.getAdn().size() <= this.mutantProperties.getSequenceToMudant()) {
			log.debug("Minimum length must be greater than {} to belong to mutant.",
					this.mutantProperties.getSequenceToMudant());
			return false;
		}

		char[][] dna = loadDNAStructure(dnaSequence);
		DetectorMutant mutantDetector = new DetectorMutant();
		
		return mutantDetector.isMutante(dnaSequence, dna);
	}

	/**
	 * Load the DNA structure into a Two-dimensional vectors.
	 * 
	 * @param dnaSequence the dna sequence
	 * @return Two-dimensional vectors representation from DNA sequence
	 */
	private char[][] loadDNAStructure(ADNSequence dnaSequence) {
		log.debug("Load the DNA structure into a Two-dimensional vectors.");
		int vectorLength = dnaSequence.getAdn().size();
		char[][] dna = new char[vectorLength][vectorLength];

		for (int i = 0; i < vectorLength; i++) {
			String dnaRow = dnaSequence.getAdn().get(i);
			validateDNAConsistency(vectorLength, dnaRow);
			dna[i] = dnaRow.toUpperCase().toCharArray();
		}
		return dna;
	}

	/**
	 * Validate DNA consistency
	 * <p>
	 * 
	 * @param vectorLength length of DNA sequeces
	 * @param dnaRow       row of dna
	 * @throws DNAStructureLengthException     when length of the DNA sequences is
	 *                                         not the same size.
	 * @throws InvalidNitrogenousBaseException when exist a invalid valid characters
	 */
	private void validateDNAConsistency(int vectorLength, String dnaRow) {
		if (dnaRow.length() != vectorLength) {
			log.error("La longitud de las secuencias de ADN deben ser del mismo tamaño. Execpion {}, encontrada {}: {} ", vectorLength,
					dnaRow.length(), dnaRow);
			throw new ADNStringLengthException(vectorLength, dnaRow.length());
		} else if (!BASE_PATTERN.matcher(dnaRow).matches()) {
			log.error("Los carateres validos son: A, T, C e G. Encontrado {}", dnaRow);
			throw new InvalidAdnBaseException(dnaRow);
		}
	}

}
