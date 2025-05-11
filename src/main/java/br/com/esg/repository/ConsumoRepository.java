package br.com.esg.repository;

import br.com.esg.model.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
    List<Consumo> findBySensorId(Long sensorId);
    List<Consumo> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
} 