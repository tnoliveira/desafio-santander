package com.desafio.santander.repository;

import com.desafio.santander.model.Log;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface LogRepository extends CrudRepository<Log, String> {
}
