package com.esg.monitoramento.service;

import com.esg.monitoramento.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    public void acionarSensor(Long sensorId) {
        if (sensorId == null || sensorId <= 0) {
            throw new RecursoNaoEncontradoException("Sensor não encontrado.");
        }

        System.out.println("Sensor acionado: " + sensorId);
    }
} 