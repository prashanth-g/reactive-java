package com.prashanth.os.reactive;

import java.util.List;
import rx.Observable;
import rx.Subscriber;

public class Stockserver {

  public static Observable<StockInfo> getFeed(List<String> stockLabels) {
    return Observable
        .create(subscriber -> processRequest(subscriber, stockLabels));
  }

  private static void processRequest(Subscriber<? super StockInfo> subscriber,
      List<String> stockLabels) {
    System.out.println("Processing...");
    while (!subscriber.isUnsubscribed()) {
      stockLabels.stream()
          .map(StockFetcher::fetch)
          .forEach(subscriber::onNext);
    }
  }
}
