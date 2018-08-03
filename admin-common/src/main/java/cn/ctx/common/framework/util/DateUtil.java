package cn.ctx.common.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil {

	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	* @Title: dateTimeFormat
	* @Description: TODO(格式化时间)
	* @param 
	* @author gyu
	 * @throws java.text.ParseException 
	* @date 2017年5月31日上午11:48:41
	 */
	public synchronized static Date dateTimeFormat(String dateTime) throws ParseException{
		return dateTimeFormat.parse(dateTime);
	}
}
