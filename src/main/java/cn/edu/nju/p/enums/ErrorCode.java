package cn.edu.nju.p.enums;

/**
 * imply all the error codes here
 */
public enum ErrorCode {

    //Date Error
    DATE_NOT_ORDERED(10000001),
    DATE_PARSE_ERROR(10000002),

    //Stock Error
    STOCK_NOT_FOUND(20000001)
    ;

    private int errorCode;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static ErrorCode stateOf(int errorCode) {
        for (ErrorCode error : values()) {
            if (error.getErrorCode() == errorCode) {
                return error;
            }
        }
        return null;
    }
}
