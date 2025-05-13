package br.com.fiap.esg.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsumoDTO {
    private Long id;
    @NotNull(message = "O ID do sensor é obrigatório")
    private Long sensorId;
    @NotNull(message = "A data da medição é obrigatória")
    @PastOrPresent(message = "A data da medição não pode ser futura")
    private LocalDateTime dataMedicao;
    @NotNull(message = "O consumo em kWh é obrigatório")
    @DecimalMin(value = "0.0", message = "O consumo deve ser maior ou igual a zero")
    private Double consumoKwh;
    private Double picoDemanda;
    private Double temperatura;
    private String sensorNome;
    private String observacao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public LocalDateTime getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(LocalDateTime dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public Double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(Double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public Double getPicoDemanda() {
        return picoDemanda;
    }

    public void setPicoDemanda(Double picoDemanda) {
        this.picoDemanda = picoDemanda;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public String getSensorNome() {
        return sensorNome;
    }

    public void setSensorNome(String sensorNome) {
        this.sensorNome = sensorNome;
    }
}