package com.prashanth.os.reactive;

import java.util.*;
import rx.Observable;
import rx.Subscriber;

public class ReactAction {

  public static void main(String[] args) {

    List<String> stocks = Arrays.asList("GOOG", "AAPL", "AMZN", "WMT");
    final Observable<StockInfo> feed = Stockserver.getFeed(stocks);
    // feed.subscribe(System.out::println);

    feed.subscribe(new Subscriber<StockInfo>() {
      @Override
      public void onCompleted() {
        System.out.println("Completed!");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("An error occurred!");
      }

      @Override
      public void onNext(StockInfo stockInfo) {
        System.out.println(stockInfo);
        if(stockInfo.price > 5.0d) {
          System.out.println("Lets trade...");
          unsubscribe();
        }
      }
    });
  }
}
