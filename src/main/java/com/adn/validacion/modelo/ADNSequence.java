package com.adn.validacion.modelo;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.annotation.Id;

/**
 * dominio, Secuencia adn .
 * <p>
 * ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
 * 
 */
public class ADNSequence {
	
	@Id
	public String id;
	
	@NotNull
	private List<String> adn;

	public List<String> getAdn() {
		return adn;
	}

	public void setAdn(List<String> adn) {
		this.adn = adn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adn == null) ? 0 : adn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ADNSequence other = (ADNSequence) obj;
		if (adn == null) {
			if (other.adn != null)
				return false;
		} else if (!CollectionUtils.isEqualCollection(adn, other.adn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ADNSequence [adn=" + adn + "]";
	}

}
