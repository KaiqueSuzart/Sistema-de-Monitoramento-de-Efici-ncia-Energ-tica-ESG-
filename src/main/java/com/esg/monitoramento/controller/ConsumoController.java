package com.esg.monitoramento.controller;

import com.esg.monitoramento.dto.ConsumoDTO;
import com.esg.monitoramento.service.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<String> registrarConsumo(@RequestBody ConsumoDTO consumoDTO) {
        try {
            consumoService.registrarConsumo(consumoDTO);
            return ResponseEntity.ok("Consumo registrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 