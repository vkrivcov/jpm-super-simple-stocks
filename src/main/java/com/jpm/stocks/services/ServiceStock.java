package com.jpm.stocks.services;

import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.StockType;
import com.jpm.stocks.model.Trade;

import java.util.List;

/**
 * Main stock service class that provides stock calculations
 *
 * @author Vadims Krivcovs
 */
public class ServiceStock {

	/**
	 * Calculate Dividend Yield value
	 *
	 * @param stock  stock object
	 * @param dPrice price for stock
	 * @return
	 * @throws Exception if invalid stock was specified
	 */
	public double dCalculateDividendYield(Stock stock, double dPrice) throws Exception {

		// store return value
		double dDividendYield;

		// first of all get storm type from the stock object
		StockType stockType = stock.getEnumStockType();

		// make sure that the price is not less or equal to 0
		if(dPrice <= 0) {
			throw new Exception("price can not be less or equal to 0");
		}

		// based on the stock type we need to calculate either COMMON or PREFFERED dividend yield
		// if COMMON
		if (stockType == StockType.COMMON) {
			dDividendYield = stock.getdLastDivident() / dPrice;

		} else if (stockType == StockType.PREFFERED) {
			dDividendYield = (stock.getdFixedDivident() * stock.getdParValue()) / dPrice;

		} else {
			throw new Exception("invalid Stock Type " + stockType + " (valid stock types are " + java.util.Arrays.asList(StockType.values()));
		}

		// round to 2 decimals
		dDividendYield = Math.round(dDividendYield * 100.0) / 100.0;

		// return dividend yield
		return dDividendYield;
	}

	/**
	 * Calculate P/E ratio
	 *
	 * @param stock   stock object
	 * @param dPrice  price for stock
	 * @return calculated Calculate P/E ratio
	 */
	public double dCalculatePERatio(Stock stock, double dPrice) throws Exception {

		// store return value
		double dPERatio;

		// make sure that the price is not less or equal to 0
		if(dPrice <= 0) {
			throw new Exception("price can not be less or equal to 0");
		}

		// calculate dividend yield
		double dDividendYield = dCalculateDividendYield(stock, dPrice);

		// make sure that dividend yield value is not les or equal 0.0
		if(dDividendYield <= 0) {
			throw new Exception("unable to calculate P/E Ratio because dividend value for stock " + stock.getStrStockSymbol() + " is 0");
		}

		// calculate PERatio
		dPERatio = dPrice / dDividendYield;

		// round to 2 decimals
		dPERatio = Math.round(dPERatio * 100.0) / 100.0;

		// return PERatio
		return dPERatio;
	}

	/**
	 * Calculate GBCE All Share Index
	 *
	 * @param listTrades list of trades
	 * @return calculated GBCE All Share Index
	 */
	public double dCalculateGBCE(List<Trade> listTrades) throws Exception {

		// store return value
		double dProductPrice = 1.0;

		// make sure that we have trades
		if(listTrades.size() == 0) {
			throw new Exception("unable to calculate GBCE because we have no trades");
		}

		for (Trade tradeCurrent : listTrades)
		{
			dProductPrice *= tradeCurrent.getdTradePrice();
		}

		// calculate root pow
		double nRootPower = 1.0 / listTrades.size();

		// calculate Math.pow based on calculated value as well as root power
		dProductPrice = Math.pow(dProductPrice, nRootPower);

		// round to 2 decimals
		dProductPrice = Math.round(dProductPrice * 100.0) / 100.0;

		// return GBCE All Share Index
		return dProductPrice;
	}

	/**
	 * Calculate Volume Weighted Stock Price
	 *
	 * @param listTrades list of trades
	 * @return calculated volume weighted stock price
	 */
	public double dCalculateVolumeWeightedStockPrice(List<Trade> listTrades) throws Exception {

		// store return value
		double dVolumeWeightedStockPrice = 0.0;

		double dTotalTradesValue = 0.0;
		double dTotalQuantity = 0.0;

		// make sure that we have trades
		if(listTrades.size() == 0) {
			throw new Exception("unable to calculate Volume Weighted Stock Price because we have no trades");
		}

		for (Trade tradeRecorded : listTrades)
		{
			dTotalTradesValue += (tradeRecorded.getdTradePrice() * tradeRecorded.getnQuantityOfShares());
			dTotalQuantity += tradeRecorded.getnQuantityOfShares();
		}

		// calculate volume weighted stock price based on generated data
		dVolumeWeightedStockPrice = dTotalTradesValue / dTotalQuantity;

		// round to 2 decimals
		dVolumeWeightedStockPrice = Math.round(dVolumeWeightedStockPrice * 100.0) / 100.0;

		// volume weighted stock price
		return dVolumeWeightedStockPrice;
	}
}
