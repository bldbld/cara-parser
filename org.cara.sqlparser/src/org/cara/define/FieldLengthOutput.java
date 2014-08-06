/*
 * CARA Project!
 */
package org.cara.define;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.cara.core.CaraManager;
import org.cara.core.QuerySqlParser;

/**
 * This Class is used for generate the "TxnXXXLength.java" file.
 * 
 * @author GUSSET
 * @version 0.1, 10/28/2010
 */
public class FieldLengthOutput {
	public static void main(String[] args) {
		FieldLengthOutput flo = new FieldLengthOutput();
		String inputSql = "select a.id, a.name, b.address from actinfo a, address b where a.id = b.id";
		flo.writeJavaFile(inputSql, "Txn1011Length.java", "com.cara.test",
				"1011");
		// System.out.println ("a.d".split(".").length);
	}

	/**
	 * Generate "TxnXXXLength.java" file by input SQL sentence.
	 */
	public void writeJavaFile(String inputSql, String fileName,
			String packageName, String txnName) {
		QuerySqlParser sp = new QuerySqlParser();
		sp.parseSql(inputSql);
		String[] fields = sp.getSelect();
		outputJavaFile(fields, fileName, packageName, txnName);
	}

	/**
	 * Output "TxnXXXLength.java" file.
	 */
	private void outputJavaFile(String[] fields, String fileName,
			String packageName, String txnName) {
		// txnName means ClassName
		/* The fileName is include the dirName */
		File f = new File(fileName);
		String str = null;
		try {
			FileWriter fw = new FileWriter(f);
			/* The output format */
			fw.write("package " + packageName + ";" + lineFeed);
			fw.write(lineFeed);
			fw.write("import " + importName + ".*;" + lineFeed);
			fw.write(lineFeed);
			fw.write("public class " + txnName + " {" + lineFeed);
			fw.write("    public static int []  tos = {" + lineFeed);
			for (int i = 0; i < fields.length; i++) {
				String[] className = fields[i].split("\\.");
				System.out.println(fields[i]);
				System.out.println(className.length);
				String classNameTmp = className[0].substring(0, 1)
						.toUpperCase()
						+ className[0].substring(1, className[0].length())
								.toLowerCase();
				String fieldNameTmp = className[1].toLowerCase();
				str = "        " + classNameTmp + "." + fieldNameTmp
						+ ".length," + lineFeed;
				fw.write(str);
			}
			fw.write("    };" + lineFeed);
			fw.write("}" + lineFeed);
			fw.flush();
			fw.close();
			/* Output the Log */
			System.out.println("CARA LOG: Generate JavaLengthFile " + fileName
					+ " successfully.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Line break */
	private static String lineFeed = "\r\n";

	/** Import name */
	private static String importName = CaraManager.getValue("ImportName");
}
