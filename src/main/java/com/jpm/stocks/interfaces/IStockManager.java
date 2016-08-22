package com.jpm.stocks.interfaces;

import com.jpm.stocks.model.Stock;

import java.util.List;

/**
 * Main service stock interface that is providing common stock operations
 *
 * @author Vadims Krivcovs
 */
public interface IStockManager {
	public void addStock(Stock stock);
	public Stock getStock(String strStockSymbol);
	public List<Stock> getAllStocks();
}
