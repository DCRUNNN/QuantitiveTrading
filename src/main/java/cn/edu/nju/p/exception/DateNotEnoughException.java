package cn.edu.nju.p.exception;

/**
 * thrown when the given two date is too close
 */
public class DateNotEnoughException extends Exception {

    public DateNotEnoughException(String message) {

        super(message);
    }
}
