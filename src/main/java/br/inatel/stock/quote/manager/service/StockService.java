package br.inatel.stock.quote.manager.service;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.stock.quote.manager.controller.dto.StockDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockService {

	private String defaultUrl = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();

	@Cacheable(value = "cache")
	public List<StockDto> listAll() {

		log.debug("listaAll {}");
		String response = "Busca toda lista";
		log.info(response);

		String url = defaultUrl + "/stock";
		StockDto[] stockDto = restTemplate.getForObject(url, StockDto[].class);

		return Arrays.asList(stockDto);
	}

	@Cacheable(value = "cache")
	public StockDto GetById(String id) {
		String url = defaultUrl + "/stock" + "/" + id;
		StockDto stockDto = restTemplate.getForObject(url, StockDto.class);

		return stockDto;
	}

	public void Notification() {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject data = new JSONObject();
		data.put("host", "localhost");
		data.put("port", "8081");
		HttpEntity<String> request = new HttpEntity<String>(data.toString(), headers);
		restTemplate.postForObject(defaultUrl + "/notification", request, String.class);

	}

}
