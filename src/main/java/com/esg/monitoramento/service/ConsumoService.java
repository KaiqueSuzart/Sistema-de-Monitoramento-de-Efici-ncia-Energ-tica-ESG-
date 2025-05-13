package com.esg.monitoramento.service;

import com.esg.monitoramento.dto.ConsumoDTO;
import com.esg.monitoramento.exception.LimiteExcedidoException;
import com.esg.monitoramento.exception.ValidacaoException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ConsumoService {

    private static final double LIMITE_MIN = 10.0;
    private static final double LIMITE_MAX = 1000.0;

    private final Set<String> consumosRegistrados = new HashSet<>();

    public void registrarConsumo(ConsumoDTO dto) {
        String chave = dto.getSensorId() + "-" + dto.getPeriodo();

        if (consumosRegistrados.contains(chave)) {
            throw new ValidacaoException("Consumo já registrado para este sensor e período.");
        }

        if (dto.getValor() < LIMITE_MIN || dto.getValor() > LIMITE_MAX) {
            throw new LimiteExcedidoException("Valor de consumo fora dos limites permitidos.");
        }

        consumosRegistrados.add(chave);
        System.out.println("Consumo registrado com sucesso: " + dto);
    }
} 