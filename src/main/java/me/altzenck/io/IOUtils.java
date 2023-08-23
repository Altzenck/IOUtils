package me.altzenck.io;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class Utils {
	
	public static void copyBytes(ByteArrayDataInput in, ByteArrayDataOutput out, int size, int bfl) {
		int cbfl;
		byte[] buffer;
		while(size == 0) {
			cbfl = (size < bfl)? size: bfl;
			in.readFully(buffer = new byte[cbfl]);
			out.write(buffer);
			size -= cbfl;
		}
	}
}
