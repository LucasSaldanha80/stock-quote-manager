package br.inatel.stock.quote.manager.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.stock.quote.manager.Repository.QuoteRepository;
import br.inatel.stock.quote.manager.models.Quote;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuoteService {
	
	@Autowired
	private QuoteRepository quoteRepository;

	public void saveQuote(List<Quote> quotes) {
		
		log.debug("salvar {}", quotes);
		String response = "Quote salva";
		log.info(response);
		
		quoteRepository.saveAll(quotes);
	}

	public List<Quote> findByStockId(String stockId) {
		
		log.debug("findByStockId {}", stockId);
		String response = "Achar pelo id: " + stockId;
		log.info(response);
		
		return quoteRepository.findByStockId(stockId);
	}
}
