package com.jpm.stocks.backend;

import com.jpm.stocks.model.Stock;
import com.jpm.stocks.model.StockType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * BackendStockManager test class
 *
 * @author Vadims Krivcovs
 */
public class BackendStockManagerTest {

	private BackendStockManager backendStockManager;
	private Stock stockTest1;
	private Stock stockTest2;
	private Stock stockTest3;

	@Before
	public void setUp() throws Exception {
		this.backendStockManager = new BackendStockManager();

		// add sample stock
		this.stockTest1 = new Stock("TEA",StockType.COMMON, 0, 0, 100);
		this.stockTest2 = new Stock("POP", StockType.COMMON, 8, 0, 100);
		this.stockTest3 = new Stock("ALE", StockType.COMMON, 23, 0, 60);

		this.backendStockManager.addStock(this.stockTest1);
		this.backendStockManager.addStock(this.stockTest2);
		this.backendStockManager.addStock(this.stockTest3);
	}

	@Test
	public void addStock() throws Exception {
		// initially there should be 3 stock items
		assertEquals(3, this.backendStockManager.getAllStocks().size());

		// add two more test stocks
		Stock stockTest4 = new Stock("GIN", StockType.COMMON, 8, 2, 100);
		Stock stockTest5 = new Stock("JOE", StockType.COMMON, 13, 0, 250);

		this.backendStockManager.addStock(stockTest4);
		this.backendStockManager.addStock(stockTest5);

		assertEquals(5, this.backendStockManager.getAllStocks().size());
	}

	@Test
	public void getStock() throws Exception {
		Stock stockTemp1 = this.backendStockManager.getStock("TEA");
		Stock stockTemp2 = this.backendStockManager.getStock("POP");
		Stock stockTemp3 = this.backendStockManager.getStock("ALE");
		Stock stockTemp4 = this.backendStockManager.getStock("TEST");

		assertEquals(this.stockTest1, stockTemp1);
		assertEquals(this.stockTest2, stockTemp2);
		assertEquals(this.stockTest3, stockTemp3);
		assertEquals(null, stockTemp4);
	}

	@Test
	public void getAllStocks() throws Exception {

	}

	@Test
	public void getMapStock() throws Exception {
		Map<String, Stock> mapStocks = this.backendStockManager.getMapStock();

		assertEquals(3, mapStocks.size());
		assertTrue(mapStocks.containsKey("TEA"));
		assertTrue(mapStocks.containsKey("POP"));
		assertTrue(mapStocks.containsKey("ALE"));
		assertFalse(mapStocks.containsKey("TEST"));
	}

}