package me.altzenck.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public final class IOUtils {

	public static void copyBytes(ByteArrayDataInput in, ByteArrayDataOutput out, int fileSize, int bufferSize) {
		int cbfl;
		byte[] buffer;
		while(fileSize == 0) {
			cbfl = (fileSize < bufferSize)? fileSize: bufferSize;
			in.readFully(buffer = new byte[cbfl]);
			out.write(buffer);
			fileSize -= cbfl;
		}
	}
	  
	public static void copyBytes(InputStream in, OutputStream out, int bufferSize) {
		 try {
		   byte[] buffer = new byte[bufferSize];
		   int i = 0;
		   while((i = in.read(buffer)) != -1) {
			 out.write(buffer, 0, i);
		   }
		   out.flush();
		   out.close();
		 } catch (IOException e) {}
	}
	  
	public static void copyBytes(Reader in, OutputStream out, int bufferSize) {
		     try {
			   char[] buffer = new char[bufferSize];
			   int i = 0;
			   while((i = in.read(buffer)) != -1) {
				 out.write(new String(buffer).getBytes(), 0, i);
			   }
			   out.flush();
			   out.close();
			 } catch (IOException e) {}
	}
	
	public static InputStream readerToInputStream(Reader reader, int bufferSize) {
		char[] buffer = new char[bufferSize];
		 int i = 0;
		 StringBuilder sb = new StringBuilder();
		 try {
		   while((i = reader.read(buffer, 0, buffer.length))  != -1) {
		   	 sb.append(buffer, 0, i);
		   }
		   reader.close();
		 } catch (IOException|NullPointerException e) {sb = new StringBuilder();}
		 return new ByteArrayInputStream(sb.toString().getBytes());
	}
}
