package org.example.exception;

/**
 *   数据访问异常
 *   <example>D00001</example>
 *   @author Derek-huang
 */
public class DataServiceException extends BaseServiceException {

    private static final long serialVersionUID = 1L;


    /**
     * <a>NOT RECOMMENDED !</a>NOT
     */
    public DataServiceException() {
        super();
        setErrorCode("D00001");
    }

    /**
     * @param cause
     */
    public DataServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param errorCode
     * @param cause
     */
    public DataServiceException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * errorCode
     *
     * @param errorCode
     * @param errorMsg
     * @param cause
     */
    public DataServiceException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }

    /**
     * errorCode
     *
     * @param errorCode
     */
    public DataServiceException(String errorCode) {
        super(errorCode);
    }

    /**
     * errorCode & msg
     *
     * @param errorCode
     * @param errorMsg
     */
    public DataServiceException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
