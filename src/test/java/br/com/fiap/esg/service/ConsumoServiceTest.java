package br.com.fiap.esg.service;

import br.com.fiap.esg.model.Consumo;
import br.com.fiap.esg.model.Sensor;
import br.com.fiap.esg.repository.ConsumoRepository;
import br.com.fiap.esg.repository.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConsumoServiceTest {

    @Mock
    private ConsumoRepository consumoRepository;

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private ConsumoService consumoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodos_DeveRetornarListaDeConsumos() {
        // Arrange
        List<Consumo> consumos = Arrays.asList(
            criarConsumo(1L, 100.0),
            criarConsumo(2L, 200.0)
        );
        when(consumoRepository.findAll()).thenReturn(consumos);

        // Act
        List<Consumo> resultado = consumoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(consumoRepository, times(1)).findAll();
    }

    @Test
    void registrarMedicao_ComSensorValido_DeveSalvarConsumo() {
        // Arrange
        Long sensorId = 1L;
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setStatus(Sensor.SensorStatus.ATIVO);

        Consumo consumo = new Consumo();
        consumo.setConsumoKwh(150.0);

        when(sensorRepository.findById(sensorId)).thenReturn(Optional.of(sensor));
        when(consumoRepository.save(any(Consumo.class))).thenReturn(consumo);

        // Act
        Consumo resultado = consumoService.registrarMedicao(sensorId, consumo);

        // Assert
        assertNotNull(resultado);
        assertEquals(150.0, resultado.getConsumoKwh());
        verify(sensorRepository, times(1)).findById(sensorId);
        verify(consumoRepository, times(1)).save(any(Consumo.class));
    }

    @Test
    void registrarMedicao_ComSensorInativo_DeveLancarExcecao() {
        // Arrange
        Long sensorId = 1L;
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        sensor.setStatus(Sensor.SensorStatus.INATIVO);

        Consumo consumo = new Consumo();
        consumo.setConsumoKwh(150.0);

        when(sensorRepository.findById(sensorId)).thenReturn(Optional.of(sensor));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> 
            consumoService.registrarMedicao(sensorId, consumo)
        );
    }

    @Test
    void calcularConsumoTotal_DeveRetornarSomaCorreta() {
        // Arrange
        Long sensorId = 1L;
        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fim = LocalDateTime.now();

        List<Consumo> consumos = Arrays.asList(
            criarConsumo(1L, 100.0),
            criarConsumo(2L, 200.0),
            criarConsumo(3L, 300.0)
        );

        when(consumoRepository.findBySensorIdAndDataMedicaoBetween(sensorId, inicio, fim))
            .thenReturn(consumos);

        // Act
        Double resultado = consumoService.calcularConsumoTotal(sensorId, inicio, fim);

        // Assert
        assertEquals(600.0, resultado);
        verify(consumoRepository, times(1))
            .findBySensorIdAndDataMedicaoBetween(sensorId, inicio, fim);
    }

    private Consumo criarConsumo(Long id, Double consumoKwh) {
        Consumo consumo = new Consumo();
        consumo.setId(id);
        consumo.setConsumoKwh(consumoKwh);
        consumo.setDataMedicao(LocalDateTime.now());
        return consumo;
    }
} 