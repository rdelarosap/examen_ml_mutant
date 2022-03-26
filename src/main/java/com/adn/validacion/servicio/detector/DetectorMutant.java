package com.adn.validacion.servicio.detector;

//import java.util.LinkedList;
import java.util.List;

import com.adn.validacion.modelo.ADNSequence;

/**
* Clase encargada del procesamiento y decicion si una cadena de adn es mutante.
*/
public class DetectorMutant {
		
	/**
	 * Retorna true si detecta secuencias para considerar como mutante.
	 */
	public boolean isMutante(ADNSequence dnaSequence, char[][] matrix) {
		
		int letterCounter = 0;
		List<String> list = dnaSequence.getAdn();
		String[] dna = list.toArray(new String[0]);

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                if (j == 0) continue;
                if (i == 0) continue;
                
              //Valida 4 repeticiones Horizontal
                if (j>2 && dna[i].charAt(j) == dna[i].charAt(j-1) && dna[i].charAt(j) == dna[i].charAt(j-2) && dna[i].charAt(j) == dna[i].charAt(j-3)) 
                {
                	letterCounter++;
                }      
                
                //Valida 4 repeticiones Vertical
                if (i>2 && dna[i].charAt(j) == dna[i-1].charAt(j) && dna[i].charAt(j) == dna[i-2].charAt(j) && dna[i].charAt(j) == dna[i-3].charAt(j)) 
                {
                	letterCounter++;

                }      
                
                //Valida 4 repeticiones Diagonal principal
                if (i>2 && j>2 && dna[i].charAt(j) == dna[i-1].charAt(j-1) && dna[i].charAt(j) == dna[i-2].charAt(j-2) && dna[i].charAt(j) == dna[i-3].charAt(j-3)) 
                {
                	letterCounter++;
                }
                
              //Valida 4 repeticiones Diagonal izquierda a derecha
                if (j<3 && i<3) {
                    if (matrix[i][j] == matrix[i+1][j+1] && matrix[i][j] == matrix[i+2][j+2] && matrix[i][j] == matrix[i+3][j+3]
                    ) {
                    	letterCounter++;
                    }
                }
                
              //Valida 4 repeticiones Diagonal derecha a izquierda
                if (j>=3 && i<3) {
                    if (matrix[i][j] == matrix[i+1][j-1] && matrix[i][j] == matrix[i+2][j-2] && matrix[i][j] == matrix[i+3][j-3]
                    ) {
                    	letterCounter++;
                    }
                }
             }
        }
        
       //Valida que sea mutante (2 o màs repeticiones de 4 letras )
        if (letterCounter>1)
          {
              return true;
          }
        
        return false;
	}
}
