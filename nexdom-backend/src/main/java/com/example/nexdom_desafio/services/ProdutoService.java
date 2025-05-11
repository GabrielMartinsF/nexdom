package com.example.nexdom_desafio.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexdom_desafio.entities.Produto;
import com.example.nexdom_desafio.dto.ProdutoLucroDTO;
import com.example.nexdom_desafio.entities.MovimentoEstoque;
import com.example.nexdom_desafio.enums.TipoMovimentacao;
import com.example.nexdom_desafio.enums.TipoProduto;
import com.example.nexdom_desafio.repositories.MovimentoEstoqueRepository;
import com.example.nexdom_desafio.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private MovimentoEstoqueRepository movimentacaoRepository;
	
	public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPorId(id);
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setTipo(produtoAtualizado.getTipo());
        produto.setValorFornecedor(produtoAtualizado.getValorFornecedor());
        produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        return produtoRepository.save(produto);
    }
    
    public void excluir(Long id) {
    	produtoRepository.deleteById(id);
    }

    public List<Produto> buscarPorTipo(TipoProduto tipo) {
        return produtoRepository.findByTipo(tipo);
    }

    public ProdutoLucroDTO calcularLucroPorProduto(Long idProduto) {
        Produto produto = buscarPorId(idProduto);
        List<MovimentoEstoque> saidas = movimentacaoRepository.findByProdutoAndTipo(produto, TipoMovimentacao.SAIDA);
        
        Integer totalSaidas = saidas.stream().mapToInt(m -> (m.getQuantidadeMovimentada())).sum();
        
        BigDecimal lucro = saidas.stream()
            .map(m -> (m.getValorVenda().subtract(produto.getValorFornecedor()))
                     .multiply(BigDecimal.valueOf(m.getQuantidadeMovimentada())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return new ProdutoLucroDTO(
	        		idProduto, 
	        		produto.getDescricao(), 
	        		produto.getTipo(),
	        		totalSaidas,
	        		lucro
        		);
    }
}