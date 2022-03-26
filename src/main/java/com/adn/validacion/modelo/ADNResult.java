package com.adn.validacion.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
* resultado de la evalucion del adn --> isMutant
* 
*/
@Document(collection = "adn_result")
public class ADNResult {
	
	@Id
	private ADNSequence adn;

	@Indexed(name = "is_mutant")
	private boolean isMutant;

	public ADNSequence getAdn() {
		return adn;
	}

	public void setAdn(ADNSequence adn) {
		this.adn = adn;
	}

	public boolean isMutant() {
		return isMutant;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
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
		ADNResult other = (ADNResult) obj;
		if (adn == null) {
			if (other.adn != null)
				return false;
		} else if (!adn.equals(other.adn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ADNResult [adn=" + adn + ", isMutant=" + isMutant + "]";
	}

}
