package com.desafio.santander.service;

import com.desafio.santander.dto.CepResponseDTO;

public interface CepService {

    public CepResponseDTO buscarCep(String cep);
}
