package cn.ctx.service.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;

public class AmAdmin implements Serializable{
	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/
	private static final long serialVersionUID = -4677505767691628911L;

	@TableId
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String nickName;

    private Date createTime;

    private Integer locked;

    private Integer loginCount;

    private Integer lastLoginTime;

    private String lastLoginip;
    
    private Integer roleId;
    
    private String vCode;

    public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }


    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Integer lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginip() {
		return lastLoginip;
	}

	public void setLastLoginip(String lastLoginip) {
		this.lastLoginip = lastLoginip;
	}

}