package com.technical.payconiqchallenge.controllerIntegrationTesting;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.payconiqchallenge.dto.StockDTO;
import com.technical.payconiqchallenge.entity.Stock;
import com.technical.payconiqchallenge.service.IStockService;

/**
 * @author Bharath
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private IStockService stockservice;

	@BeforeAll
	public void setUp() {
		StockDTO stock = new StockDTO("Test1", 1234);
		StockDTO stock1 = new StockDTO("Test2", 1234);
		StockDTO stock2 = new StockDTO("Test3", 1234);
		StockDTO stock3 = new StockDTO("Test4", 1234);
		StockDTO stock4 = new StockDTO("Test5", 1234);
		stockservice.addStock(stock);
		stockservice.addStock(stock1);
		stockservice.addStock(stock2);
		stockservice.addStock(stock3);
		stockservice.addStock(stock4);
	}

	@Test
	public void testGetAllStocksPaginationOfSize5() throws Exception {

		String result = this.restTemplate.getForObject("http://localhost:" + port + "/api/stocks?size=5", String.class);
		ObjectMapper mapper = new ObjectMapper();
		List<StockDTO> stocks = mapper.readValue(result, new TypeReference<List<StockDTO>>() {
		});
		assertTrue(stocks.size() == 5);

	}

	@Test
	public void testGetAllStocks() throws Exception {

		String result = this.restTemplate.getForObject("http://localhost:" + port + "/api/stocks", String.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Stock> stocks = mapper.readValue(result, new TypeReference<List<Stock>>() {
		});
		assertTrue(stocks.size() > 0);
	}

	@Test
	public void testGetStocksById() throws Exception {

		String result = this.restTemplate.getForObject("http://localhost:" + port + "/api/stocks/1014", String.class);
		ObjectMapper mapper = new ObjectMapper();
		StockDTO stocks = mapper.readValue(result, StockDTO.class);
		assertTrue(stocks.getId() == 1014);
	}

	@Test
	public void testGetStocksByInvalidId() throws Exception {

		String result = this.restTemplate.getForObject("http://localhost:" + port + "/api/stocks/345", String.class);
		ObjectMapper mapper = new ObjectMapper();
		StockDTO stocks = mapper.readValue(result, StockDTO.class);
		assertTrue(stocks.getId() == 0);
		assertTrue(stocks.getName() == null);
	}

	@Test
	public void testCreateStocks() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/api/stocks";
		URI uri = new URI(baseUrl);

		StockDTO stock1 = new StockDTO("testCS", 1264);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, stock1, String.class);

		assertTrue(Integer.valueOf(result.getBody()) > 0);
	}

	@Test
	public void testCreateStocksUsingAllValues() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/api/stocks";
		URI uri = new URI(baseUrl);

		StockDTO stock1 = new StockDTO(1, "testCStock", 3478, new Timestamp(new Date().getTime()));

		ResponseEntity<String> result = restTemplate.postForEntity(uri, stock1, String.class);

		assertTrue(Integer.valueOf(result.getBody()) > 0);
	}

	@Test
	public void testCreateStocksWithEmptyValue() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/api/stocks";
		URI uri = new URI(baseUrl);

		StockDTO stock1 = new StockDTO();

		ResponseEntity<?> result = restTemplate.postForEntity(uri, stock1, String.class);

		assertTrue(result.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertTrue(result.getBody().equals("name and currentPrice cannot be empty"));
	}

	@Test
	public void testCreateStocksWithEmptyNameValue() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/api/stocks";
		URI uri = new URI(baseUrl);

		StockDTO stock1 = new StockDTO(null, 1234);

		ResponseEntity<?> result = restTemplate.postForEntity(uri, stock1, String.class);

		assertTrue(result.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertTrue(result.getBody().equals("name and currentPrice cannot be empty"));
	}

	@Test
	public void testCreateStocksWithEmptyCurrentPriceValue() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/api/stocks";
		URI uri = new URI(baseUrl);

		StockDTO stock1 = new StockDTO();

		ResponseEntity<?> result = restTemplate.postForEntity(uri, stock1, String.class);

		assertTrue(result.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertTrue(result.getBody().equals("name and currentPrice cannot be empty"));
	}

	@Test
	public void testCreateStocksWithonlyNameAndCurrentPriceValue() throws Exception {

		final String baseUrl = "http://localhost:" + port + "/api/stocks";
		URI uri = new URI(baseUrl);

		StockDTO stock1 = new StockDTO("BharathTest", 123456);

		ResponseEntity<?> result = restTemplate.postForEntity(uri, stock1, String.class);

		assertTrue(Integer.valueOf(result.getBody().toString()) > 0);
	}

	@Test
	public void testUpdateStockValidData() {
		String url = "http://localhost:" + port + "/api/stocks/1015/456";
		;
		this.restTemplate.patchForObject(url, null, String.class);
		assertTrue(stockservice.getStockById(1015).getCurrentPrice() == 456);
	}

	@Test
	public void testUpdateStockInValidId() {
		String url = "http://localhost:" + port + "/api/stocks/23456/456";
		;
		String s = this.restTemplate.patchForObject(url, null, String.class);
		assertTrue(Integer.valueOf(s) == 0);
	}

	@Test
	public void testUpdateStockNullValues() {
		String url = "http://localhost:" + port + "/api/stocks";
		String s = this.restTemplate.patchForObject(url, null, String.class);
		assertTrue(s.equals("Please provide valid Parameters"));
	}

	@Test
	public void deleteStocks() throws Exception {
		if (stockservice.getStockById(1014).getId() != 0) {
			String deleteStocks = "http://localhost:" + port + "/api/stocks/1014";
			this.restTemplate.delete(deleteStocks);
			assertTrue(stockservice.getStockById(1014).getId() == 0);
		}

	}

	@Test
	public void deleteStocksInValidData() throws Exception {

		String deleteStocks = "http://localhost:" + port + "/api/stocks/1";
		restTemplate.delete(deleteStocks);
		assertTrue(stockservice.getStockById(1).getId() == 0);
	}

	@AfterAll
	public void delete() {

		stockservice.deleteStock(1014);
		stockservice.deleteStock(1015);
		stockservice.deleteStock(1016);
		stockservice.deleteStock(1017);
		stockservice.deleteStock(1018);
		stockservice.deleteStock(1019);
		stockservice.deleteStock(1020);
		stockservice.deleteStock(1021);

	}

}
