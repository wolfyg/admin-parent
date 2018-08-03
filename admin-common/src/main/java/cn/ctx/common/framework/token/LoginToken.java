package cn.ctx.common.framework.token;

import java.io.IOException;

import cn.ctx.common.framework.UTDataInputStream;
import cn.ctx.common.framework.UTDataOutputStream;

public class LoginToken extends Token {

	private static final long serialVersionUID = 1L;

	private long createTime;
	private long ttl;
	private String userId;
	private String channelCode;
	private String code;
	private String msg;

	public LoginToken() {
		super(TokenUtil.TOKEN_LOGIN_VERSION);
		this.type = TokenUtil.TOKEN_LOGIN;
		this.ttl = TokenUtil.TOKEN_LOGIN_TTL;
		this.createTime = System.currentTimeMillis();
	}

	public LoginToken(int version) {
		super(version);
		this.type = TokenUtil.TOKEN_LOGIN;
		this.ttl = TokenUtil.TOKEN_LOGIN_TTL;
		this.createTime = System.currentTimeMillis();
	}

	public LoginToken(String userId) {
		this();
		this.userId = userId;
	}
	
	public LoginToken(String userId,String channelCode) {
		this();
		this.userId = userId;
		this.channelCode=channelCode;
	}

	public void readFrom(UTDataInputStream in) throws IOException {
		createTime = in.readLong();
		ttl = in.readLong();
		userId = in.readString();
		channelCode = in.readString();
		code = in.readString();
		msg = in.readString();
	}

	public void writeTo(UTDataOutputStream out) throws IOException {
		out.writeLong(createTime);
		out.writeLong(ttl);
		out.writeString(userId);
		out.writeString(channelCode);
		out.writeString(code);
		out.writeString(msg);
	}

	@Override
	public boolean isExpired() {
		long now = System.currentTimeMillis();
		return createTime + ttl < now;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("type: ").append(type);
		sb.append(", version: ").append(version);
		sb.append(", createTime: ").append(createTime);
		sb.append(", ttl: ").append(ttl);
		sb.append(", userId: ").append(userId);
		sb.append(", channelCode: ").append(channelCode);
		sb.append("}");
		return sb.toString();
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTtl() {
		return ttl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

}
