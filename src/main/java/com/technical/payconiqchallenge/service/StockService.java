package com.technical.payconiqchallenge.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.technical.payconiqchallenge.dto.StockDTO;
import com.technical.payconiqchallenge.entity.Stock;
import com.technical.payconiqchallenge.repository.StockRepository;

@Component
public class StockService implements IStockService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<StockDTO> getAllStock(Pageable p) {
		Page<Stock> stockList = stockRepository.findAll(p);
		Type listType = new TypeToken<List<StockDTO>>() {
		}.getType();
		List<StockDTO> stockDTO = mapper.map(stockList.getContent(), listType);
		return stockDTO;
	}

	@Override
	public StockDTO getStockById(int id) {
		Optional<Stock> stock = stockRepository.findAllById(id);
		if (stock.isPresent()) {
			StockDTO stockDTO = mapper.map(stock.get(), StockDTO.class);
			return stockDTO;
		}
		return new StockDTO();
	}

	@Override
	public int addStock(StockDTO stockDTO) {
		Stock stock = mapper.map(stockDTO, Stock.class);
		return stockRepository.save(stock).getId();
	}

	@Override
	public int updateStock(int id, int price) {
		Optional<Stock> stock = stockRepository.findAllById(id);

		if (stock.isPresent()) {
			stock.get().setCurrentPrice(price);
			return stockRepository.save(stock.get()).getId();
		}
		return 0;
	}

	@Override
	public ResponseEntity<?> deleteStock(int id) {
		try {
			stockRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

	}

}
