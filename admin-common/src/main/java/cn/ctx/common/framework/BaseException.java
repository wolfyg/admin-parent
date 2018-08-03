package cn.ctx.common.framework;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected int errorCode = -1;

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String message, int errorCode) {
	    super(message);
	    this.errorCode = errorCode;
	}
	
	public BaseException(String message, Throwable cause) {
	    super(message, cause);
	}

	public BaseException(String message, Throwable cause, int errorCode) {
	    super(message, cause);
	    this.errorCode = errorCode;
	}

	public BaseException(Throwable cause) {
	    super(cause);
	}

	public BaseException(Throwable cause, int errorCode) {
	    super(cause);
	    this.errorCode = errorCode;
	}

	public int getErrorCode() {
	    return errorCode;
	}
}



