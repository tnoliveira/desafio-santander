package com.desafio.santander.controller;


import com.desafio.santander.dto.CepResponseDTO;
import com.desafio.santander.model.Log;
import com.desafio.santander.repository.LogRepository;
import com.desafio.santander.service.CepService;
import com.desafio.santander.service.CepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CepController {

    private final CepService cepService;
    private final LogRepository logRepository;

    @Autowired
    public CepController(CepService cepService, LogRepository logRepository) {
        this.cepService = cepService;
        this.logRepository = logRepository;
    }

    @GetMapping("/buscar-cep/{cep}")
    public CepResponseDTO buscarCep(@PathVariable String cep) {
        return cepService.buscarCep(cep);
    }

    @GetMapping("/historico")
    public Iterable<Log> obterHistorico() {
        return logRepository.findAll();
    }
}

