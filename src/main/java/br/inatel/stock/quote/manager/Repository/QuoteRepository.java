package br.inatel.stock.quote.manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.stock.quote.manager.models.Quote;

public interface QuoteRepository extends JpaRepository<Quote, String> {

	List<Quote> findByStockId(String stockId);
}
