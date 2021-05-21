package br.inatel.stock.quote.manager.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import br.inatel.stock.quote.manager.models.Quote;

public class QuoteForm {
	
	@NotEmpty @NotNull
    private String id;
    
	@NotEmpty @NotNull
    Map<String,String> quotes = new HashMap<String,String>();

    public String getId() {
        return id;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public List<Quote> converter() {
        List<Quote> quoteList = new ArrayList<>();

        for (Map.Entry<String, String> quote : this.quotes.entrySet()) {
            LocalDate dataCriacao = LocalDate.parse(quote.getKey());
            BigDecimal preco = new BigDecimal(quote.getValue());

            quoteList.add(new Quote(this.id, dataCriacao, preco));
        }

        return quoteList;
    }


}
