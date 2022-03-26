package com.adn.validacion.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adn.validacion.servicio.MutantService;

@Configuration
public class SingletonConfiguration {
		
	@Bean
    public MutantService singletonCache() {
        return MutantService.getInstance();
    }

}
