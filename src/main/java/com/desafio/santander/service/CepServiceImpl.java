package com.desafio.santander.service;

import com.desafio.santander.dto.CepResponseDTO;
import com.desafio.santander.model.Log;
import com.desafio.santander.repository.LogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CepServiceImpl implements CepService {

    private final LogRepository logRepository;
    private final ObjectMapper objectMapper;
    private final CepApiClient cepApiClient;

    @Autowired
    public CepServiceImpl(CepApiClient cepApiClient, LogRepository logRepository) {
        this.cepApiClient = cepApiClient;
        this.logRepository = logRepository;
        objectMapper = new ObjectMapper();
    }


    public CepResponseDTO buscarCep(String cep) {
        CepResponseDTO response = cepApiClient.getCep(cep);
        String json;
        try {
            json = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        logRepository.save(Log.builder().
                timestamp(LocalDateTime.now().
                        format(DateTimeFormatter.ISO_DATE_TIME))
                .body(json).build());

        return response;
    }


    @FeignClient(name = "mock-api", url = "http://viacep.com.br/ws")
    interface CepApiClient {
        @GetMapping("/{cep}/json")
        CepResponseDTO getCep(@PathVariable String cep);
    }

}