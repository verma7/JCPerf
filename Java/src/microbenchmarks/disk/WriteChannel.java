package microbenchmarks.disk;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class WriteChannel {
	public static void main(String args[]) {
		if (args.length != 3) {
			System.out.println("Usage: java microbenchmark.disk.WriteChannel"
					+ " <filename> <filesize> <buffersize>");
			return;
		}
		long size = Long.parseLong(args[1]);
		int bufferSize = Integer.parseInt(args[2]);

		Random r = new Random();
		long written = 0;
		try {
			FileOutputStream fos = new FileOutputStream(args[0]);
			FileChannel fc = fos.getChannel();

			ByteBuffer bb = ByteBuffer.allocate(bufferSize);
			for (written = 0; written < size; written++) {
				if (bb.position() == bb.limit() - 1) {
					bb.flip();
					fc.write(bb);
					bb.clear();
					// Make sure data is synced
					fc.force(true);
				}
				bb.put((byte) r.nextInt());
			}
			fc.close();
			fos.close();
			System.out.println("Java: Written " + written + " bytes.");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
