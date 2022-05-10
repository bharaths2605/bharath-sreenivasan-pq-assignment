package com.technical.paycoinzchallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.paycoinzchallenge.entity.Stock;


@Repository
public interface StockRepository extends JpaRepository<Stock,Integer>{
	
   public Optional<Stock> findAllById(int id);
   
   public Optional<Stock> findAllByName(String name);
   
   

}
