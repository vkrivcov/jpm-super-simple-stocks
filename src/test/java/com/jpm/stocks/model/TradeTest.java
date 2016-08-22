package com.jpm.stocks.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vk on 21/08/2016.
 */
public class TradeTest {

	private Trade trade;
	private long ltradeTimestampEpoch;
	private Stock stock, stock1;

	@Before
	public void setUp() throws Exception {

		this.stock = new Stock("TEA", StockType.COMMON, 0, 0, 100);
		this.stock1 = new Stock("POA", StockType.PREFFERED, 0, 0, 200);
		this.ltradeTimestampEpoch = System.currentTimeMillis() / 1000L;
		this.trade = new Trade(this.stock, this.ltradeTimestampEpoch, 100, TradeType.BUY, 20);
	}

	@Test
	public void getStock() throws Exception {
		assertEquals(this.stock, this.trade.getStock());
	}

	@Test
	public void setStock() throws Exception {
		this.trade.setStock(this.stock1);
		assertEquals(this.stock1, this.trade.getStock());
	}

	@Test
	public void getDateTimestamp() throws Exception {
		assertEquals(this.ltradeTimestampEpoch, this.trade.getDateTimestamp());
	}

	@Test
	public void setDateTimestamp() throws Exception {
		this.trade.setDateTimestamp(this.ltradeTimestampEpoch - 100);
		assertEquals(this.ltradeTimestampEpoch - 100, this.trade.getDateTimestamp());
	}

	@Test
	public void getnQuantityOfShares() throws Exception {
		assertEquals(100, this.trade.getnQuantityOfShares(), 0);
	}

	@Test
	public void setnQuantityOfShares() throws Exception {
		this.trade.setnQuantityOfShares(200);
		assertEquals(200, this.trade.getnQuantityOfShares(), 0);
	}

	@Test
	public void getEnumTradeType() throws Exception {
		assertEquals(TradeType.BUY, this.trade.getEnumTradeType());
	}

	@Test
	public void setEnumTradeType() throws Exception {
		this.trade.setEnumTradeType(TradeType.SELL);
		assertEquals(TradeType.SELL, this.trade.getEnumTradeType());
	}

	@Test
	public void getdTradePrice() throws Exception {
		assertEquals(20, this.trade.getdTradePrice(), 0);
	}

	@Test
	public void setdTradePrice() throws Exception {
		this.trade.setdTradePrice(40);
		assertEquals(40, this.trade.getdTradePrice(), 0);
	}

}