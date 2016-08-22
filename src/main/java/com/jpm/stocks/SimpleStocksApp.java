package com.jpm.stocks;

import com.jpm.stocks.backend.BackendStockManager;
import com.jpm.stocks.backend.BackendTradeManager;
import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.StockType;
import com.jpm.stocks.model.Trade;
import com.jpm.stocks.services.ServiceStock;
import com.jpm.stocks.util.LogMessageType;
import com.jpm.stocks.util.SimpleStocksAppHelper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * main runner class
 *
 * @author Vadims Krivcovs
 */
public class SimpleStocksApp {

	private static BackendStockManager backendStockManager = new BackendStockManager();
	private static BackendTradeManager backendTradeManager = new BackendTradeManager();
	private static ServiceStock serviceStock = new ServiceStock();
	private static SimpleStocksAppHelper simpleStocksAppHelper = new SimpleStocksAppHelper(serviceStock, backendTradeManager);

	private static Logger logger = Logger.getLogger(BackendTradeManager.class);

	/**
	 * Main application runner
	 *
	 * @param args args
	 */
	public static void main(String[] args) {

		// create dummy stocks and add them to directly to stock manager
		// NOTE: all values are in pennies
		backendStockManager.addStock(new Stock("TEA", StockType.COMMON, 0, 0, 100));
		backendStockManager.addStock(new Stock("POP", StockType.COMMON, 8, 0, 100));
		backendStockManager.addStock(new Stock("ALE", StockType.COMMON, 23, 0, 60));
		backendStockManager.addStock(new Stock("GIN", StockType.PREFFERED, 8, 2, 100));
		backendStockManager.addStock(new Stock("JOE", StockType.COMMON, 13, 0, 250));

		// process initial bootstrap screen
		processBootstrapScreen();
	}

	/**
	 * Process initial welcome screen and start emulator if user entry will be
	 * satisfactory
	 */
	private static void processBootstrapScreen() {

		// store boolean flag that will indicate that a user entered a correct/incorrect value
		boolean bUserEnteredIncorrectValue = true;

		// get set of available stocks
		Set<String> setAvailableStock = backendStockManager.getMapStock().keySet();

		String strStockChoice = null;
		while(bUserEnteredIncorrectValue) {
			// first of all display a welcome message
			displayInitialWelcomeMenu(backendStockManager.getMapStock().keySet());

			// initialise new scanner
			Scanner scanner = new Scanner(System.in);

			// get the keyboard entry from the user
			// note: automatically convert to uppercase
			strStockChoice = scanner.nextLine().toUpperCase();

			if(setAvailableStock.contains(strStockChoice)) {
				bUserEnteredIncorrectValue = false;

			} else {
				String strMessage = "Stock entry " + strStockChoice + " is invalid or does not exist (available stocks are " + setAvailableStock + "\n";
				strMessage += "Please try again...";
				simpleStocksAppHelper.displayLogMessage(LogMessageType.ERROR,strMessage);
			}
		}

		// display main stock processing console
		processStockEmulator(strStockChoice, backendStockManager);
	}

	/**
	 * Process stock emulator options
	 *
	 * @param strStockChoice       selected stock type (i.e. stock symbol)
	 * @param backendStockManager  stock management object
	 */
	private static void processStockEmulator(String strStockChoice, BackendStockManager backendStockManager) {

		// store boolean flag that will indicate that a user entered a correct/incorrect value
		boolean bMainStockEmulatorRunning = true;

		// check if we ar dealing with existing stock
		while(bMainStockEmulatorRunning) {
			// display main emulator options
			displayStockManagementMenu(strStockChoice);

			if (backendStockManager.getMapStock().keySet().contains(strStockChoice)) {
				// create new scanner that will be recording emulator options
				Scanner scannerStockEmulatorInput = new Scanner(System.in);

				// declare empty message
				String strMessage;

				// get stock emulator entry
				String strStockEmulatorEntry = scannerStockEmulatorInput.nextLine().toUpperCase();
				try {
					// define what we want to do
					int nStockEmulatorEntry = Integer.parseInt(strStockEmulatorEntry);

					// get selected stock
					Stock stockSelected = backendStockManager.getStock(strStockChoice);

					// process further based on entry
					switch (nStockEmulatorEntry) {
						//
						// Calculate the dividend yield for a given stock
						//
						case 1:
							// calculate dividend yield
							double dDividendYield = simpleStocksAppHelper.processDividendYield(stockSelected);

							// log info entry
							strMessage = "Calculated Dividend Yield: " + dDividendYield;
							simpleStocksAppHelper.displayLogMessage(LogMessageType.INFO, strMessage);

							break;

						//
						// Calculate the P/E Ratio for a given stock
						//
						case 2:
							// calculate PERatio
							double dPERatio = simpleStocksAppHelper.processPERatio(stockSelected);

							// log info entry
							strMessage = "Calculated PERatio: " + dPERatio;
							simpleStocksAppHelper.displayLogMessage(LogMessageType.INFO, strMessage);

							break;

						//
						// Record a trade for stock (including time of trade, quantity of shares, buy or sell indicator and trade price)
						//
						case 3:
							// create a trade object
							Trade trade = simpleStocksAppHelper.processRecordATrade(stockSelected);

							// add trade object to trade manager
							backendTradeManager.addTrade(trade);

							// log info entry
							strMessage = "Trade added successfully\n";
							simpleStocksAppHelper.displayLogMessage(LogMessageType.INFO, strMessage);

							break;

						//
						// Calculate Volume Weighted Stock Price for a given stock
						//
						case 4:
							// prepare volume weighted stock price
							List<Trade> listTrades = simpleStocksAppHelper.processVolumeWeightedStockPrice(stockSelected);

							// calculate actual volume weighted stock price
							double dVolumeWeightedStockPrice = serviceStock.dCalculateVolumeWeightedStockPrice(listTrades);

							// log info entry
							strMessage = "Calculated volume weighted stock price: " + dVolumeWeightedStockPrice;
							simpleStocksAppHelper.displayLogMessage(LogMessageType.INFO, strMessage);

							break;

						//
						// Calculate GBCE All Share Index using the geometric mean of prices for all stocks
						//
						case 5:
							// calculate the value
							double dGBCE = serviceStock.dCalculateGBCE(backendTradeManager.getAllTrades());

							// log info entry
							strMessage = "GBCE All Share Index using the geometric mean of prices for all stocks: " + dGBCE;
							simpleStocksAppHelper.displayLogMessage(LogMessageType.INFO, strMessage);

							break;

						//
						// Chose different Stock
						//
						case 6:
							// call back to the bootstrap screen
							processBootstrapScreen();

							break;

						//
						// Exit the emulator
						//
						case 7:
							// set application stop flag
							bMainStockEmulatorRunning = false;

							break;

						default:
							// log warning message if emulator entry will not be recognisable
							strMessage = "Unknown or unsupported emulator entry (plese try again)";
							simpleStocksAppHelper.displayLogMessage(LogMessageType.WARN, strMessage);

							break;
					}

				} catch (InterruptedException e1) {
					// set application stop flag
					bMainStockEmulatorRunning = false;


				} catch (NumberFormatException e2) {
					strMessage = "Invalid choice entry " + strStockEmulatorEntry + " please try again";
					simpleStocksAppHelper.displayLogMessage(LogMessageType.ERROR, strMessage);

				} catch (Exception e3) {
					strMessage = "Error occurred - " + e3.getMessage();
					simpleStocksAppHelper.displayLogMessage(LogMessageType.ERROR, strMessage);
				}
			}
		}

		// log final message before exit
		simpleStocksAppHelper.displayLogMessage(LogMessageType.ERROR, "Thank you for using Simple Stock Emulator. Good buy.");
		System.exit(0);
	}

	/**
	 * Log initial welcome message
	 *
	 * @param setStockTypes set of stock type symbols for message information purposes
	 */
	private static void displayInitialWelcomeMenu(Set<String> setStockTypes) {

		String strInitialWelcomeMessage = "\n\nWelcome to Super Simple Stock Emulator (please input stock type below):\n";
		strInitialWelcomeMessage += "NOTE: available stock types are: " + setStockTypes + "\n";

		logger.info(strInitialWelcomeMessage);
	}

	/**
	 * Log stock management emulator options
	 *
	 * @param strStockSymbol stock type symbol for message information purposes
	 */
	private static void displayStockManagementMenu(String strStockSymbol) {

		String strStockManagementMenu = "\n\nStock **" + strStockSymbol + "** (please the following stock management options below):\n\n";

		strStockManagementMenu += "1: Calculate the dividend yield for a given stock\n";
		strStockManagementMenu += "2: Calculate the P/E Ratio for a given stock\n";
		strStockManagementMenu += "3: Record a trade for stock (including time of trade, quantity of shares, buy or sell indicator and trade price)\n";
		strStockManagementMenu += "4: Calculate Volume Weighted Stock Price for a given stock (last 15 minutes)\n";
		strStockManagementMenu += "5: Calculate GBCE All Share Index using the geometric mean of prices for all stocks\n";
		strStockManagementMenu += "6: Chose different Stock\n";
		strStockManagementMenu += "7: Exit the emulator";
		strStockManagementMenu += "\n\n";

		logger.info(strStockManagementMenu);
	}
}
