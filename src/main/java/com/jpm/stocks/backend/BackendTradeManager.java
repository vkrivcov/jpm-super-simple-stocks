package com.jpm.stocks.backend;

import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.Trade;
import com.jpm.stocks.interfaces.ITradeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Main trade manager that provides implementations for trade manipulations
 *
 * @author Vadims Krivcovs
 */
public class BackendTradeManager implements ITradeManager {

	private List<Trade> listTrades = new ArrayList<Trade>();

	/**
	 * Add a trade
	 *
	 * @param trade trade object
	 */
	public void addTrade(Trade trade) {

		listTrades.add(trade);
	}

	/**
	 * Get trades based on stock and trade times (i.e. return all trades for a specific stock for the last 15 minutes)
	 *
	 * @param  stock     stock object
	 * @param  nMinutes  last N minutes (i.e. last 15 minutes), -1 means return all trades disregarding trade times
	 * @return list of trades for the last N Minutes (or all trades of nMinutes will be set to -1)
	 */
	public List<Trade> getTrades(Stock stock, int nMinutes) {

		// store temporary list to return
		List<Trade> listTradesTemp = new ArrayList<Trade>();

		// if nMinutes will be set to -1 then we will return trades for stock
		// for ALL times
		boolean bReturnAllStockTrades = false;
		if(nMinutes == -1) {
			bReturnAllStockTrades = true;
		}

		//
		// loop over recorded trades
		//
		for(Trade tradeRecorded : listTrades) {
			// first of all check if current stock matches the trade
			if(tradeRecorded.getStock().getStrStockSymbol().equals(stock.getStrStockSymbol())) {
				// now we need to check whether we want to return stock
				// trades for ALL time of only for a specified amount of minutes
				if(bReturnAllStockTrades) {
					listTradesTemp.add(tradeRecorded);

				// otherwise check the stock traes that happened within last N minutes
				} else {
					// calculate allowed time delta
					long lAllowedTimeDelta = (System.currentTimeMillis() / 1000L) - (nMinutes * 60);

					// check the trade time
					if(lAllowedTimeDelta <= tradeRecorded.getDateTimestamp()) {
						listTradesTemp.add(tradeRecorded);
					}
				}
			}
		}

		// return list of trades
		return listTradesTemp;
	}

	/**
	 * Get list of all trades
	 *
	 * @return list of all trades
	 */
	public List<Trade> getAllTrades() {

		return listTrades;
	}
}
