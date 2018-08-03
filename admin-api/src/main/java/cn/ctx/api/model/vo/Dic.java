/**
 * 
 */
package cn.ctx.api.model.vo;

/**
 * 数据字典
 * @author yg
 *
 */
public class Dic {

	public String id;
	public String name;
	private String code;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Dic [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
