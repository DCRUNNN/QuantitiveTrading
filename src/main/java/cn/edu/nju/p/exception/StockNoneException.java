package cn.edu.nju.p.exception;

/**
 * thrown when the given date is holiday and festivals,causing the stock market paused
 */
public class StockNoneException extends Exception {

    public StockNoneException(String message) {
        super(message);
    }
}
