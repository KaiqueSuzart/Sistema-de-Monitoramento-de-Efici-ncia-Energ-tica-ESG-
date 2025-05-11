package br.com.esg.repository;

import br.com.esg.model.Alerta;
import br.com.esg.model.Consumo;
import br.com.esg.model.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AlertaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AlertaRepository alertaRepository;

    @Test
    public void whenSaveAlerta_thenReturnSavedAlerta() {
        // given
        Sensor sensor = new Sensor();
        sensor.setNome("Sensor Teste");
        sensor.setLocalizacao("Local Teste");
        entityManager.persist(sensor);

        Consumo consumo = new Consumo();
        consumo.setValor(100.0);
        consumo.setDataHora(LocalDateTime.now());
        consumo.setSensor(sensor);
        entityManager.persist(consumo);

        Alerta alerta = new Alerta();
        alerta.setLimite(90.0);
        alerta.setDataGeracao(LocalDateTime.now());
        alerta.setConsumoReferente(consumo);

        // when
        Alerta savedAlerta = alertaRepository.save(alerta);

        // then
        assertThat(savedAlerta).isNotNull();
        assertThat(savedAlerta.getId()).isNotNull();
        assertThat(savedAlerta.getLimite()).isEqualTo(90.0);
        assertThat(savedAlerta.getConsumoReferente().getId()).isEqualTo(consumo.getId());
    }

    @Test
    public void whenFindByConsumoReferenteSensorId_thenReturnAlertas() {
        // given
        Sensor sensor = new Sensor();
        sensor.setNome("Sensor Teste");
        sensor.setLocalizacao("Local Teste");
        entityManager.persist(sensor);

        Consumo consumo = new Consumo();
        consumo.setValor(100.0);
        consumo.setDataHora(LocalDateTime.now());
        consumo.setSensor(sensor);
        entityManager.persist(consumo);

        Alerta alerta = new Alerta();
        alerta.setLimite(90.0);
        alerta.setDataGeracao(LocalDateTime.now());
        alerta.setConsumoReferente(consumo);
        entityManager.persist(alerta);

        // when
        List<Alerta> alertas = alertaRepository.findByConsumoReferenteSensorId(sensor.getId());

        // then
        assertThat(alertas).isNotEmpty();
        assertThat(alertas.get(0).getLimite()).isEqualTo(90.0);
    }
} 