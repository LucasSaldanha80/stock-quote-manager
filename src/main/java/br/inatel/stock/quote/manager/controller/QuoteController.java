package br.inatel.stock.quote.manager.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.inatel.stock.quote.manager.controller.dto.StockDto;
import br.inatel.stock.quote.manager.controller.dto.StockQuoteDto;
import br.inatel.stock.quote.manager.controller.form.QuoteForm;
import br.inatel.stock.quote.manager.models.Quote;
import br.inatel.stock.quote.manager.service.QuoteService;
import br.inatel.stock.quote.manager.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/quote")
public class QuoteController {

	QuoteService quoteService;
	StockService stockService;
	
	@Autowired
	public QuoteController(QuoteService quoteService, StockService stockService) {
		this.quoteService = quoteService;
		this.stockService = stockService;
	}

	// listar todos
	@GetMapping
	public ResponseEntity<List<StockQuoteDto>> listAll() {

		List<StockDto> stocks = stockService.listAll(); // lista todos

		List<StockQuoteDto> stockQuote = stocks.stream().map(stock -> {

			List<Quote> quotes = quoteService.findByStockId(stock.getId());
			return new StockQuoteDto(stock.getId(), quotes);

		}).collect(Collectors.toList());

		if (stockService == null) {
			String response = "Não encontrado";
			log.warn(response);
			return ResponseEntity.status(404).body(stockQuote);
		} else {
			String response = "Tudo certo";
			log.info(response);
			return ResponseEntity.status(200).body(stockQuote);
		}

	}

	// listar específico pelo ID
	@GetMapping("/{id}")
	@Transactional // leva o banco de dados de um estado consistente a outro estado consistente
	public ResponseEntity<StockQuoteDto> GetById(@PathVariable String id) {

		List<Quote> quote = quoteService.findByStockId(id); // Lista as quotes somente id passados

		if (quote.isEmpty()) {
			String response = "Empty";
			log.info(response);
			return ResponseEntity.status(204).body(new StockQuoteDto(id, quote));
		}else{
			String response = "Status ok";
			log.info(response);
			return ResponseEntity.ok(new StockQuoteDto(id, quote));
		}
	}

	//criar os quotes dentro do id especificado
	@PostMapping("/{id}")
	@Transactional // leva o banco de dados de um estado consistente a outro estado consistente
	public ResponseEntity<StockQuoteDto> adicionar(@RequestBody @Valid QuoteForm form,
			UriComponentsBuilder uriBuilder) {

		StockDto stockDto = stockService.GetById(form.getId());

		if (stockDto == null) {
			String response = "Not Found";
			log.warn(response);
			return ResponseEntity.status(404).build();
		} else {
			quoteService.saveQuote(form.converter());

			@SuppressWarnings("unused")
			URI uri = uriBuilder.path("/quote/{id}").buildAndExpand(form.getId()).toUri();

			List<Quote> quotes = quoteService.findByStockId(form.getId());

			String response = "Status ok";
			log.info(response);
			return ResponseEntity.status(201).body(new StockQuoteDto(form.getId(), quotes));
		}

	}

}
