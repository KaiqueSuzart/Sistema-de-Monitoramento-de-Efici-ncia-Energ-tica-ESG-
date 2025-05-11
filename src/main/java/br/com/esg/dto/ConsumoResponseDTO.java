package br.com.esg.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsumoResponseDTO {
    private Long id;
    private Double valor;
    private LocalDateTime dataHora;
    private Long sensorId;
    private String sensorNome;
} 