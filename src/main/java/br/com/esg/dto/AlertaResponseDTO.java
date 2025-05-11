package br.com.esg.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AlertaResponseDTO {
    private Long id;
    private Double limite;
    private LocalDateTime dataGeracao;
    private Long consumoId;
    private Double consumoValor;
    private String sensorNome;
} 