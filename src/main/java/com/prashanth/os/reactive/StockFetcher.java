package com.prashanth.os.reactive;

public class StockFetcher {

  public static StockInfo fetch(String label) {
    try {
      Thread.sleep(1000);
      return new StockInfo(label, 100.0);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }
}
