package cn.edu.nju.p.exception;

/**
 * Created by soft on 2017/5/17.
 */
public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
