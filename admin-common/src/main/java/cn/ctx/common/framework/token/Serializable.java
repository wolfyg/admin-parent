package cn.ctx.common.framework.token;

import java.io.IOException;

import cn.ctx.common.framework.UTDataInputStream;
import cn.ctx.common.framework.UTDataOutputStream;

public interface Serializable {

	byte[] getBytes() throws IOException;

	void readFrom(UTDataInputStream in) throws IOException;

	void readFrom(byte[] source) throws IOException;
	
	void writeTo(UTDataOutputStream out) throws IOException;

}
