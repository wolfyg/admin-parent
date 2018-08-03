package cn.ctx.common.framework.token;


public class TokenException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public TokenException(String message) {
		super(message);
	}
	
	public TokenException(String message, Throwable cause) {
	    super(message, cause);	 
	}
	
	public TokenException(Throwable cause) {
	    super(cause);
	}

}
