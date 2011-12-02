package microbenchmark.disk;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Write {
	public static void main(String args[]) {
		if (args.length < 2) {
			System.out.println("Usage: java microbenchmark.disk.Write <filename> <size>");
			return;
		}
		long size = Long.parseLong(args[1]);
		Random r = new Random();
		long written = 0;
		try {
			FileOutputStream fos = new FileOutputStream(args[0]);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			for (written = 0; written < size; written++) {
				bos.write(r.nextInt());
			}
			bos.close();
			fos.close();
			System.out.println("Java: Written " + written + " bytes.");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
