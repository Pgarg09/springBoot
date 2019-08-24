package com.spring.stockmarket.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.stockmarket.jsonBeans.StockInput;
import com.spring.stockmarket.jsonBeans.TradingJsonBean;
import com.spring.stockmarket.services.StockService;
import com.spring.stockmarket.util.Validator;

@RestController
@RequestMapping("/stockMarket")
public class StockMarketRestController {
	
	private StockService stockservice;
	
	@PostMapping("/calculateDividend")
	public String calculateDividentYield(@RequestBody StockInput stockBody){
		
		double price = Validator.validateAndGivedouble(stockBody.getPrice());
		return stockservice.calculateDividendYield(stockBody.getStock(), price);				
	}
	
	@PostMapping("/calculatePERatio")
	public String calculatePERatio(@RequestBody StockInput stockBody) {
		
		double price = Validator.validateAndGivedouble(stockBody.getPrice());
		return stockservice.calculatePERatio(stockBody.getStock(), price);	
	}
	
	@PutMapping("/recordTrade")
	public Boolean recordTrade(@RequestBody TradingJsonBean tradingModel) {
		
		int quantity = Validator.validateAndGiveQuantity(tradingModel.getShareQuantity());
		double price = Validator.validateAndGivedouble(tradingModel.getTradePrice());
		return stockservice.recordTrade(tradingModel.getStock(), quantity, 
				tradingModel.getBuySell(), new Date(), price);	
	}
	
	@GetMapping("/calVolWeightedStockPrice")
	public String calVolWeightedStockPrice(){
		
		String value = stockservice.calVolWeightedStockPrice();
		return value;
		
	}
	
	@GetMapping("/getMeanOfPrices")
	public String getMeanOfPrices(){
		
		String value = stockservice.getMeanOfPrices();
		return value;
		
	}

	@Autowired
	public void setStockservice(StockService stockservice) {
		this.stockservice = stockservice;
	}
	

	
}
