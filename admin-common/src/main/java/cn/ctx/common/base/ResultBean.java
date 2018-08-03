package cn.ctx.common.base;

/**
* @ClassName: ResultBean  
* @Description: TODO(vo对象)  
* @author gyu
* @date 2017年11月7日 下午4:46:16  
*
 */
public class ResultBean {
	private String encrypt="n"; //y 加密 n 不加密
	private Integer result;// 1 正确 0:失败
	private String msg;// 错误信息
	private Object data;// 返回客户端JSON数据
	private Object token;//令牌
	private Integer icon;//图标类型

	public Integer getIcon() {
		return icon;
	}

	/**
	* @Title: setIcon
	* @Description: TODO(图片类型)
	* @param 1 成功 2失败 3问题 4锁
	* @author gyu
	* @date 2017年11月10日下午12:18:05
	 */
	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	public Object getToken() {
		return token;
	}

	public void setToken(Object token) {
		this.token = token;
	}

	public String getEncrypt() {
		return encrypt;
	}

	/**
	 * y 加密 n 不加密 默认不加密
	 * @param encrypt
	 */
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}


	public Integer getResult() {
		return result;
	}

	/**
	 * 1 正确 0失败
	 * @param result
	 */
	public void setResult(Integer result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * 提示信息
	 * @param data
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	/**
	 * 返回客户端JSON数据
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
