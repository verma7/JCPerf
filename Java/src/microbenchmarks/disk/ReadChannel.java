package microbenchmarks.disk;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadChannel {
	public static void main(String args[]) {
		if (args.length != 2) {
			System.out.println("Usage: java microbenchmark.disk.ReadChannel" +
					" <filename> <buffersize>");
			return;
		}

		int bufferSize = Integer.parseInt(args[1]);
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			FileChannel fc = fis.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(bufferSize);
			
			int n = 0;
			long read = 0;
			while ((n = fc.read(bb)) != -1) {
				read += n;
			}
			fc.close();
			fis.close();
			System.out.println("Java: Read " + read + " bytes.");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
