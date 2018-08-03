/**    
* @Title: TreeParser.java  
* @Package cn.ctx.common.framework.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2018年4月25日 下午2:06:08  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

import java.util.ArrayList;
import java.util.List;

/**  
* @ClassName: TreeParser  
* @Description: TODO(树生成工具)  
* @author gyu
* @date 2018年4月25日 下午2:06:08  
*    
*/
public class TreeParser{
	
	public interface TreeEntity<E> {  
	    public Integer getId();  
	    public Integer getParentId();  
	    public void setChildList(List<E> childList);  
	}  
    /**
     * 解析树形数据
     * @param topId
     * @param entityList
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(Integer topId, List<E> entityList) {
        List<E> resultList=new ArrayList<E>();
        
        //获取顶层元素集合
        Integer parentId;
        for (E entity : entityList) {
            parentId=entity.getParentId();
            if(parentId==null||topId.equals(parentId)){
                resultList.add(entity);
            }
        }
        
        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(),entityList));
        }
        
        return resultList;
    }
    
    /**
     * 获取子数据集合
     * @param id
     * @param entityList
     */
    private  static  <E extends TreeEntity<E>>  List<E> getSubList(Integer id, List<E> entityList) {
        List<E> childList=new ArrayList<E>();
        Integer parentId;
        
        //子集的直接子对象
        for (E entity : entityList) {
            parentId=entity.getParentId();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }
        
        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        
        //递归退出条件
        if(childList.size()==0){
            return null;
        }
        
        return childList;
    }
}
