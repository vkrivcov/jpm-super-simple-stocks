package com.jpm.stocks.backend;

import com.jpm.stocks.model.Stock;
import com.jpm.stocks.interfaces.IStockManager;

import java.util.*;

/**
 * Main stock manager that provides implementations for stock manipulations
 *
 * @author Vadims Krivcovs
 */
public class BackendStockManager implements IStockManager {

	private Map<String, Stock> mapStock = new LinkedHashMap<String, Stock>();

	/**
	 * Add a stock
	 *
	 * @param stock
	 */
	public void addStock(Stock stock) {

		mapStock.put(stock.getStrStockSymbol(), stock);
	}

	/**
	 * Get stock based on stock symbol
	 *
	 * @param  strStockSymbol stock symbol
	 * @return stock object or null if it will not be found
	 */
	public Stock getStock(String strStockSymbol) {

		// store return value
		Stock stock = null;

		if(mapStock.containsKey(strStockSymbol)) {
			stock = mapStock.get(strStockSymbol);
		}

		// return stock
		return stock;
	}

	/**
	 * Return list of stock objects
	 *
	 * @return list of stock objects
	 */
	public List<Stock> getAllStocks() {
		List<Stock> listStocks = new ArrayList<Stock>(mapStock.values());
		return listStocks;
	}

	/**
	 * Return map of stock objects (useful in some cases)
	 *
	 * @return map of stock objects
	 */
	public Map<String, Stock> getMapStock() {
		return mapStock;
	}
}
