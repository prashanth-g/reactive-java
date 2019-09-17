package com.prashanth.os.reactive;

import java.util.*;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ReactAction {

  public static void main(String[] args) throws InterruptedException {

    List<String> stocks = Arrays.asList("GOOG", "AAPL", "AMZN", "WMT");
    final Observable<StockInfo> feed = Stockserver.getFeed(stocks);

    // #1
//     feed.subscribe(System.out::println);

    // #2
//    feed.subscribe(new Subscriber<StockInfo>() {
//      @Override
//      public void onCompleted() {
//        System.out.println("Completed!");
//      }
//
//      @Override
//      public void onError(Throwable e) {
//        System.out.println("An error occurred!");
//      }
//
//      @Override
//      public void onNext(StockInfo stockInfo) {
//        System.out.println(stockInfo);
//        if(stockInfo.price > 5.0d) {
//          System.out.println("Lets trade...");
//          unsubscribe();
//        }
//      }
//    });

    // #3
//    feed.subscribeOn(Schedulers.io())
//        .subscribe(ReactAction::printStockInfo);
//    Thread.sleep(10000);
    // #4
//    feed.subscribe(System.out::println, ReactAction::handleError);

    feed.onErrorResumeNext(throwable -> callAgain(throwable, stocks)) // Failure service #1
        .onErrorResumeNext(throwable -> callAgain(throwable, stocks)) // Failure service #2
        .onErrorResumeNext(throwable -> callAgain(throwable, stocks)) // Failure service #3
        .subscribe(System.out::println, ReactAction::handleError);

  }

  private static Observable<StockInfo> callAgain(Throwable throwable, List<String> stocks) {
    System.out.println(throwable);
    return Stockserver.getFeed(stocks);
  }

  private static void handleError(Throwable throwable) {
    // Closes data channel once error blown up
    System.out.println(throwable);
  }

  private static void printStockInfo(StockInfo stockInfo) {
    System.out.println("Thread : " + Thread.currentThread());
    System.out.println(stockInfo);
  }
}
