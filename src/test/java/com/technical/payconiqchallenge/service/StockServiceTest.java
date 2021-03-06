package com.technical.payconiqchallenge.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.technical.payconiqchallenge.dto.StockDTO;
import com.technical.payconiqchallenge.entity.Stock;
import com.technical.payconiqchallenge.repository.StockRepository;

/**
 * @author Bharath
 *
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockServiceTest {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private IStockService stockService;

	@BeforeAll
	public void setUp() {
		Stock stock = new Stock(1, "Test1", 1234, new Date());
		Stock stock1 = new Stock(2, "Test2", 1234, new Date());
		Stock stock2 = new Stock(3, "Test3", 1234, new Date());
		Stock stock3 = new Stock(4, "Test4", 1234, new Date());
		Stock stock4 = new Stock(5, "Test5", 1234, new Date());
		stockRepository.save(stock);
		stockRepository.save(stock1);
		stockRepository.save(stock2);
		stockRepository.save(stock3);
		stockRepository.save(stock4);
	}

	@Test
	public void testGetAllStockByPaginationOf5() {
		Pageable pageable = PageRequest.of(0, 5);
		assertTrue(stockService.getAllStock(pageable).getBody().size() == 5);
	}

	@Test
	public void testGetByIdValidStock() {
		assertTrue(stockService.getStockById(1001).getBody().getId() == 1001);
	}

	@Test
	public void testGetByIdInvalidValidStock() {
		assertTrue(stockService.getStockById(567).getBody() == null);
		assertTrue(stockService.getStockById(567).getStatusCode().equals(HttpStatus.NOT_FOUND));
		

	}

	@Test
	public void testCreateStock() {
		StockDTO stock = new StockDTO("Test6", 12345);
		int id = stockService.addStock(stock).getBody();

		assertTrue(stockService.getStockById(id).getBody().getName().equals("Test6"));

	}

	@Test
	public void updateByValidIdStock() {
		stockService.updateStock(1001, 345);
		assertTrue(stockService.getStockById(1001).getBody().getCurrentPrice() == 345);
	}

	@Test
	public void updateByInValidIdStock() {
		stockService.updateStock(1, 345);
		assertTrue(stockService.updateStock(1, 345).getBody().contains("value not found in database"));
	}

	@Test
	public void deleteByValidIdStock() {
		assertTrue(stockService.deleteStock(1014).getStatusCode().equals(HttpStatus.NO_CONTENT));
	}

	@Test
	public void deleteByInValidIdStock() {
		assertTrue(stockService.deleteStock(1).getStatusCode().equals(HttpStatus.NOT_FOUND));
	}

	@AfterAll
	public void delete() {
		stockService.deleteStock(1014);
		stockService.deleteStock(1015);
		stockService.deleteStock(1016);
		stockService.deleteStock(1017);
		stockService.deleteStock(1018);
		stockService.deleteStock(1019);
		stockService.deleteStock(1020);
	}

}
