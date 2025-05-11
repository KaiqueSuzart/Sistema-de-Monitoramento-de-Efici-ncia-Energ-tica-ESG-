package br.com.fiap.esg.controller;

import br.com.fiap.esg.model.Consumo;
import br.com.fiap.esg.model.Sensor;
import br.com.fiap.esg.repository.ConsumoRepository;
import br.com.fiap.esg.repository.SensorRepository;
import br.com.fiap.esg.dto.ConsumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;
    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping("/consumos")
    public String listarConsumos(@RequestParam(value = "sensorId", required = false) Long sensorId, Model model) {
        List<Consumo> consumos;
        if (sensorId != null) {
            consumos = consumoRepository.findBySensorId(sensorId);
        } else {
            consumos = consumoRepository.findAll();
        }
        model.addAttribute("consumos", consumos);
        model.addAttribute("sensorId", sensorId);
        return "consumos";
    }

    @GetMapping("/cadastrar-consumo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("sensores", sensorRepository.findAll());
        return "cadastrar-consumo";
    }

    @PostMapping("/cadastrar-consumo")
    public String salvarConsumo(@ModelAttribute Consumo consumo) {
        consumo.setDataMedicao(LocalDateTime.now());
        consumoRepository.save(consumo);
        return "redirect:/consumos";
    }

    @GetMapping("/consumos/{id}")
    @ResponseBody
    public ConsumoDTO buscarPorId(@PathVariable Long id) {
        Consumo consumo = consumoRepository.findById(id).orElse(null);
        if (consumo == null) return null;
        ConsumoDTO dto = new ConsumoDTO();
        dto.setId(consumo.getId());
        dto.setConsumoKwh(consumo.getConsumoKwh());
        dto.setPicoDemanda(consumo.getPicoDemanda());
        dto.setTemperatura(consumo.getTemperatura());
        dto.setDataMedicao(consumo.getDataMedicao());
        dto.setSensorNome(consumo.getSensor().getNome());
        return dto;
    }

    @DeleteMapping("/consumos/{id}")
    @ResponseBody
    public void excluir(@PathVariable Long id) {
        consumoRepository.deleteById(id);
    }

    @PutMapping("/consumos/{id}")
    @ResponseBody
    public void atualizar(@PathVariable Long id, @RequestBody ConsumoDTO dto) {
        Consumo consumo = consumoRepository.findById(id).orElseThrow();
        consumo.setConsumoKwh(dto.getConsumoKwh());
        consumo.setPicoDemanda(dto.getPicoDemanda());
        consumo.setTemperatura(dto.getTemperatura());
        consumoRepository.save(consumo);
    }
} 