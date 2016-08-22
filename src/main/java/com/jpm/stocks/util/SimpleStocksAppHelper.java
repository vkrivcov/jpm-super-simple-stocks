package com.jpm.stocks.util;

import com.jpm.stocks.backend.BackendTradeManager;
import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.Trade;
import com.jpm.stocks.model.TradeType;
import com.jpm.stocks.services.ServiceStock;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * SimpleStocksApp helper class that will handle most of the business logic
 *
 * @author Vadims Krivcovs
 */
public class SimpleStocksAppHelper {

	private ServiceStock serviceStock;
	private BackendTradeManager backendTradeManager;
	private Logger logger = Logger.getLogger(BackendTradeManager.class);

	/**
	 * Main constructor
	 *
	 * @param serviceStock
	 * @param backendTradeManager
	 */
	public SimpleStocksAppHelper(ServiceStock serviceStock, BackendTradeManager backendTradeManager) {
		this.serviceStock = serviceStock;
		this.backendTradeManager = backendTradeManager;
	}

	/**
	 * Helper method that returns calculated dividend yield value
	 *
	 * @param  stock stock object
	 * @return generated dividend value
	 * @throws Exception if price value will be illegal
	 */
	public double processDividendYield(Stock stock) throws Exception {

		// store return value
		double dDividendYield;

		logger.info("\n\nPlease enter a price value for a " + stock.getStrStockSymbol() + " (in pennies)");

		// get price value
		double dPrice = getUserPriceInput();

		// calculate dividend yield
		dDividendYield = this.serviceStock.dCalculateDividendYield(stock, dPrice);

		// return calculated dividend yield
		return dDividendYield;
	}

	/**
	 * Helper method that returns calculated P/E Ratio value
	 *
	 * @param stock stock object
	 * @return P/E Ratio value
	 * @throws Exception if price value will be illegal
	 */
	public double processPERatio(Stock stock) throws Exception {

		// store return value
		double dPERatio;

		logger.info("\n\nPlease enter a price value for a " + stock.getStrStockSymbol() + " (in pennies)");

		// get price value
		double dPrice = getUserPriceInput();

		// calculate PE ratio
		dPERatio = this.serviceStock.dCalculatePERatio(stock, dPrice);

		// return calculated PE ratio
		return dPERatio;
	}

	/**
	 * Helper method that will prepare a trade object
	 *
	 * @param stock stock object
	 * @return trade object
	 * @throws Exception if price value will be illegal and if quantity of shares value will be illegal
	 */
	public Trade processRecordATrade(Stock stock) throws Exception {

		// store return value
		Trade trade;

		logger.info("\n\nPlease enter quantity of shared you want to BUY or SELL for " + stock.getStrStockSymbol());
		double dUserSharesQuantity = getUserInputForSharesQuantity();

		logger.info("\n\nPlease enter a price value for a " + stock.getStrStockSymbol() + " (in pennies)");

		// get price value
		double dPrice = getUserPriceInput();

		String strMessage = "\n\nWould you like to BUY OR SELL:\n";
		strMessage += "1. BUY\n";
		strMessage += "2. SELL\n";
		strMessage += "\n";

		logger.info(strMessage);

		// get user trade type
		TradeType tradeType = getUserInputForTradeType();

		// create new trade
		trade = new Trade(stock, getCurrentDate(), dUserSharesQuantity, tradeType, dPrice);

		// return trade
		return trade;
	}

	/**
	 * Helper method that will prepare list of trades for a specified stock for the last 15 minutes). List of trades
	 * will be used later to calculate Volume Weighted Stock Price for a specified stock
	 *
	 * @param stock stock object
	 * @return list of trades for a specified stock that happened within last 15 minutes
	 * @throws Exception
	 */
	public List<Trade> processVolumeWeightedStockPrice(Stock stock) throws Exception {

		// get list of trades for the last 15 minutes
		List<Trade> listTrades = backendTradeManager.getTrades(stock, 15);

		// make sure that at least some of the trades exist
		if(listTrades.size() == 0) {
			throw new Exception("no trades were previously recorded for " + stock.getStrStockSymbol());
		}

		// return trades
		return listTrades;
	}

	/**
	 * Get user price entry (user input will be validated before it will be returned)
	 *
	 * @return validated user input for a price
	 * @throws Exception if user input for a price will be illegal
	 */
	public double getUserPriceInput() throws Exception {

		// store return value
		double dPrice;

		// get the keyboard entry from the user
		String strUserChoice = getUserInput();

		// try to parse the price input from the user
		try {
			dPrice = Double.parseDouble(strUserChoice);

			// make sure that the price is greater than 0
			if(dPrice <= 0) {
				throw new Exception("price must be larger than 0 (pennies)");
			}

		} catch (NumberFormatException e1) {
			throw new Exception("invalid value for a price - " + strUserChoice + " (please try again later)");

		} catch (Exception e2) {
			throw new Exception("failed to analyse price - " + e2.getMessage());
		}

		// return price value
		return dPrice;
	}

	/**
	 * Get user shares quantity entry (user input will be validated before it will be returned)
	 *
	 * @return validated user input for a price
	 * @throws Exception if user input for a quantity will be illegal
	 */
	public int getUserInputForSharesQuantity() throws Exception {

		// store return value
		int nSharesQuantity;

		// get the keyboard entry from the user
		String strUserChoice = getUserInput();

		// try to parse the price input from the user
		try {
			nSharesQuantity = Integer.parseInt(strUserChoice);

			// make sure that the price is greater than 0
			if(nSharesQuantity <= 0) {
				throw new Exception("shares quantity must be larger than 0");
			}

		} catch (NumberFormatException e1) {
			throw new Exception("invalid value for a quantity of shares - " + strUserChoice + " (please try again later)");

		} catch (Exception e2) {
			throw new Exception("failed to analyse quantity - " + e2.getMessage());
		}

		// return quantity value
		return nSharesQuantity;
	}

	/**
	 * General user input from the console
	 *
	 * @return strUserChoice
	 */
	public String getUserInput() {

		// store return value
		String strUserChoice;

		// create new scanner
		Scanner scanner = new Scanner(System.in);

		// get the keyboard entry from the user
		strUserChoice = scanner.nextLine();

		// return user input as it is
		return strUserChoice;
	}

	/**
	 * Helper function that will get input for the trade type i.e. BUY or SELL
	 *
	 * @return trade type value i.e. BUY or SELL
	 * @throws Exception if invalid option will be selected from the menu
	 */
	public TradeType getUserInputForTradeType() throws Exception {

		// store return value
		TradeType tradeType;

		// get user input
		String strUserInput = getUserInput();

		// we need to validate the input
		try {
			// define what we want to do
			int nUserMenuInput = Integer.parseInt(strUserInput);

			switch (nUserMenuInput) {
				case 1:
					tradeType = TradeType.BUY;

					break;

				case 2:
					tradeType = TradeType.SELL;

					break;

				default:
					throw new Exception("please specify whether you want to BUY or SELL stock");
			}
		} catch (NumberFormatException e1) {
			throw new Exception("invalid option for BUY or SELL (please select corresponding option from the menu i.e. (1) for BUY and (2) for SELL and try again)");

		} catch (Exception e2) {
			throw new Exception(e2.getMessage());
		}

		// return trade type
		return tradeType;
	}

	/**
	 * Little helper utility that will return formatted time since epoch
	 *
	 * @return time since epoch (in seconds)
	 */
	public long getCurrentDate() {
		long lUnixTime = System.currentTimeMillis() / 1000L;
		return lUnixTime;
	}

	/**
	 * Common function that will help to display a log entry with a simple but
	 * understandable formatting
	 *
	 * @param logMessageType
	 * @param strMessage
	 */
	public void displayLogMessage(LogMessageType logMessageType, String strMessage) {

		// prepare log message
		String strLogMessage = "\n\n\n*********************************\n\n" + strMessage + "\n\n*********************************\n\n\n";

		if(logMessageType.equals(LogMessageType.DEBUG)) {
			logger.debug(strLogMessage);

		} else if (logMessageType.equals(LogMessageType.INFO)) {
			logger.info(strLogMessage);

		} else if (logMessageType.equals(LogMessageType.WARN)) {
			logger.warn(strLogMessage);

		} else if (logMessageType.equals(LogMessageType.ERROR)) {
			logger.error(strLogMessage);

		} else if (logMessageType.equals(LogMessageType.TRACE)) {
			logger.trace(strLogMessage);

		} else {
			logger.fatal(strLogMessage);

		}
	}
}
