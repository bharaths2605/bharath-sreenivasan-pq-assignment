package com.technical.payconiqchallenge.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.technical.payconiqchallenge.dto.StockDTO;

/**
 * @author Bharath
 *
 */
@Service
public interface IStockService {

	public ResponseEntity<List<StockDTO>> getAllStock(Pageable p);

	public ResponseEntity<Integer> addStock(StockDTO stockDTO);

	public ResponseEntity<StockDTO> getStockById(int id);

	public ResponseEntity<String> updateStock(int id, int price);

	public ResponseEntity<?> deleteStock(int id);

}
