package br.com.esg.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsumoRequestDTO {
    @NotNull(message = "O valor do consumo é obrigatório")
    @Positive(message = "O valor do consumo deve ser positivo")
    private Double valor;
    
    @NotNull(message = "A data e hora do consumo são obrigatórias")
    private LocalDateTime dataHora;
    
    @NotNull(message = "O ID do sensor é obrigatório")
    private Long sensorId;
}

@Data
public class ConsumoResponseDTO {
    private Long id;
    private Double valor;
    private LocalDateTime dataHora;
    private Long sensorId;
    private String sensorNome;
} 