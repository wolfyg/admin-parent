package cn.ctx.service.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;

public class AmSysRole {
	@TableId
    private Integer id;

    private String name;

    private String description;

    private Integer isSys;

    private String sort;

    private Date createTime;

    private Date modifyTime;
    
    private String funcIds;

    public String getFuncIds() {
		return funcIds;
	}

	public void setFuncIds(String funcIds) {
		this.funcIds = funcIds;
	}

	public Integer getId() {
        return id;
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


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

	public Integer getIsSys() {
		return isSys;
	}

	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}