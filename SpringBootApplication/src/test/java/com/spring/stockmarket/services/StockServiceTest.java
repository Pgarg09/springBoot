package com.spring.stockmarket.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.stockmarket.bean.GBCEDataModelSingleton;
import com.spring.stockmarket.bean.StockDataModel;
import com.spring.stockmarket.constant.StockMarketConstant;
import com.spring.stockmarket.exceptions.BusinessException;
import com.spring.stockmarket.exceptions.InvalidValueException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

private static Collection<StockDataModel> stockDataModels= null;

private StockService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stockDataModels = GBCEDataModelSingleton.getStockDataModel().values();
		List<StockDataModel> dataModels = stockDataModels.stream().collect(Collectors.toList());
		Iterator<StockDataModel> iterator = dataModels.iterator();
		while(iterator.hasNext()) {
			iterator.next().setPrice(10.00);
		}
	}
	

	@Test(expected = BusinessException.class)
	public void testCalculateDividendYield_InvalidStock() {
		
		service.calculateDividendYield("ABC", 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testCalculateDividendYield_InvalidPrice() {
		
		service.calculateDividendYield("Tea", -10.0);
	}
	
	@Test
	public void testCalculateDividendYield_Common() {
		
		assertEquals("0.8", service.calculateDividendYield("Pop", 10.0));
	}
	
	@Test
	public void testCalculateDividendYield_Preferred() {
		
		assertEquals("20.0", service.calculateDividendYield("Gin", 10.0));
	}

	@Test(expected = BusinessException.class)
	public void testCalculatePERatio_InvalidStock() {
				
		service.calculatePERatio("ABC", 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testCalculatePERatio_InvalidPrice() {
				
		service.calculatePERatio("Pop", -12.12);
	}
	
	@Test
	public void testCalculatePERatio() {
				
		assertEquals("1.51", service.calculatePERatio("Pop", 12.12));
	}
	

	@Test(expected = BusinessException.class)
	public void testRecordTrade_InvalidStock() {
		
		service.recordTrade("ABCD", 12, StockMarketConstant.BUY_INDICATOR, new Date(), 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testRecordTrade_InvalidQuantity() {
		
		service.recordTrade("Pop", -12, StockMarketConstant.BUY_INDICATOR, new Date(), 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testRecordTrade_InvalidBuySell() {
		
		service.recordTrade("Pop", 12, "Purchase",new Date(), 12.12);
	}
	
	@Test(expected = InvalidValueException.class)
	public void testRecordTrade_InvalidPrice() {
		
		service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(), -12.12);
	}
	
	
	@Test
	public void testRecordTrade() {
		
		assertTrue(service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(), 12));
	}
	

	@Test
	public void testCalVolWeightedStockPrice_NoTrade() {
		long time = new Date().getTime();
		
		service.getTradingModelList().clear();
		service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-20*60*1000), 12);
		service.recordTrade("Tea", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-16*60*1000), 12);
		service.recordTrade("Gin", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-17*60*1000), 12);
		service.recordTrade("Joe", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-18*60*1000), 12);
		assertEquals("0.00", service.calVolWeightedStockPrice());
		
		
	}
	
	@Test
	public void testCalVolWeightedStockPrice() {
		long time = new Date().getTime();
		
		service.getTradingModelList().clear();
		service.recordTrade("Pop", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-10*60*1000), 12);
		service.recordTrade("Tea", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-11*60*1000), 12);
		service.recordTrade("Gin", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-5*60*1000), 12);
		service.recordTrade("Joe", 12, StockMarketConstant.BUY_INDICATOR, new Date(time-2*60*1000), 12);
		assertEquals("12", service.calVolWeightedStockPrice());
		
		
	}

	@Test
	public void testGetMeanOfPrices() {
		
		assertEquals("316.23", service.getMeanOfPrices());
	}

	@Autowired
	public void setService(StockService service) {
		this.service = service;
	}

}
