package cn.ctx.common.framework;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UTDataOutputStream extends DataOutputStream {
	
	public UTDataOutputStream(OutputStream out) {
		super(out);
	}
	
	public final void writeString(String str) throws IOException {
		if (str == null) {
			str = "";
		}
		writeUTF(str);
	}
}
