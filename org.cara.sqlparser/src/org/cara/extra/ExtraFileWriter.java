/*
 * CARA Project!
 */
package org.cara.extra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.cara.core.FieldParser;
import org.cara.core.QuerySqlParser;
import org.cara.define.Field;

/**
 * This Class is used for generate the Extra "fileName.xml" file. The XML file
 * contains a DATASET with Labels.
 * <p>
 * 使用ExtraFileWriter，需注意要将存放数据库表字段定义的文件的包导入到本工程。
 * </p>
 * 
 * @author GUSSET
 * @version 0.1, 10/30/2010
 */
public class ExtraFileWriter {

	public void writeExtraFile(String datasetName, String inputSql,
			String fileName) {
		QuerySqlParser sp = new QuerySqlParser();
		FieldParser fp = new FieldParser();
		sp.parseSql(inputSql);
		String[] selects = sp.getSelect();
		fp.generateFields(selects);
		Field[] fields = fp.getFields();
		this.outputExtraFile(datasetName, fields, fileName);
	}

	/**
	 * Output the Extra file.
	 */
	private void outputExtraFile(String datasetName, Field[] selects,
			String fileName) {
		File f = new File(fileName + ".xml");
		try {
			FileWriter fw = new FileWriter(f);
			System.out.println ("Encoding"+fw.getEncoding());
			String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			fw.write(str);
			str = "<Extra><Datasets><Dataset><id>" + datasetName
					+ "</id><Fields>";
			fw.write(str);
			for (int i = 0; i < selects.length; i++) {
				fw.write("<Field>");
				// Output the Label of Field.
				str = "<label>" + selects[i].label.getBytes("UTF-8") + "</label>";
				fw.write(str);
				// Output the ID of Field.
				str = "<name>field" + (i + 1) + "</name>";
				fw.write(str);
				fw.write("</Field>");
			}
			str = "</Fields></Dataset></Datasets></Extra>";
			fw.write(str);
			fw.flush();
			fw.close();
			/* Output the Log */
			System.out.println("CARA LOG: Generate Extra " + fileName
					+ " successfully.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}