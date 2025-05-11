package br.com.fiap.esg.controller;

import br.com.fiap.esg.dto.SensorDTO;
import br.com.fiap.esg.model.Sensor;
import br.com.fiap.esg.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fiap.esg.repository.SensorRepository;

@Tag(name = "Sensores", description = "API para gerenciamento de sensores de energia")
@Controller
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    private SensorRepository sensorRepository;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os sensores cadastrados")
    public ResponseEntity<List<SensorDTO>> listarTodos() {
        List<SensorDTO> sensores = sensorService.listarTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Lista sensores por status")
    public ResponseEntity<List<SensorDTO>> listarPorStatus(@PathVariable Sensor.SensorStatus status) {
        List<SensorDTO> sensores = sensorService.listarPorStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Lista sensores por tipo")
    public ResponseEntity<List<SensorDTO>> listarPorTipo(@PathVariable String tipo) {
        List<SensorDTO> sensores = sensorService.listarPorTipo(tipo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/localizacao/{localizacao}")
    @Operation(summary = "Busca sensores por localização")
    public ResponseEntity<List<SensorDTO>> buscarPorLocalizacao(@PathVariable String localizacao) {
        List<SensorDTO> sensores = sensorService.buscarPorLocalizacao(localizacao).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sensores);
    }

    @PostMapping("/sensores")
    @ResponseBody
    public ResponseEntity<SensorDTO> cadastrar(@RequestBody SensorDTO sensorDTO) {
        Sensor sensor = convertToEntity(sensorDTO);
        Sensor sensorSalvo = sensorService.cadastrar(sensor);
        return ResponseEntity.ok(convertToDTO(sensorSalvo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um sensor existente")
    public ResponseEntity<SensorDTO> atualizar(@PathVariable Long id, @RequestBody SensorDTO sensorDTO) {
        Sensor sensor = convertToEntity(sensorDTO);
        Sensor sensorAtualizado = sensorService.atualizar(id, sensor);
        return ResponseEntity.ok(convertToDTO(sensorAtualizado));
    }

    @PostMapping("/{id}/manutencao")
    @Operation(summary = "Registra manutenção em um sensor")
    public ResponseEntity<SensorDTO> registrarManutencao(@PathVariable Long id) {
        Sensor sensor = sensorService.registrarManutencao(id);
        return ResponseEntity.ok(convertToDTO(sensor));
    }

    @PostMapping("/{id}/finalizar-manutencao")
    @Operation(summary = "Finaliza manutenção de um sensor")
    public ResponseEntity<SensorDTO> finalizarManutencao(@PathVariable Long id) {
        Sensor sensor = sensorService.finalizarManutencao(id);
        return ResponseEntity.ok(convertToDTO(sensor));
    }

    @GetMapping("/sensores")
    public String listarSensores(Model model) {
        List<Sensor> sensores = sensorRepository.findAll();
        model.addAttribute("sensores", sensores);
        return "sensores";
    }

    @PostMapping("/sensores/{id}/acionar")
    @ResponseBody
    public void acionarSensor(@PathVariable Long id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow();
        if (sensor.getStatus() == Sensor.SensorStatus.ATIVO) {
            sensor.setStatus(Sensor.SensorStatus.INATIVO);
        } else {
            sensor.setStatus(Sensor.SensorStatus.ATIVO);
        }
        sensorRepository.save(sensor);
    }

    @DeleteMapping("/sensores/{id}")
    @ResponseBody
    public void excluir(@PathVariable Long id) {
        sensorRepository.deleteById(id);
    }

    private SensorDTO convertToDTO(Sensor sensor) {
        SensorDTO dto = new SensorDTO();
        dto.setId(sensor.getId());
        dto.setNome(sensor.getNome());
        dto.setLocalizacao(sensor.getLocalizacao());
        dto.setTipo(sensor.getTipo());
        dto.setStatus(sensor.getStatus());
        dto.setDataInstalacao(sensor.getDataInstalacao());
        dto.setUltimaManutencao(sensor.getUltimaManutencao());
        return dto;
    }

    private Sensor convertToEntity(SensorDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setId(dto.getId());
        sensor.setNome(dto.getNome());
        sensor.setLocalizacao(dto.getLocalizacao());
        sensor.setTipo(dto.getTipo());
        sensor.setStatus(dto.getStatus());
        sensor.setDataInstalacao(dto.getDataInstalacao());
        sensor.setUltimaManutencao(dto.getUltimaManutencao());
        return sensor;
    }
}