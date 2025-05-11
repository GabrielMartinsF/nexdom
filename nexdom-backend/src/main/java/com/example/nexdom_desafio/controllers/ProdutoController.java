package com.example.nexdom_desafio.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexdom_desafio.dto.ProdutoLucroDTO;
import com.example.nexdom_desafio.dto.ProdutoResumoDTO;
import com.example.nexdom_desafio.entities.Produto;
import com.example.nexdom_desafio.enums.TipoProduto;
import com.example.nexdom_desafio.repositories.MovimentoEstoqueRepository;
import com.example.nexdom_desafio.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private MovimentoEstoqueRepository movimentacaoRepository;

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }
    
    @PutMapping("/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto) {
    	return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.excluir(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<ProdutoResumoDTO> buscarPorTipo(@PathVariable TipoProduto tipo) {
        List<Produto> produtos = produtoService.buscarPorTipo(tipo);

        return produtos.stream().map(produto -> {
            Optional<Integer> quantidadeSaida = movimentacaoRepository.sumQuantidadeSaidaByProduto(produto);

            return new ProdutoResumoDTO(
                produto.getId(),
                produto.getDescricao(),
                produto.getValorFornecedor(),
                produto.getTipo(),
                produto.getQuantidadeEstoque(),
                quantidadeSaida.isEmpty() ? 0 : quantidadeSaida.get()
            );
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}/lucro")
    public ProdutoLucroDTO calcularLucro(@PathVariable Long id) {
        return produtoService.calcularLucroPorProduto(id);
    }
}