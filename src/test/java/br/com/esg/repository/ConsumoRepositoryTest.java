package br.com.esg.repository;

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
public class ConsumoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ConsumoRepository consumoRepository;

    @Test
    public void whenSaveConsumo_thenReturnSavedConsumo() {
        // given
        Sensor sensor = new Sensor();
        sensor.setNome("Sensor Teste");
        sensor.setLocalizacao("Local Teste");
        entityManager.persist(sensor);

        Consumo consumo = new Consumo();
        consumo.setValor(100.0);
        consumo.setDataHora(LocalDateTime.now());
        consumo.setSensor(sensor);

        // when
        Consumo savedConsumo = consumoRepository.save(consumo);

        // then
        assertThat(savedConsumo).isNotNull();
        assertThat(savedConsumo.getId()).isNotNull();
        assertThat(savedConsumo.getValor()).isEqualTo(100.0);
        assertThat(savedConsumo.getSensor().getId()).isEqualTo(sensor.getId());
    }

    @Test
    public void whenFindBySensorId_thenReturnConsumos() {
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

        // when
        List<Consumo> consumos = consumoRepository.findBySensorId(sensor.getId());

        // then
        assertThat(consumos).isNotEmpty();
        assertThat(consumos.get(0).getValor()).isEqualTo(100.0);
    }
} 