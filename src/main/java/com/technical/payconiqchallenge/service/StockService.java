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

/**
 * @author Bharath
 *
 */
@Component
public class StockService implements IStockService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private ModelMapper mapper;

	/**
	 * getAllStock
	 * 
	 * @param Pageable
	 * @return List<StockDTO>
	 */
	@Override
	public ResponseEntity<List<StockDTO>> getAllStock(Pageable p) {
		try {
			Page<Stock> stockList = stockRepository.findAll(p);
			Type listType = new TypeToken<List<StockDTO>>() {
			}.getType();
			List<StockDTO> stockDTOList = mapper.map(stockList.getContent(), listType);
			return ResponseEntity.status(HttpStatus.OK).body(stockDTOList);
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	/**
	 * getStockById
	 * 
	 * @param id
	 * @return StockDTO
	 *
	 */
	@Override
	public ResponseEntity<StockDTO> getStockById(int id) {
		StockDTO stockDTO = null;
		try {
			Optional<Stock> stock = stockRepository.findAllById(id);
			if (stock.isPresent()) 
			{
				stockDTO = mapper.map(stock.get(), StockDTO.class);
				return ResponseEntity.status(HttpStatus.OK).body(stockDTO);
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stockDTO);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}

	}

	/**
	 * addStock
	 * 
	 * @param StockDTO
	 * @return id
	 */
	@Override
	public ResponseEntity<Integer> addStock(StockDTO stockDTO) {
		try {
			Stock stock = mapper.map(stockDTO, Stock.class);
			int id = stockRepository.save(stock).getId();
			return ResponseEntity.status(HttpStatus.OK).body(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}

	/**
	 * updateStock
	 * 
	 * @param id , @param price
	 * @return id
	 */
	@Override
	public ResponseEntity<String> updateStock(int id, int price) {
		try {
			Optional<Stock> stock = stockRepository.findAllById(id);
			if (stock.isPresent()) {
				StockDTO stockDTO = mapper.map(stock.get(), StockDTO.class);
				stockDTO.setCurrentPrice(price);
				Stock stockUpdate = mapper.map(stockDTO, Stock.class);
				stockRepository.save(stockUpdate);
				return ResponseEntity.status(HttpStatus.OK).body("id "+ id + " has been updated");

			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id " + id + " value not found in database");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Exception Occured while updating the stock");
		}

	}

	/**
	 * deleteStock
	 * 
	 * @param id
	 * @return httpStatus
	 */
	@Override
	public ResponseEntity<?> deleteStock(int id) {
		try {
			stockRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
