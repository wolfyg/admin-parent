/**
 * 
 */
package cn.ctx.api.util;

/**
 * 参数验证
 * @author yg
 *
 */
public class CheckUtil {
//
//	
//	public static ResultBean checkDevice(CmsDeviceUpload device) {
//		ResultBean result=new ResultBean();
//		if(null == device) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("参数不能为空");
//		}else if(StringUtils.isEmpty(device.getAddr())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("地址不能为空");
//		}else if(null == device.getLng()) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("经度不能为空");
//		}else if(null == device.getLng()) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("经度不能为空");
//		}else if(device.getLat()==null) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("纬度不能为空");
//		}else if(StringUtils.isEmpty(device.getProvince())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("省份不能为空");
//		}else if(StringUtils.isEmpty(device.getCity())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("城市不能为空");
//		}else if(StringUtils.isEmpty(device.getArea())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("地区不能为空");
//		}else if(StringUtils.isEmpty(device.getDescription())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("描述不能为空");
//		}else if(StringUtils.isEmpty(device.getImgarr())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("至少上传一张图片");
//		}else if(StringUtils.isEmpty(device.getPhone())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("手机不能为空");
//		}else if(device.getDescription().length()>50) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("描述不能大于50个字符");
//		}else if(!RegexChk.checkPhoneMob(device.getPhone())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("请输入有效的手机号码");
//		}else {
//			result.setCode(Constants.CODE.SUCCESS);
//			result.setMessage("参数验证成功");
//			device.setStatus((byte)0);
//			device.setIsdel((byte)1);
//			device.setCreatetime(new Date());
//		}
//		return result;
//	}
//	
//	public static ResultBean checkMoveCar(CmsMoveCar moveCar) {
//		ResultBean result=new ResultBean();
//		if(null == moveCar) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("参数不能为空");
//		}else if(StringUtils.isEmpty(moveCar.getAddr())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("地址不能为空");
//		}else if(null == moveCar.getLng()) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("经度不能为空");
//		}else if(moveCar.getLat()==null) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("纬度不能为空");
//		}else if(StringUtils.isEmpty(moveCar.getProvince())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("省份不能为空");
//		}else if(StringUtils.isEmpty(moveCar.getCity())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("城市不能为空");
//		}else if(StringUtils.isEmpty(moveCar.getArea())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("地区不能为空");
//		}else if(StringUtils.isEmpty(moveCar.getDescription())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("描述不能为空");
//		}else if(StringUtils.isEmpty(moveCar.getPhone())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("手机不能为空");
//		}else if(!RegexChk.checkPhoneMob(moveCar.getPhone())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("请输入有效的手机号码");
//		}else if(StringUtils.isEmpty(moveCar.getPlateNumber())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("车牌号不能为空");
//		}else {
//			result.setCode(Constants.CODE.SUCCESS);
//			result.setMessage("参数验证成功");
//			moveCar.setStatus("0");
//			moveCar.setIsDel((byte)1);
//			moveCar.setCreateTime(new Date());
//		}
//		return result;
//	}
//	
//	
//	public static ResultBean checkReport(CmsJtwfjb jtwfjb) {
//		ResultBean result=new ResultBean();
//		if(null == jtwfjb) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("参数不能为空");
//		}else if(StringUtils.isEmpty(jtwfjb.getAddr())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("地址不能为空");
//		}else if(null == jtwfjb.getLng()) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("经度不能为空");
//		}else if(null == jtwfjb.getLng()) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("经度不能为空");
//		}else if(jtwfjb.getLat()==null) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("纬度不能为空");
//		}else if(StringUtils.isEmpty(jtwfjb.getProvince())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("省份不能为空");
//		}else if(StringUtils.isEmpty(jtwfjb.getCity())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("城市不能为空");
//		}else if(StringUtils.isEmpty(jtwfjb.getArea())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("地区不能为空");
//		}else if(StringUtils.isEmpty(jtwfjb.getDescription())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("描述不能为空");
//		}else if(StringUtils.isEmpty(jtwfjb.getImgarr())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("至少上传一张图片");
//		}else if(StringUtils.isEmpty(jtwfjb.getCarnum())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("请输入车牌");
//		}else if(StringUtils.isEmpty(jtwfjb.getName())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("请输入姓名");
//		}else if(StringUtils.isEmpty(jtwfjb.getPhone())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("手机不能为空");
//		}else if(jtwfjb.getDescription().length()>50) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("描述不能大于50个字符");
//		}else if(!RegexChk.checkCarNumber(jtwfjb.getCarnum())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("请输入有效的车牌号码");
//		}else if(jtwfjb.getName().length()>4) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("名字长度不能大于4个字符");
//		}else if(!RegexChk.checkPhoneMob(jtwfjb.getPhone())) {
//			result.setCode(Constants.CODE.FAIL);
//			result.setMessage("请输入有效的手机号码");
//		}else {
//			result.setCode(Constants.CODE.SUCCESS);
//			result.setMessage("参数验证成功");
//			jtwfjb.setStatus((byte)0);
//			jtwfjb.setIsdel((byte)1);
//			jtwfjb.setCreatetime(new Date());
//		}
//		return result;
//	}
}
