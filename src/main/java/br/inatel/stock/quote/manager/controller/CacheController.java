package br.inatel.stock.quote.manager.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.CacheEvict;

import br.inatel.stock.quote.manager.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {
	
	@Autowired
	public CacheController(StockService stockService) {
		stockService.Notification();
	}
	
	@DeleteMapping
	@Transactional
	@Caching(evict = { @CacheEvict(value = "cache", allEntries = true), @CacheEvict(value = "cache", allEntries = true)})
	public ResponseEntity<?> limpaCache(){
		
		String response = "Limpa todo cache";
		log.info(response);
		
		return ResponseEntity.status(204).build();
	}
}
