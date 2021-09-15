package com.thup.icu.demo;

/**
 * 服务异常类
 * @author tlibn
 * @description
 * @create 2019-08-02
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String returnCode;

    private String message;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*public ServiceException(String returnCode, Throwable th){
        super("系统异常",th);
        this.returnCode = returnCode;
    }*/
    public ServiceException(final String returnCode, final String message, Throwable th){
        super(message,th);
        this.returnCode = returnCode;
        this.message = message;
    }

    public ServiceException(final String returnCode, final String message){
        this.returnCode = returnCode;
        this.message = message;
    }
    public ServiceException(final String message){
        this.message = message;
    }

    public static void ex(String message){
        throw new ServiceException(message);
    }

}
