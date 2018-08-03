/**
 * 
 */
package cn.ctx.api.model.vo;

/**
 * 详细地址
 * @author yg
 *
 */
public class MapAddr {
	private String addr;
	private Double lng;
	private Double lat;
	private String province;;
	private String city;
	private String area;
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "MapAddr [addr=" + addr + ", lng=" + lng + ", lat=" + lat + ", province=" + province + ", city=" + city
				+ ", area=" + area + "]";
	}

}
