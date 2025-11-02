package com.fintrack.service;

import com.fintrack.entity.Boleto;
import com.fintrack.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    public Boleto saveBoleto(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public List<Boleto> findAll() {
        return boletoRepository.findAll();
    }

    public Optional<Boleto> findById(Long id) {
        return boletoRepository.findById(id);
    }

    public List<Boleto> findByUserId(Long userId) {
        return boletoRepository.findByUserId(userId);
    }
}
