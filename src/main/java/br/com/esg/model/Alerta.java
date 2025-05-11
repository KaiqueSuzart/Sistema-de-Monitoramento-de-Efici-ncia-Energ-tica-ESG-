package br.com.esg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Alerta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O limite do alerta é obrigatório")
    @Positive(message = "O limite deve ser positivo")
    private Double limite;
    
    @NotNull(message = "A data de geração do alerta é obrigatória")
    private LocalDateTime dataGeracao;
    
    @ManyToOne
    @JoinColumn(name = "consumo_id", nullable = false)
    @NotNull(message = "O consumo referente é obrigatório")
    private Consumo consumoReferente;
} 