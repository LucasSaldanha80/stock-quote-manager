package br.inatel.stock.quote.manager.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class QuoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void DeveriaListarTodos() throws Exception {
		URI uri = new URI("/quote");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void DeveriaListarUm() throws Exception {
		URI uri = new URI("/quote/petr4");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void NaoDeveriaListarUmPorqueIdInvalido() throws Exception {
		URI uri = new URI("/quote/blabal");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(204));
	}

	@Test
	public void DeveriaAdicionarUmQuote() throws Exception {
		URI uri = new URI("/quote");

		Map<String, String> quotes = new HashMap<String, String>();
		quotes.put("2019-01-02", "11");

		JSONObject map = new JSONObject(quotes);

		JSONObject quote = new JSONObject();
		quote.put("quotes", map);
		quote.put("id", "petr4");

		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(quote.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(405));
	}
}
