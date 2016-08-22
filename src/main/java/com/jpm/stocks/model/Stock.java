package com.jpm.stocks.model;

/**
 * Stock model class
 *
 * @author Vadims Krivcovs
 */
public class Stock {
	private String strStockSymbol = null;
	private StockType enumStockType = null;
	private double dLastDivident = 0.0;
	private double dFixedDivident = 0.0;
	private double dParValue = 0.0;

	/**
	 * Main stock model constructor
	 *
	 * @param strStockSymbol   stock symbol i.e. TEA
	 * @param enumStockType    stock type i.e. PREFFERED
	 * @param dLastDivident    stock last divident i.e. 0.0
	 * @param dFixedDivident   stock fixed divident i.e. 10.0
	 * @param dParValue        stock par value i.e. 10
	 */
	public Stock(String strStockSymbol, StockType enumStockType, double dLastDivident, double dFixedDivident, double dParValue) {
		this.strStockSymbol = strStockSymbol;
		this.enumStockType = enumStockType;
		this.dLastDivident = dLastDivident;
		this.dFixedDivident = dFixedDivident;
		this.dParValue = dParValue;
	}

	/**
	 * Get stock symbol
	 *
	 * @return stock symbol
	 */
	public String getStrStockSymbol() {
		return strStockSymbol;
	}


	/**
	 * Set stock symbol
	 *
	 * @param strStockSymbol stock symbol
	 */
	public void setStrStockSymbol(String strStockSymbol) {
		this.strStockSymbol = strStockSymbol;
	}


	/**
	 * Get stock type (enum)
	 *
	 * @return value for a stock type (enum)
	 */
	public StockType getEnumStockType() { return enumStockType; }


	/**
	 * Set stock type
	 *
	 * @param enumStockType stock type (enum)
	 */
	public void setEnumStockType(StockType enumStockType) {
		this.enumStockType = enumStockType;
	}


	/**
	 * Get last dividend value
	 *
	 * @return last dividend value
	 */
	public double getdLastDivident() {
		return dLastDivident;
	}


	/**
	 * Set last dividend
	 *
	 * @param dLastDivident last dividend value
	 */
	public void setdLastDivident(double dLastDivident) {
		this.dLastDivident = dLastDivident;
	}


	/**
	 * Get fixed dividend value
	 *
	 * @return fixed dividend value
	 */
	public double getdFixedDivident() {
		return dFixedDivident;
	}


	/**
	 * Set fixed dividend value
	 *
	 * @param dFixedDivident fixed dividend value
	 */
	public void setdFixedDivident(double dFixedDivident) {
		this.dFixedDivident = dFixedDivident;
	}


	/**
	 * Get PAR value
	 *
	 * @return PAR value
	 */
	public double getdParValue() {
		return dParValue;
	}


	/**
	 * Set PAR value
	 *
	 * @param dParValue PAR value
	 */
	public void setdParValue(double dParValue) {
		this.dParValue = dParValue;
	}


	/**
	 * Stock representation implementation
	 *
	 * @return string representation of a stock model
	 */
	@Override
	public String toString() {
		return "Stock{" +
				"strStockSymbol='" + strStockSymbol + '\'' +
				", dLastDivident=" + dLastDivident +
				", dFixedDivident=" + dFixedDivident +
				", dParValue=" + dParValue +
				'}';
	}
}
