package com.prashanth.os.reactive;

public class StockInfo {

  public final String ticker;
  public final double price;

  public StockInfo(String ticker, double price) {
    this.ticker = ticker;
    this.price = price;
  }

  @Override
  public String toString() {
    return "StockInfo{" +
        "ticker='" + ticker + '\'' +
        ", price=" + price +
        '}';
  }
}
