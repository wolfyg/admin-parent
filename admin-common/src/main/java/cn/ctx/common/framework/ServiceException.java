package cn.ctx.common.framework;


public class ServiceException extends BaseException {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String message, int errorCode) {
		super(message, errorCode);
	}
	
	public ServiceException(String message, Throwable cause, int errorCode) {
	    super(message, cause, errorCode);	 
	}
	
	public ServiceException(Throwable cause, int errorCode) {
	    super(cause, errorCode);
	}

}
