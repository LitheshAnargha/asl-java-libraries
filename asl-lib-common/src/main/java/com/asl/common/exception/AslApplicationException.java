package com.asl.common.exception;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common Application Exception class which can be used by other classes across all the projects
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
public class AslApplicationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	// Custom error message
    private String message;

    // Custom error code representing an error in system
    private String errorCode;

    public AslApplicationException (String message) {
        super(message);
        this.message = message;
    }

    public AslApplicationException (String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
