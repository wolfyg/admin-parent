/**
 * 
 */
package cn.ctx.api.util;

/**
 * 返回结果
 * @author yg
 *
 */
public class ResultBean {

	/**
	 * 默认成功
	 */
	private int code;

	/**
	 * 返回信息
	 */
	private String message;

	/**
	 * 数据项
	 */
	private Object data;


	public ResultBean( int code, String message ) {
		this(code, message, null);
	}
	
	public ResultBean() {
	}

	public ResultBean( Object data ) {
		this(1, null, data);
	}


	public ResultBean( int code, String message, Object data ) {
		this.code = code;
		this.message = message;
		this.data = data;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage( String message ) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData( Object data ) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


}
