package com.example.nexdom_desafio.entities;
import java.math.BigDecimal;

import com.example.nexdom_desafio.enums.TipoProduto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
    private TipoProduto tipo;
	
	private BigDecimal valorFornecedor;
	private Integer quantidadeEstoque;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValorFornecedor() {
		return valorFornecedor;
	}

	public void setValorFornecedor(BigDecimal valorFornecedor) {
		this.valorFornecedor = valorFornecedor;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	
}