package com.technical.payconiqchallenge.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.technical.payconiqchallenge.dto.StockDTO;

@Service
public interface IStockService {

	public List<StockDTO> getAllStock(Pageable p);

	public int addStock(StockDTO stockDTO);

	public StockDTO getStockById(int id);

	public int updateStock(int id, int price);

	public ResponseEntity<?> deleteStock(int id);

}
