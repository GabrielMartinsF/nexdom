package com.example.nexdom_desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexdom_desafio.entities.MovimentoEstoque;
import com.example.nexdom_desafio.services.MovimentoEstoqueService;

@RestController
@RequestMapping("/estoque")
public class MovimentoEstoqueController {
	
	@Autowired
    private MovimentoEstoqueService movimentoService;
	
	@GetMapping
	public List<MovimentoEstoque> lista() {
		return movimentoService.listar();
	}

    @PostMapping("/movimentarProduto")
    public MovimentoEstoque movimentar(@RequestBody MovimentoEstoque movimento) {
        return movimentoService.movimentar(movimento);
    }
}