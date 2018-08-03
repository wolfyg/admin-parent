package cn.ctx.service.model;

import java.util.Date;

public class VoteWxUser {
    private String openid;

    private String headimg;

    private String nickname;

    private Date createtime;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	@Override
	public String toString() {
		return "VoteWxUser [openid=" + openid + ", headimg=" + headimg + ", nickname=" + nickname + ", createtime="
				+ createtime + "]";
	}
}