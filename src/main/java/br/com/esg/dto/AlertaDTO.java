package br.com.esg.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AlertaRequestDTO {
    @NotNull(message = "O limite do alerta é obrigatório")
    @Positive(message = "O limite deve ser positivo")
    private Double limite;
    
    @NotNull(message = "O ID do consumo referente é obrigatório")
    private Long consumoId;
}

@Data
public class AlertaResponseDTO {
    private Long id;
    private Double limite;
    private LocalDateTime dataGeracao;
    private Long consumoId;
    private Double consumoValor;
    private String sensorNome;
} 