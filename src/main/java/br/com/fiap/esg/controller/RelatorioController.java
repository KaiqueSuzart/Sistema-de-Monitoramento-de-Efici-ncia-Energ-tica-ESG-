package br.com.fiap.esg.controller;

import br.com.fiap.esg.model.Consumo;
import br.com.fiap.esg.model.Sensor;
import br.com.fiap.esg.repository.ConsumoRepository;
import br.com.fiap.esg.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RelatorioController {

    @Autowired
    private ConsumoRepository consumoRepository;
    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping("/relatorio")
    public String relatorio(Model model) {
        List<Sensor> sensores = sensorRepository.findAll();
        model.addAttribute("sensores", sensores);
        model.addAttribute("consumos", consumoRepository.findAll());
        return "relatorio";
    }
} 