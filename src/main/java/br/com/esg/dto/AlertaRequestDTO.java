package br.com.esg.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AlertaRequestDTO {
    @NotNull(message = "O limite do alerta é obrigatório")
    @Positive(message = "O limite deve ser positivo")
    private Double limite;
    
    @NotNull(message = "O ID do consumo referente é obrigatório")
    private Long consumoId;
} 