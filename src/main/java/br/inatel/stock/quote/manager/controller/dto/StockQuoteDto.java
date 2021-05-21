package br.inatel.stock.quote.manager.controller.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.inatel.stock.quote.manager.models.Quote;

public class StockQuoteDto {
	
	private String id;
	
	Map<String, String> quotes = new HashMap<String, String>();

	public StockQuoteDto(String stockId, List<Quote> quotes) {
		this.id = stockId;
		this.mapQuotes(quotes);
	}

	public String getId() {
		return id;
	}

	public Map<String, String> getQuotes(){
		return quotes;
	}

	private void mapQuotes(List<Quote> quotes) {
		quotes.forEach(quote -> {
			String dataCriacao = quote.getDataCriacao().toString();
			String preco = quote.getPreco().toBigInteger().toString();
			
			this.getQuotes().put(dataCriacao, preco);
		});
	}

}
