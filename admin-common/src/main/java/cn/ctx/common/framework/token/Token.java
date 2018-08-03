package cn.ctx.common.framework.token;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cn.ctx.common.framework.Entity;
import cn.ctx.common.framework.UTDataInputStream;
import cn.ctx.common.framework.UTDataOutputStream;


public abstract class Token extends Entity {
	private static final long serialVersionUID = 1L;
	
	protected int type;
	protected int version;
	
	public Token() {
	}
	
	public Token(int version) {
		this.version = version;
	}
	
	public abstract boolean isExpired();

	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		UTDataOutputStream out = new UTDataOutputStream(bos);
		out.writeByte(type);
		out.writeByte(version);
		writeTo(out);
		return bos.toByteArray();
	}
	
	public void readFrom(byte[] data) throws IOException {
		UTDataInputStream in = new UTDataInputStream(new ByteArrayInputStream(data));
		type = in.readByte();
		version = in.readByte();
		readFrom(in);
	}
	
	public int getType() {
		return type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
