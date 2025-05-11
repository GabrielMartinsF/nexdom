package com.example.nexdom_desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexdom_desafio.entities.Produto;
import com.example.nexdom_desafio.enums.TipoProduto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByTipo(TipoProduto tipo);
}
