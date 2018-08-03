package cn.ctx.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * Web层相关的实用工具类
 * @author yg
 * @date 2017-12-17 下午3:14:59
 */
public class WebUtils {
	
	/**
	 * 将请求参数封装为Map<br>
	 * request中的参数t1=1&t1=2&t2=3<br>
	 * 形成的map结构：<br>
	 * key=t1;value[0]=1,value[1]=2<br>
	 * key=t2;value[0]=3<br>
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> getPraramsAsMap(HttpServletRequest request) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Map map = request.getParameterMap();
		Iterator keyIterator = (Iterator) map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = ((String[]) (map.get(key)))[0];
			if(!org.apache.commons.lang3.StringUtils.isEmpty(value)&&value.equals("undefined")){
				value="";
			}
			hashMap.put(key,value.trim());
		}
		if(!org.apache.commons.lang3.StringUtils.isEmpty(sort(request))){
			hashMap.put("sort",sort(request));
		}
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(hashMap.get("iDisplayStart"))){
			int start=1;
			int end=Integer.parseInt(hashMap.get("iDisplayLength"));
			hashMap.put("start",hashMap.get("iDisplayStart"));
			hashMap.put("end",String.valueOf(start+end));
		}
		return hashMap;
	}
	
	public static String sort(HttpServletRequest request){
		StringBuffer stringBuffer=new StringBuffer();
		String sColumnstr=request.getParameter("sColumns");
		if(!org.apache.commons.lang3.StringUtils.isEmpty(sColumnstr)){
			String[] sColumns = request.getParameter("sColumns").split(",");
			List<String> orders = new ArrayList<String>();
			List<String> orderBys = new ArrayList<String>();
			int iSortingCols = Integer.parseInt(request.getParameter("iSortingCols"));
			for (int i = 0; i < iSortingCols; i++) {
				int iSortColumnIndex = Integer.parseInt(request.getParameter("iSortCol_" + i));
				String sColumn = sColumns[iSortColumnIndex];
				if (org.apache.commons.lang3.StringUtils.isNotEmpty(sColumn)) {
					orderBys.add(sColumn);
					String sSortDirection = request.getParameter("sSortDir_" + i);
					orders.add(sSortDirection);
				}
			}
			for (int i = 0; i < orderBys.size(); i++) {
				stringBuffer.append(orderBys.get(i));
				stringBuffer.append(" ");
				stringBuffer.append(orders.get(i));
				stringBuffer.append(",");
			}
			if(!"".equals(stringBuffer.toString())&&null!=stringBuffer.toString()){
				return stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
			}
		}
		return "";
	}
	
	
	/**
	 * 将请求参数封装为PageParameter对象<br>
	 * request中的参数t1=1&t1=2&t2=3<br>
	 * 形成的map结构：<br>
	 * key=t1;value[0]=1,value[1]=2<br>
	 * key=t2;value[0]=3<br>
	 * @param request
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static Dto getPraramsAsDto(HttpServletRequest request) {
		Dto dto=new Dto();
		PageParameter pageParameter=new PageParameter();
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		Map map = request.getParameterMap();
		Iterator keyIterator = (Iterator) map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = ((String[]) (map.get(key)))[0];
			if(!org.apache.commons.lang3.StringUtils.isEmpty(value)&&value.equals("undefined")){
				value="";
			}
			hashMap.put(key,value.trim());
		}
		
		if(!org.apache.commons.lang3.StringUtils.isEmpty(sort(request))){
			hashMap.put("sort",sort(request));
		}
		dto.setQueryCondition(hashMap);
		
		if(org.apache.commons.lang3.StringUtils.isNotEmpty((String) hashMap.get("start"))){
			//每页显示多少条
			int end=Integer.parseInt((String) hashMap.get("length"));
			int start=1;
			if(ProjectUtils.isNumeric((String) hashMap.get("start"))){
				//当前页数
				start=Integer.parseInt((String) hashMap.get("start"))/end+1;
			}
			
			pageParameter.setCurrentPage(start);
			pageParameter.setPageSize(end);
		}
		dto.setPage(pageParameter);
		return dto;
	}
	
}
