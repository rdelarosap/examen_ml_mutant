package com.adn.validacion.repositorio.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.stereotype.Repository;

import com.adn.validacion.modelo.ADNResult;
import com.adn.validacion.modelo.ADNStatus;
import com.adn.validacion.repositorio.ADNStatusRepository;

/**
 * Resultado de comprobar si humano o mutante.
 */
@Repository
public class ADNStatusRepositoryImpl implements ADNStatusRepository{
	
	private static final Logger log = LoggerFactory.getLogger(ADNStatusRepositoryImpl.class);

	/**
	 * {@link MongoTemplate}
	 */
	@Autowired
	private transient MongoTemplate template;

	/*
	 * (non-Javadoc)
	 */
	@Override
	public ADNStatus getSummaryStatus() {
		log.debug("Repository, find summary status");

		ADNStatus status = findSumResult();
		BigDecimal ratio = calcRatio(status);
		status.setRatio(ratio);

		return status;

	}

	/**
	 * Calculate the ratio between mutant / human<br>
	 * R - ratio<br>
	 * M - mutant count<br>
	 * H - human count
	 * 
	 * R = M / H
	 * 
	 * @param status
	 * @return
	 */
	private BigDecimal calcRatio(ADNStatus status) {
		BigDecimal ratio = BigDecimal.ZERO;

		if (status.getMutantCount() != 0) {
			if (status.getHumanCount() == 0) {
				ratio = BigDecimal.ONE;
			} else {
				BigDecimal mutant = BigDecimal.valueOf(status.getMutantCount());
				BigDecimal human = BigDecimal.valueOf(status.getHumanCount());
				ratio = mutant.divide(human, 2, RoundingMode.HALF_UP);
			}
		}
		return ratio;
	}

	/**
	 * @return result summarized
	 */
	private ADNStatus findSumResult() {
		// @formatter:off
		Aggregation aggregation = Aggregation.newAggregation(
		group()
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("isMutant").equalToValue(true)).then(1).otherwise(0)).as("mutantCount")
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("isMutant").equalToValue(false)).then(1).otherwise(0)).as("humanCount")
			.count().as("total")
		);

		AggregationResults<ADNStatus> result = this.template.aggregate(aggregation, ADNResult.class, ADNStatus.class);
		// @formatter:on

		ADNStatus status = result.getUniqueMappedResult();
		return status == null ? new ADNStatus() : status;
	}

	

}
