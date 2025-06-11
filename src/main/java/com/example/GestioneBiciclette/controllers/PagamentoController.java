package com.example.GestioneBiciclette.controllers;


import com.example.GestioneBiciclette.models.Pagamento;
import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;
import com.example.GestioneBiciclette.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Pagamento>> findAllPagamenti(){
        return ResponseEntity.ok(pagamentoService.findAllPagamenti());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Pagamento> findPagamentoById(@PathVariable Long id){
        return ResponseEntity.ok(pagamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pagamento> processaPagamento(@RequestParam Long id, @RequestParam TipoPagamento tipoPagamento, @RequestParam Double kmPercorsi){
        return ResponseEntity.ok(pagamentoService.processaPagamento(id, tipoPagamento, kmPercorsi));
    }

}
