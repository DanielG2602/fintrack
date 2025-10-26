package com.fintrack.repository;

import com.fintrack.entity.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoletoRepository extends JpaRepository <Boleto, Long> {
    List<Boleto> findByUserId (long userId);
}
