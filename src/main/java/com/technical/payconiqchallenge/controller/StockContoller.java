package com.technical.payconiqchallenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technical.payconiqchallenge.dto.StockDTO;
import com.technical.payconiqchallenge.service.IStockService;

/**
 * @author Bharath
 *
 */
@RestController
@RequestMapping("/api")
public class StockContoller {

	@Autowired
	private IStockService stockService;

	@GetMapping(value = "/stocks")
	public ResponseEntity<List<StockDTO>> getAllStocks(Pageable p) {
		return stockService.getAllStock(p);
	}
	
	@GetMapping(value = "/stocks/{id}")
	public ResponseEntity<StockDTO> getStockById(@PathVariable int id) {
		return stockService.getStockById(id);

	}
	
	@PostMapping("/stocks")
	public ResponseEntity<Integer> addStock(@Valid @RequestBody StockDTO stockDTO) {
		return stockService.addStock(stockDTO);
	}

	

	@PatchMapping(value = "/stocks/{id}/{price}")
	public ResponseEntity<String> updateStock(@PathVariable int id, @PathVariable int price) {
		return stockService.updateStock(id, price);

	}

	@DeleteMapping(value = "/stocks/{id}")
	public ResponseEntity<?> deleteStock(@PathVariable int id) {
		return stockService.deleteStock(id);
	}

}
