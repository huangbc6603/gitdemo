package org.example.exception;

/**
 * runtime异常项目基础类
 * <example>E00001</example>
 * @author Derek-huang
 */
public class BaseServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    /**
     * <a>NOT RECOMMENDED !</a>NOT
     */
    public BaseServiceException() {
        super();
        this.errorCode = "E00001" ;
    }

    /**
     * @param cause
     */
    public BaseServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param errorCode
     * @param cause
     */
    public BaseServiceException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * errorCode
     *
     * @param errorCode
     * @param errorMsg
     * @param cause
     */
    public BaseServiceException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }


    /**
     * errorCode
     *
     * @param errorCode
     */
    public BaseServiceException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    /**
     * errorCode & msg
     *
     * @param errorCode
     * @param errorMsg
     */
    public BaseServiceException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String toString() {
        String s = getClass().getName();

        String errorCodeMsg = this.errorCode == null ? "" : this.errorCode;

        String message = getLocalizedMessage();
        message = message == null ? errorCodeMsg : (errorCodeMsg + message);

        return ("".equals(message)) ? s : (s + ": " + message);
    }
}
