package com.example.nexdom_desafio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexdom_desafio.entities.MovimentoEstoque;
import com.example.nexdom_desafio.enums.TipoMovimentacao;
import com.example.nexdom_desafio.error.SaldoInsuficienteException;
import com.example.nexdom_desafio.repositories.MovimentoEstoqueRepository;

@Service
public class MovimentoEstoqueService {
	
    @Autowired
    private MovimentoEstoqueRepository movimentoRepo;

    @Autowired
    private ProdutoService produtoService;
    
    public List<MovimentoEstoque> listar() {
    	return movimentoRepo.findAll();
    }

    public MovimentoEstoque movimentar(MovimentoEstoque movimento) {
        if (movimento.getTipo() == TipoMovimentacao.SAIDA) {
            if (movimento.getProduto().getQuantidadeEstoque() < movimento.getQuantidadeMovimentada()) {
                throw new SaldoInsuficienteException("Quantidade insuficiente em estoque");
            }
            movimento.getProduto().setQuantidadeEstoque(movimento.getProduto().getQuantidadeEstoque() - movimento.getQuantidadeMovimentada());
        } else {
        	movimento.getProduto().setQuantidadeEstoque(movimento.getProduto().getQuantidadeEstoque() + movimento.getQuantidadeMovimentada());
        }

        produtoService.salvar(movimento.getProduto());
        return movimentoRepo.save(movimento);
    }
}