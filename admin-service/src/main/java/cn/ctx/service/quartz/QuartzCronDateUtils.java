/**    
* @Title: QuartzCronDateUtils.java  
* @Package cn.ctx.admin.core.quartz  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月14日 上午11:06:54  
* @version V1.0    
*/
package cn.ctx.service.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**  
* @ClassName: QuartzCronDateUtils  
* @Description: TODO(日期转换cron表达式时间格式)  
* @author gyu
* @date 2017年11月14日 上午11:06:54  
*    
*/
public class QuartzCronDateUtils {  
    /***  
     * @param date  
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss  
     * @return  
     */    
    public static String formatDateByPattern(Date date,String dateFormat){    
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);    
        String formatTimeStr = null;    
        if (date != null) {    
            formatTimeStr = sdf.format(date);    
        }    
        return formatTimeStr;    
    }    
    /***  
     * convert Date to cron ,eg.  "14 01 17 22 07 ? 2017"  
     * @param date:时间点  
     * @return  
     */    
    public static String getCron(java.util.Date  date){    
        String dateFormat="ss mm HH dd MM ? *";    
        return formatDateByPattern(date,dateFormat);    
    }    
    
    public static void main(String arg[]) {
    	System.out.println(getCron(new Date()));
    }
}  