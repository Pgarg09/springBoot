package com.spring.stockmarket.util;

import com.spring.stockmarket.constant.StockMarketConstant;
import com.spring.stockmarket.exceptions.InvalidValueException;

public class Validator {

	
	public static void checkValidPrice(final double price) {
		if(price<0.0) {
			throw new InvalidValueException(price+" is a negetive value");
		}
	}
	
	public static void checkBuySellIndicator(final String buySellIndicator) {
		if(!StockMarketConstant.BUY_INDICATOR.equals(buySellIndicator)
				&& !StockMarketConstant.SELL_INDICATOR.equals(buySellIndicator)){
			throw new InvalidValueException("Trade must be either Buy or Sell");
		}
	}
	
	public static void checkValidQuantity(final int quantity) {
		if(quantity<0) {
			throw new InvalidValueException(quantity+" is a negetive value");
		}
	}
	
	public static double validateAndGivedouble(String price) {
		try {
			double priceValue = Double.parseDouble(price);
			return priceValue;
		} catch (Exception e) {
			throw new InvalidValueException("Invalid price value");
		}
	}
	
	public static int validateAndGiveQuantity(String quantity) {
		try {
			int priceValue = Integer.parseInt(quantity);
			return priceValue;
		} catch (Exception e) {
			throw new InvalidValueException("Invalid price value");
		}
	}
}
