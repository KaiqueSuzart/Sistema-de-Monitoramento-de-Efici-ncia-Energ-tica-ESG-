package br.com.esg.repository;

import br.com.esg.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByConsumoReferenteSensorId(Long sensorId);
    List<Alerta> findByDataGeracaoBetween(LocalDateTime inicio, LocalDateTime fim);
} 