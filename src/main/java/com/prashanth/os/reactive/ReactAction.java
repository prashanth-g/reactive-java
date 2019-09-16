package com.prashanth.os.reactive;

import java.util.*;
import rx.Observable;
import rx.functions.Action1;

public class ReactAction {

  public static void main(String[] args) {

    List<String> stocks = Arrays.asList("GOOG", "AAPL", "AMZN", "WMT");
    final Observable<StockInfo> feed = Stockserver.getFeed(stocks);
    feed.subscribe(System.out::println);

  }
}
