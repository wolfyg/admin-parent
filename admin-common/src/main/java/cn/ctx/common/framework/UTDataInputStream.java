package cn.ctx.common.framework;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UTDataInputStream extends DataInputStream {
	
	public UTDataInputStream(InputStream in) {
		super(in);
	}

	public final String readString() throws IOException {
		return readUTF();
	}
	
}
