package com.fintrack.controller;

import com.fintrack.entity.Boleto;
import com.fintrack.service.BoletoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boletos")
public class BoletoController {
    @Autowired
    private BoletoService boletoService;

    @PostMapping
    public ResponseEntity<Boleto> createBoleto(@Valid @RequestBody Boleto boleto) {
        Boleto savedBoleto = boletoService.saveBoleto(boleto);
        return ResponseEntity.ok(savedBoleto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Boleto>> getBoletosByUser(@PathVariable Long userId) {
        List<Boleto> boletos = boletoService.findByUserId(userId);
        return ResponseEntity.ok(boletos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleto> getBoletoById(@PathVariable Long id) {
        Optional<Boleto> boleto = boletoService.findById(id);
        return boleto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
