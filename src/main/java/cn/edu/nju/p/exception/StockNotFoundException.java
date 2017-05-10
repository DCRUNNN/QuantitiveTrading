package cn.edu.nju.p.exception;

/**
 * to be thrown when stock could not find
 */
public class StockNotFoundException extends Exception{

    public StockNotFoundException(String message) {

        super(message);
    }
}
