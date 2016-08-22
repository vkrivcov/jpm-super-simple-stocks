package com.jpm.stocks.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Stock test class
 *
 * @author Vadims Krivcovs
 */
public class StockTest {

	private Stock stock;

	@Before
	public void setUp() throws Exception {
		this.stock =  new Stock("ALE", StockType.COMMON, 23, 0, 60);
	}

	@Test
	public void getStrStockSymbol1() throws Exception {
		assertEquals("ALE", this.stock.getStrStockSymbol());
	}

	@Test
	public void setStrStockSymbol() throws Exception {
		this.stock.setStrStockSymbol("TEA");
		assertEquals("TEA", this.stock.getStrStockSymbol());
	}

	@Test
	public void getEnumStockType() throws Exception {
		assertEquals(StockType.COMMON, this.stock.getEnumStockType());
	}

	@Test
	public void setEnumStockType() throws Exception {
		this.stock.setEnumStockType(StockType.PREFFERED);
		assertEquals(StockType.PREFFERED, this.stock.getEnumStockType());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getdLastDivident() throws Exception {
		assertEquals(23, this.stock.getdLastDivident(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void setdLastDivident() throws Exception {
		this.stock.setdLastDivident(24);
		assertEquals(24, this.stock.getdLastDivident(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getdFixedDivident() throws Exception {
		assertEquals(0.0, this.stock.getdFixedDivident(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void setdFixedDivident() throws Exception {
		this.stock.setdFixedDivident(1.0);
		assertEquals(1.0, this.stock.getdFixedDivident(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getdParValue() throws Exception {
		assertEquals(60.0, this.stock.getdParValue(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void setdParValue() throws Exception {
		this.stock.setdParValue(40);
		assertEquals(40, this.stock.getdParValue(), 0);
	}
}