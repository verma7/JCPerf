package microbenchmark.disk;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Read {
	public static void main(String args[]) {
		if (args.length < 1) {
			System.out.println("Usage: java microbenchmark.disk.Read <filename>");
			return;
		}

		try {
			FileInputStream fis = new FileInputStream(args[0]);
			BufferedInputStream bis = new BufferedInputStream(fis);
			long read = 0;

			while (bis.read() != -1) {
				read++;
			}
			bis.close();
			System.out.println("Java: Read " + read + " bytes.");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
