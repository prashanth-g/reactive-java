package com.prashanth.os.reactive;

import java.util.Random;

public class StockFetcher {

  public static StockInfo fetch(String label) {
    try {
      Thread.sleep(1000);
      Random random = new Random();
      return new StockInfo(label, random.nextInt(10));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }
}
