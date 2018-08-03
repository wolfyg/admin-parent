package cn.ctx.service.model;

import com.baomidou.mybatisplus.annotations.TableId;

public class AmAdminRole {
	@TableId
    private Integer adminId;

    private Integer roleId;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}