package br.inatel.stock.quote.manager.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class QuoteDto {

	private BigDecimal preco;
	private LocalDate dataCriacao;

	public QuoteDto(BigDecimal preco, LocalDate dataCriacao) {
		this.preco = preco;
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
}
