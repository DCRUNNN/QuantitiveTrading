package cn.edu.nju.p.baseresult;

import java.io.Serializable;

/**
 * data transfer base object
 */
public class BaseResult<T> implements Serializable{

    private static final long serialVersionUID = -4185151304730685014L;

    private int errorCode; //0 means success
    private T data;

    /**
     * successfully get the result data,error code is 0
     * @param data the data to be transferred
     */
    public BaseResult( T data) {
        this.data = data;
        this.errorCode = 0;
    }

    /**
     * occurs error
     * @param errorCode the error code
     * @param message the error message
     */
    public BaseResult(int errorCode, String message) {
        this.errorCode = errorCode;
        this.data = (T) message;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public T getData() {
        return data;
    }
}
