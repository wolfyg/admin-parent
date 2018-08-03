/**    
* @Title: NodeUtils.java  
* @Package cn.ctx.admin.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月16日 下午6:59:22  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;


/**  
* @ClassName: NodeUtils  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年11月16日 下午6:59:22  
*    
*/
public class NodeUtils {

    private List<LinkedHashMap<String,Object>> result = new ArrayList<LinkedHashMap<String,Object>>();
    /**
     * 获取某个分类的子分类
     * @param listRecord
     * @param id
     * @return
     */
    public List<LinkedHashMap<String,Object>> getSubs(List<LinkedHashMap<String,Object>> listRecord, Integer id) {
        for(LinkedHashMap<String,Object> record : listRecord) {
            if(id == Integer.parseInt(record.get("parentId").toString())) {
                result.add(record);
                getSubs(listRecord, Integer.parseInt(record.get("id").toString()));
            }
        }
        return result;
    }

    private List<Integer> temp = new ArrayList<Integer>();
    public List<Integer> getSubIds(List<LinkedHashMap<String,Object>> listRecord, long id) {
        for(Map<String,Object> record : listRecord) {
            if(id == Integer.parseInt(record.get("parentId").toString())) {
                temp.add(Integer.parseInt(record.get("id").toString()));
                getSubIds(listRecord, Integer.parseInt(record.get("id").toString()));
            }
        }
        return temp;
    }

    /**
     * 生成嵌套循环
     * @param listRecord
     * @return
     */
    public List<LinkedHashMap<String,Object>> getSubs(List<LinkedHashMap<String,Object>> listRecord) {
        for(LinkedHashMap<String,Object> record : listRecord) {
            boolean mark = false;
            for(LinkedHashMap<String,Object> record2 : listRecord){
                if(null !=  record.get("parentId") && record.get("parentId").equals(record2.get("id"))){
                    mark = true;
                    if(null == record2.get("children")) record2.put("children", new ArrayList<Map<String,Object>>());
                    ArrayList<Map<String,Object>> r = (ArrayList<Map<String, Object>>) record2.get("children");
                    r.add(record);
                    break;
                }
            }
            if(!mark){
                result.add(record);
            }
        }
        System.out.println(new Gson().toJson(result));
        return result;
    }
}

