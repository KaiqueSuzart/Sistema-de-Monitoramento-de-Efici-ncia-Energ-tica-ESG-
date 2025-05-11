package br.com.fiap.esg.controller;

import br.com.fiap.esg.model.Alerta;
import br.com.fiap.esg.repository.AlertaRepository;
import br.com.fiap.esg.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AlertaController {

    @Autowired
    private AlertaRepository alertaRepository;
    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping("/alertas")
    public String listarAlertas(@RequestParam(value = "sensorId", required = false) Long sensorId, Model model) {
        List<Alerta> alertas;
        if (sensorId != null) {
            alertas = alertaRepository.findBySensorId(sensorId);
        } else {
            alertas = alertaRepository.findAll();
        }
        model.addAttribute("alertas", alertas);
        model.addAttribute("sensores", sensorRepository.findAll());
        model.addAttribute("sensorId", sensorId);
        return "alertas";
    }
} 