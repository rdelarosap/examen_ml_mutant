package com.adn.validacion.repositorio;

import org.springframework.stereotype.Repository;

import com.adn.validacion.modelo.ADNResult;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ADNResultRepository extends MongoRepository<ADNResult, ADNResult> {

}
