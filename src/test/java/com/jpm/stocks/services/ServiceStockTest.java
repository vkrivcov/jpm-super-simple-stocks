package com.jpm.stocks.services;

import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.StockType;
import com.jpm.stocks.model.Trade;
import com.jpm.stocks.model.TradeType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vk on 22/08/2016.
 */
public class ServiceStockTest {

	private ServiceStock serviceStock;
	private Stock stockTest1, stockTest2, stockTest3, stockTest4, stockTest5;

	@Before
	public void setUp() throws Exception {
		this.serviceStock = new ServiceStock();

		this.stockTest1 = new Stock("TEA", StockType.COMMON, 0, 0, 100);
		this.stockTest2 = new Stock("POP", StockType.COMMON, 8, 0, 100);
		this.stockTest3 = new Stock("ALE", StockType.COMMON, 23, 0, 60);
		this.stockTest4 = new Stock("GIN", StockType.PREFFERED, 8, 2, 100);
		this.stockTest5 = new Stock("JOE", StockType.COMMON, 13, 0, 250);
	}

	@Test
	public void dCalculateDividendYield() throws Exception {
		// dividend yield for TEA
		double dDividendYield1 = this.serviceStock.dCalculateDividendYield(this.stockTest1, 20.0);
		assertEquals(0.0, dDividendYield1, 0);

		// dividend yield for POP
		double dDividendYield2 = this.serviceStock.dCalculateDividendYield(this.stockTest2, 20.0);
		assertEquals(0.4, dDividendYield2, 0);

		// dividend yield for ALE
		double dDividendYield3 = this.serviceStock.dCalculateDividendYield(this.stockTest3, 20.0);
		assertEquals(1.15, dDividendYield3, 0);

		// dividend yield for GIN
		double dDividendYield4 = this.serviceStock.dCalculateDividendYield(this.stockTest4, 20.0);
		assertEquals(10, dDividendYield4, 0);

		// dividend yield for JOE
		double dDividendYield5 = this.serviceStock.dCalculateDividendYield(this.stockTest5, 20.0);
		assertEquals(0.65, dDividendYield5, 0);
	}

	@Test
	public void dCalculatePERatio() throws Exception {
		// P/E Ratio for TEA
		try {
			this.serviceStock.dCalculatePERatio(this.stockTest1, 20.0);

			// raise trigger exception
			assertTrue("must raise an exception for TEA P/E Ratio calculation but it did not", false);

		} catch (Exception e) {
			assertEquals("unable to calculate P/E Ratio because dividend value for stock TEA is 0", e.getMessage());
		}

		// P/E Ratio for POP
		double dPERatio2 = this.serviceStock.dCalculatePERatio(this.stockTest2, 20.0);
		assertEquals(50.0, dPERatio2, 1);

		// P/E Ratio for ALE
		double dPERatio3 = this.serviceStock.dCalculatePERatio(this.stockTest3, 20.0);
		assertEquals(17.39, dPERatio3, 1);

		// P/E Ratio for GIN
		double dPERatio4 = this.serviceStock.dCalculatePERatio(this.stockTest4, 20.0);
		assertEquals(2.0, dPERatio4, 1);

		// P/E Ratio for JOE
		double dPERatio5 = this.serviceStock.dCalculatePERatio(this.stockTest5, 20.0);
		assertEquals(30.76, dPERatio5, 1);
	}

	@Test
	public void dCalculateGBCE() throws Exception {
		List<Trade> listTrades = new ArrayList<Trade>();

		// must raise an exception if we try to calculate GBCE with no trades
		try {
			this.serviceStock.dCalculateGBCE(listTrades);

			// raise trigger exception
			assertTrue("must raise an exception because we have no trades but it did not", false);

		} catch (Exception e) {
			assertEquals("unable to calculate GBCE because we have no trades", e.getMessage());
		}

		// create some trades
		long lCurrentTimestampEpoch = System.currentTimeMillis() / 1000L;
		Trade trade1 = new Trade(stockTest1, lCurrentTimestampEpoch, 100, TradeType.BUY, 20);
		Trade trade2 = new Trade(stockTest2, lCurrentTimestampEpoch, 150, TradeType.BUY, 20);
		Trade trade3 = new Trade(stockTest3, lCurrentTimestampEpoch, 20, TradeType.SELL, 10);
		Trade trade4 = new Trade(stockTest4, lCurrentTimestampEpoch, 15, TradeType.BUY, 100);
		Trade trade5 = new Trade(stockTest5, lCurrentTimestampEpoch, 8, TradeType.BUY, 150);

		// add trades to the list
		listTrades.add(trade1);
		listTrades.add(trade2);
		listTrades.add(trade3);
		listTrades.add(trade4);
		listTrades.add(trade5);

		double dGBCEvalue = this.serviceStock.dCalculateGBCE(listTrades);
		assertEquals(35.94, dGBCEvalue, 1);
	}

	@Test
	public void dCalculateVolumeWeightedStockPrice() throws Exception {
		List<Trade> listTrades = new ArrayList<Trade>();

		// must raise an exception if we try to calculate GBCE with no trades
		try {
			this.serviceStock.dCalculateVolumeWeightedStockPrice(listTrades);

			// raise trigger exception
			assertTrue("must raise an exception because we have no trades but it did not", false);

		} catch (Exception e) {
			assertEquals("unable to calculate Volume Weighted Stock Price because we have no trades", e.getMessage());
		}

		// create some trades
		long lCurrentTimestampEpoch = System.currentTimeMillis() / 1000L;
		Trade trade1 = new Trade(stockTest1, lCurrentTimestampEpoch, 100, TradeType.BUY, 20);
		Trade trade2 = new Trade(stockTest1, lCurrentTimestampEpoch, 150, TradeType.BUY, 20);
		Trade trade3 = new Trade(stockTest1, lCurrentTimestampEpoch, 20, TradeType.SELL, 10);

		// add trades to the list
		listTrades.add(trade1);
		listTrades.add(trade2);
		listTrades.add(trade3);

		double dVolumeWeightedStockPrice = this.serviceStock.dCalculateVolumeWeightedStockPrice(listTrades);
		assertEquals(19.26, dVolumeWeightedStockPrice, 1);
	}
}