package br.com.esg.repository;

import br.com.esg.model.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SensorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SensorRepository sensorRepository;

    @Test
    public void whenSaveSensor_thenReturnSavedSensor() {
        // given
        Sensor sensor = new Sensor();
        sensor.setNome("Sensor Teste");
        sensor.setLocalizacao("Local Teste");

        // when
        Sensor savedSensor = sensorRepository.save(sensor);

        // then
        assertThat(savedSensor).isNotNull();
        assertThat(savedSensor.getId()).isNotNull();
        assertThat(savedSensor.getNome()).isEqualTo("Sensor Teste");
        assertThat(savedSensor.getLocalizacao()).isEqualTo("Local Teste");
    }

    @Test
    public void whenFindById_thenReturnSensor() {
        // given
        Sensor sensor = new Sensor();
        sensor.setNome("Sensor Teste");
        sensor.setLocalizacao("Local Teste");
        entityManager.persist(sensor);
        entityManager.flush();

        // when
        Sensor found = sensorRepository.findById(sensor.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getNome()).isEqualTo(sensor.getNome());
        assertThat(found.getLocalizacao()).isEqualTo(sensor.getLocalizacao());
    }
} 