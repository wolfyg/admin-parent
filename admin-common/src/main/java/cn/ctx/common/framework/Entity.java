package cn.ctx.common.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public abstract class Entity implements Serializable, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		UTDataOutputStream out = new UTDataOutputStream(bos);
		writeTo(out);
		return null;
	}

	@Override
	public void readFrom(byte[] data) throws IOException {
		readFrom(new UTDataInputStream(new ByteArrayInputStream(data)));
	}

}
