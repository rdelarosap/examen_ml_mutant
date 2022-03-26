# Challenge Examen Mercadolibre.
###### Autor: [Rodolfo Dela Rosa - rodolfo_de_larosa@hotmail.com]
El objetivo de este documento es detallar la resolución del challenge propuesto por ML, donde se debe diseñar un algortimo que detecte mutantes basados su secuencia de ADN que será entregado y contar con API que puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1 millón de peticiones por segundo).

### Enunciado

* Crear un programa con un método o función con la siguiente firma:

		boolean isMutant(String[] dna);

* En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. 
* Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.
* Un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales​, de forma oblicua, horizontal o vertical.
* Ejemplo (Caso mutante):

		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

* En este caso el llamado a la función isMutant(dna) devuelve “true”.
* Desarrolla el algoritmo de la manera más eficiente posible.

### Entregables

* Código Fuente (Para Nivel 2 y 3: En repositorio github).
* Instrucciones de cómo ejecutar el programa o la API. (Para Nivel 2 y 3: En README de github).
* URL de la API (Nivel 2 y 3).


### Ideas principales

* Dividir el problema en 4 subproblemas con los cuales se pueda abordar todas las secuencias de ADN: 
	a). Encontrar secuencias horizontales.
	b). Encontrar secuencias verticales.
	c). Encontrar secuencias diagonales de Izquierda a derecha.
	d). Encontrar secuencias diagonales de Derecha a izquierda.
* Solución inspirada en algoritmo goloso (greedy), es decir, cada vez que encuentre una secuencia evaluar si existe una solución, de esta forma logrando eficiencia en el algoritmo.
* Como la estructura de datos es una matriz NxN, el objetivo es desarrollar el algoritmo con complejidad polinomial, que para este caso es O(n^2)

* Utilizar arquitecturas y patrones de diseño orientados a satisfacer el atributo de calidad de desempeño representado en los picos de alta concurrencia.

### Para la alta concurrencia

Se contemplarán las siguientes consideraciones:

* Arquitectura de microservicios
1. Implementación independiente: dado que los microservicios tienen procesos de ejecución independientes, cada microservicio también se puede implementar de forma independiente. Cuando un microservicio cambia, no hay necesidad de compilar e implementar toda la aplicación. Una aplicación compuesta por microservicios es equivalente a tener una serie de procesos de lanzamiento en paralelo.
2. Tolerancia a fallas: cuando falla un determinado componente, en la arquitectura tradicional de un solo proceso, es probable que la falla se extienda dentro del proceso, lo que resulta en la falta de disponibilidad global de la aplicación. En una arquitectura de microservicios, las fallas se aíslan en un solo servicio.
3. Escalado: las aplicaciones de arquitectura de bloque único también pueden lograr el escalado horizontal, que es la replicación completa de la aplicación completa a diferentes nodos. Cuando los diferentes componentes de la aplicación tienen diferentes requisitos de expansión, la arquitectura de microservicio refleja su flexibilidad, ya que cada servicio puede expandirse independientemente de acuerdo con las necesidades reales del negocio.
la aplicación a construir estará orientada a la nube para utilizar las características de auto escalado para los momentos de mayor tráfico sobre la aplicación.

* Patrón Singleton
No se utilizará objetos nuevos con frecuencia, esto que quiere decir, que en este diseño se utilizará el patrón singleton para la clase del servicio que identificará si una persona es humana o mutante, solo se necesitará una instancia en toda la aplicación.
Por lo tanto, se manejarán menos recursos en el servidor que crear aumentando así el rendimiento de la aplicación.

* Uso de Caché
Se utilizará caheput en el método que controlará las consultas para las estadísticas de humanos y mutantes, ya que se le solicitará a la base de datos una y otra vez, lo que sin duda es un desperdicio de rendimiento.
Se utilizará la memoria de almacenamiento dinámico para almacenar los objetos en la caché. La ventaja de usar una memoria caché de almacenamiento dinámico es que no hay serialización / deserialización, impactando en el rendimiento de la memoria ya que es la caché más rápida.

# Tecnologías y herramientas

 * [Java8] - Lenguaje de programación 
 * [Git] - Versionado
 * [Maven] - Paquetización y dependencias
 * [Spring-core] - Framework de trabajo
 * [Eclipse] - Ide de desarrollo
 * [Azure Cloud] - Servidor en la nube
 * [MongoBD] - Base de datos
 * [API Rest] - Arquitectura
 * [Apache Commons] - Librerias de Java reutilizables

# API

| DESCRIPCION  | URL | PETICION  | HEADER  | RESPUESTA
| ------ | ------ | ------ | ------ | ------ |
| Servicio Mutant | https://andmutant.azurewebsites.net/stats/ | POST | Content-Type: application/json | Devuelve 200 si es mutant o 403 en caso contrario
| Servicio Stats | https://andmutant.azurewebsites.net/mutant/ | GET |   | JSON

# Ejemplos 


	1) SERVICIO: mutant 
  	   REQUEST: [TYPE POST; HEADER Content-Type: application/json]
    	{
    	"adn": ["ATGCGA",
    		"CAGTGG",
    		"TCCCCT",
    		"ATAGGG",
    		"CCTAAA",
    		"TCATTG"
    	]}
	   RESPONSE: 200 - OK
	
	2) SERVICIO: stats
	   RESPONSE: 200 - OK 
	{ "count_mutant_adn": 2, "count_human_adn": 2, "ratio": 1.00}	

### Referencias
https://freesoft.dev/program/157139327


