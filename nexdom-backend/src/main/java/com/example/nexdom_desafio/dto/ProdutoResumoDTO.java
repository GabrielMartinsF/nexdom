package com.example.nexdom_desafio.dto;

import java.math.BigDecimal;

import com.example.nexdom_desafio.enums.TipoProduto;

public class ProdutoResumoDTO {
    private Long id;
    private String descricao;
    private BigDecimal valorFornecedor;
    private TipoProduto tipo;
    private int quantidadeDisponivel;
    private int quantidadeSaida;

    public ProdutoResumoDTO(Long id, String descricao, BigDecimal valorFornecedor, TipoProduto tipo, int quantidadeDisponivel, int quantidadeSaida) {
        this.id = id;
        this.descricao = descricao;
        this.valorFornecedor = valorFornecedor;
        this.tipo = tipo;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeSaida = quantidadeSaida;
    }

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

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public int getQuantidadeSaida() {
		return quantidadeSaida;
	}

	public void setQuantidadeSaida(int quantidadeSaida) {
		this.quantidadeSaida = quantidadeSaida;
	}

	public BigDecimal getValorFornecedor() {
		return valorFornecedor;
	}

	public void setValorFornecedor(BigDecimal valorFornecedor) {
		this.valorFornecedor = valorFornecedor;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}
}