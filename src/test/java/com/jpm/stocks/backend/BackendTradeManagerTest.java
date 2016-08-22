package com.jpm.stocks.backend;

import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.StockType;
import com.jpm.stocks.model.Trade;
import com.jpm.stocks.model.TradeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * BackendTradeManager test class
 *
 * @author Vadims Krivcovs
 */
public class BackendTradeManagerTest {

	// test class
	private BackendTradeManager backendTradeManager;

	private Stock stockTest1;
	private Stock stockTest2;
	private Stock stockTest3;

	@Before
	public void setUp() throws Exception {
		this.backendTradeManager = new BackendTradeManager();

		this.stockTest1 = new Stock("TEA", StockType.COMMON, 0, 0, 100);
		this.stockTest2 = new Stock("POP", StockType.COMMON, 8, 0, 100);
		this.stockTest3 = new Stock("ALE", StockType.COMMON, 23, 0, 60);

		long lCurrentTimestampEpoch = System.currentTimeMillis() / 1000L;
		this.backendTradeManager.addTrade(new Trade(stockTest1, lCurrentTimestampEpoch, 100, TradeType.BUY, 20));
		this.backendTradeManager.addTrade(new Trade(stockTest2, lCurrentTimestampEpoch, 200, TradeType.BUY, 10));

		int nSixteenMinutes = 60 * 16;
		this.backendTradeManager.addTrade(new Trade(stockTest3, lCurrentTimestampEpoch - nSixteenMinutes, 300, TradeType.BUY, 5));
	}

	@Test
	public void addTrade() throws Exception {
		// make sure that it does not contain any trades
		assertEquals(3, this.backendTradeManager.getAllTrades().size());

		// add sample trades
		Stock stockTest4 = new Stock("GIN", StockType.COMMON, 8, 2, 100);
		Stock stockTest5 = new Stock("JOE", StockType.COMMON, 13, 0, 250);

		long lCurrentTimestampEpoch = System.currentTimeMillis() / 1000L;
		this.backendTradeManager.addTrade(new Trade(stockTest4, lCurrentTimestampEpoch, 100, TradeType.BUY, 20));
		this.backendTradeManager.addTrade(new Trade(stockTest5, lCurrentTimestampEpoch, 200, TradeType.BUY, 10));

		assertEquals(5, this.backendTradeManager.getAllTrades().size());
	}

	@Test
	public void getTrades() throws Exception {
		// check trades for ALL time
		// get all trades for TEA
		List<Trade> listTrades1 = this.backendTradeManager.getTrades(stockTest1, -1);

		// get all trades for POP
		List<Trade> listTrades2 = this.backendTradeManager.getTrades(stockTest2, -1);

		// get all trades for ALE
		List<Trade> listTrades3 = this.backendTradeManager.getTrades(stockTest3, -1);

		assertEquals(1, listTrades1.size());
		assertEquals(1, listTrades2.size());
		assertEquals(1, listTrades3.size());

		// check trades for last 15 MINUTES
		List<Trade> listTrades4 = this.backendTradeManager.getTrades(stockTest1, 15);

		// get all trades for POP
		List<Trade> listTrades5 = this.backendTradeManager.getTrades(stockTest2, 15);

		// get all trades for ALE
		List<Trade> listTrades6 = this.backendTradeManager.getTrades(stockTest3, 15);

		assertEquals(1, listTrades4.size());
		assertEquals(1, listTrades5.size());
		assertEquals(0, listTrades6.size());
	}

	@Test
	public void getAllTrades() throws Exception {
		List<Trade> listTradesAll = this.backendTradeManager.getAllTrades();
		assertEquals(3, listTradesAll.size());
	}

}