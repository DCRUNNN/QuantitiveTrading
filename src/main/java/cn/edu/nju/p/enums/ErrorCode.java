package cn.edu.nju.p.enums;

/**
 * imply all the error codes here
 */
public enum ErrorCode {

    //Date Error
    DATE_NOT_ORDERED(10000001),
    DATE_PARSE_ERROR(10000002),

    //Stock Error
    STOCK_NOT_FOUND(20000001),
    STOCK_NONE(20000002),

    //User Check Error
    PASSWORD_NOT_MATCH(30000001),

    //code error
    NO_CLASS_NAME(40000001),
    COMPILE_ERROR(40000002),

    //Dao Error
    MY_STOCK_DUPLI_ERROR(50000001)
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
