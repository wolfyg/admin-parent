package cn.ctx.service.model;

public class AmSysRoleFuncKey {
    private Integer roleId;

    private Integer funcId;
    
    private String funcIds;

	public String getFuncIds() {
		return funcIds;
	}

	public void setFuncIds(String funcIds) {
		this.funcIds = funcIds;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFuncId() {
		return funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}


}