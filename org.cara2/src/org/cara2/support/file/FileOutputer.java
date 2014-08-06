package org.cara2.support.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Cara2 Project<br>
 * Use FileWriter to output.
 * 
 * @author bkin
 * @see FileWriter
 */
public class FileOutputer {
	private File file;
	private FileWriter fw;

	public void init(String fname) {
		file = new File(fname);
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(String str) {
		try {
			fw.write(str);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
