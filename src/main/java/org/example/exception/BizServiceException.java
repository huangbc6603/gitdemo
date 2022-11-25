package org.example.exception;

/**
 * 业务异常
 * <example>B00001</example>
 * @author Derek-huang
 */
public class BizServiceException extends BaseServiceException {

    private static final long serialVersionUID = 1L;

    /**
     * <a>NOT RECOMMENDED !</a>NOT
     */
    public BizServiceException() {
        super();
        setErrorCode("B00001");
    }

    /**
     * @param cause
     */
    public BizServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param errorCode
     * @param cause
     */
    public BizServiceException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * errorCode
     *
     * @param errorCode
     * @param errorMsg
     * @param cause
     */
    public BizServiceException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }

    /**
     * errorCode
     *
     * @param errorCode
     */
    public BizServiceException(String errorCode) {
        super(errorCode);
    }

    /**
     * errorCode & msg
     *
     * @param errorCode
     * @param errorMsg
     */
    public BizServiceException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
