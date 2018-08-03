package cn.ctx.service.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import cn.ctx.common.framework.util.TreeParser;
import cn.ctx.common.framework.util.TreeParser.TreeEntity;

public class AmDic implements TreeEntity<AmDic>{
    private Integer id;

    private String name;

    private String description;

    private Integer pid;

    private Integer sort;

    private Byte delflag;

    private String code;

    private Integer count;
    
    private Byte state;
    
    List<AmDic> childList;


    public List<AmDic> getChildList() {
		return childList;
	}

	/**
    * @Title: setState
    * @param state 1添加 2修改
    * @author gyu
    * @date 2018年5月7日下午3:23:15
     */
    public Byte getState() {
		return state;
	}

    /**
    * @Title: setState
    * @param state 1添加 2修改
    * @author gyu
    * @date 2018年5月7日下午3:23:15
     */
	public void setState(Byte state) {
		this.state = state;
	}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getDelflag() {
        return delflag;
    }

    public void setDelflag(Byte delflag) {
        this.delflag=delflag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Integer getParentId() {
		return this.pid;
	}

	@Override
	public void setChildList(List childList) {
		this.childList=childList;
	}
	
	


//	public static void main(String[] args) {  
//        List<AmDic> list=new ArrayList<AmDic>();  
//        AmDic menu1=new AmDic();  
//        menu1.setId(35);  
//        menu1.setPid(34);  
//        menu1.setName("我要曝光");  
//        menu1.setCode("5100");
//        list.add(menu1);  
//          
//        AmDic menu2=new AmDic();  
//        menu2.setId(36);  
//        menu2.setPid(35);  
//        menu2.setName("变道加塞");  
//        menu2.setCode("5100");
//        list.add(menu2);  
//          
//        AmDic menu3=new AmDic();  
//        menu3.setId(37);  
//        menu3.setPid(34);  
//        menu3.setName("我要上报");  
//        menu3.setCode("5100");
//        list.add(menu3);  
//          
//        AmDic menu4=new AmDic();  
//        menu4.setId(38);  
//        menu4.setPid(37);  
//        menu4.setName("标志标线");  
//        menu4.setCode("5100");
//        list.add(menu4);  
//          
//
//        AmDic menu5=new AmDic();  
//        menu5.setId(39);  
//        menu5.setPid(34);  
//        menu5.setName("文明出行正能量");  
//        menu5.setCode("5100");
//        list.add(menu5);  
//          
//        AmDic menu6=new AmDic();  
//        menu6.setId(40);  
//        menu6.setPid(39);  
//        menu6.setName("助人为乐");  
//        menu6.setCode("5100");
//        list.add(menu6);  
//          
//        AmDic menu7=new AmDic();  
//        menu7.setId(41);  
//        menu7.setPid(34);  
//        menu7.setName("我路况分享");  
//        menu7.setCode("5100");
//        list.add(menu7);  
//          
//        AmDic menu8=new AmDic();  
//        menu8.setId(42);  
//        menu8.setPid(41);  
//        menu8.setName("积水");  
//        menu8.setCode("5100");
//        list.add(menu8);  
//        
//
//        AmDic menu9=new AmDic();  
//        menu9.setId(43);  
//        menu9.setPid(42);  
//        menu9.setName("积水");  
//        menu9.setCode("5100");
//        list.add(menu9);  
//        List<AmDic>  menus=TreeParser.getTreeList(34,list);  
//        System.out.println(new Gson().toJson(menus));  
//    } 
}