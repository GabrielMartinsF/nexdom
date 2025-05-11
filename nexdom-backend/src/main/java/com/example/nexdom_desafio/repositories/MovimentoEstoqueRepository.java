package com.example.nexdom_desafio.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nexdom_desafio.entities.MovimentoEstoque;
import com.example.nexdom_desafio.entities.Produto;
import com.example.nexdom_desafio.enums.TipoMovimentacao;

public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long>{

	List<MovimentoEstoque> findByProdutoAndTipo(Produto produto, TipoMovimentacao tipo);	
	
	@Query("SELECT m.quantidadeMovimentada FROM MovimentoEstoque m WHERE m.produto = :produto AND m.tipo = 'SAIDA' ") 
	Optional<Integer> sumQuantidadeSaidaByProduto(@Param("produto") Produto produto);
	 
}
