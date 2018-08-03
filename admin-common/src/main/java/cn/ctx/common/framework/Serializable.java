package cn.ctx.common.framework;

import java.io.IOException;

public interface Serializable {

	byte[] getBytes() throws IOException;

	void readFrom(UTDataInputStream in) throws IOException;

	void readFrom(byte[] source) throws IOException;
	
	void writeTo(UTDataOutputStream out) throws IOException;

}
