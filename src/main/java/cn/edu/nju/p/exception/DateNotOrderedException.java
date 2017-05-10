package cn.edu.nju.p.exception;

/**
 * when date not ordered ,sys throws this exception
 */
public class DateNotOrderedException extends Exception {

    public DateNotOrderedException(String message) {
        super(message);
    }
}
