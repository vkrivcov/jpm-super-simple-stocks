package com.jpm.stocks.model;

/**
 * Trade model class
 *
 * @author Vadims Krivcovs
 */
public class Trade {
	private Stock stock = null;
	private long lEpochDateTime = 0;
	private double nQuantityOfShares = 0.0;
	private TradeType tradeType = null;
	private double dTradePrice = 0.0;

	/**
	 * Main trade model constructor
	 *
	 * @param stock              stock object
	 * @param lEpochDateTime     timestamp (time since epoch) i.e. 1471906413
	 * @param nQuantityOfShares  quantity of shares i.e. 100
	 * @param tradeType          trade type i.e. SELL
	 * @param dTradePrice        trade price in pennies i.e. 20
	 */
	public Trade(Stock stock, long lEpochDateTime, double nQuantityOfShares, TradeType tradeType, double dTradePrice) {
		this.stock = stock;
		this.lEpochDateTime = lEpochDateTime;
		this.nQuantityOfShares = nQuantityOfShares;
		this.tradeType = tradeType;
		this.dTradePrice = dTradePrice;
	}

	/**
	 * Get stock
	 *
	 * @return stock object
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * Set stock
	 *
	 * @param stock stock object
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * Get timestamp (time since epoch)
	 *
	 * @return timestamp as time since epoch
	 */
	public long getDateTimestamp() {
		return lEpochDateTime;
	}

	/**
	 * Set timestamp (time since epoch)
	 *
	 * @param lEpochDateTime time since epoch (long)
	 */
	public void setDateTimestamp(long lEpochDateTime) {
		this.lEpochDateTime = lEpochDateTime;
	}

	/**
	 * Get quantity of shares
	 *
	 * @return quantity of shares
	 */
	public double getnQuantityOfShares() {
		return nQuantityOfShares;
	}

	/**
	 * Set quantity of shares
	 *
	 * @param nQuantityOfShares quantity of shares
	 */
	public void setnQuantityOfShares(int nQuantityOfShares) { this.nQuantityOfShares = nQuantityOfShares; }

	/**
	 * Get trade type (enum)
	 *
	 * @return trade type (enum)
	 */
	public TradeType getEnumTradeType() { return tradeType; }

	/**
	 * Set trade type (enum)
	 *
	 * @param enumTradeType trade type value (enum)
	 */
	public void setEnumTradeType(TradeType enumTradeType) { this.tradeType = enumTradeType; }

	/**
	 * Get trade price
	 *
	 * @return trade price (in pennies)
	 */
	public double getdTradePrice() {
		return dTradePrice;
	}

	/**
	 * Set trade type
	 *
	 * @param dTradePrice trade type (in pennies)
	 */
	public void setdTradePrice(double dTradePrice) {
		this.dTradePrice = dTradePrice;
	}

	/**
	 * trade representation implementation
	 *
	 * @return string representation of a trade model
	 */
	@Override
	public String toString() {
		return "Trade{" +
				"stock=" + stock +
				", dateTimestamp=" + lEpochDateTime +
				", nQuantityOfShares=" + nQuantityOfShares +
				", enumTradeType=" + tradeType +
				", dTradePrice=" + dTradePrice +
				'}';
	}
}
