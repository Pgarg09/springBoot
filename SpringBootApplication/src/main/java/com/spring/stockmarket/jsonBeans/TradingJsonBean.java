package com.spring.stockmarket.jsonBeans;

public class TradingJsonBean {
	
	private String stock;
	private String shareQuantity;
	private String buySell;
	private String tradePrice;
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getShareQuantity() {
		return shareQuantity;
	}
	public void setShareQuantity(String shareQuantity) {
		this.shareQuantity = shareQuantity;
	}
	public String getBuySell() {
		return buySell;
	}
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	public String getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(String tradePrice) {
		this.tradePrice = tradePrice;
	}

}
