package cn.edu.nju.p.exception;

/**
 * to be thrown when stock could not find
 */
public class StockNotFoundException extends RuntimeException{

    public StockNotFoundException(String message) {

        super(message);
    }
}
