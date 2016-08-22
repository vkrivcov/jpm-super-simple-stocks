package com.jpm.stocks.interfaces;

import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.Trade;

import java.util.List;

/**
 * Main service trade interface that is providing common trade operations
 *
 * @author Vadims Krivcovs
 */
public interface ITradeManager {
	public void addTrade(Trade trade);
	public List<Trade> getTrades(Stock stock, int nMinutes);
	public List<Trade> getAllTrades();
}
