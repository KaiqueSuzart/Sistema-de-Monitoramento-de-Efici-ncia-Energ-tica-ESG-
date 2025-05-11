package br.com.esg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Consumo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O valor do consumo é obrigatório")
    @Positive(message = "O valor do consumo deve ser positivo")
    private Double valor;
    
    @NotNull(message = "A data e hora do consumo são obrigatórias")
    private LocalDateTime dataHora;
    
    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    @NotNull(message = "O sensor é obrigatório")
    private Sensor sensor;
} 