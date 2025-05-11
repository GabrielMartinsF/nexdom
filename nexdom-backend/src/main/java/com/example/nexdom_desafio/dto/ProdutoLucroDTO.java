package com.example.nexdom_desafio.dto;

import java.math.BigDecimal;

import com.example.nexdom_desafio.enums.TipoProduto;

public class ProdutoLucroDTO {
    private Long id;
    private String descricao;
    private TipoProduto tipo;
    private int quantidadeSaida;
    private BigDecimal lucro;

    public ProdutoLucroDTO(Long id, String descricao, TipoProduto tipo, int quantidadeSaida, BigDecimal lucro) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.quantidadeSaida = quantidadeSaida;
        this.lucro = lucro;
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

	public int getQuantidadeSaida() {
		return quantidadeSaida;
	}

	public void setQuantidadeSaida(int quantidadeSaida) {
		this.quantidadeSaida = quantidadeSaida;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getLucro() {
		return lucro;
	}

	public void setLucro(BigDecimal lucro) {
		this.lucro = lucro;
	}
}