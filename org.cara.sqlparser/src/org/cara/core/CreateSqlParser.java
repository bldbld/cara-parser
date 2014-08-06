/*
 * CARA Project!
 */
package org.cara.core;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Create SQL Parser.
 * 
 * @author GUSSET
 * @version 0.1, 11/02/2010
 */
public class CreateSqlParser {

	public static void main(String[] args) {
		String inputSql = "create table NAME (id number(6.2),name varchar(10));";
		CreateSqlParser t = new CreateSqlParser();
		t.createParser(inputSql);
		t.showCreateSqlMap();
	}

	/**
	 * Constructor.
	 */
	public CreateSqlParser() {
		super();
		this.dirName = "";
		this.packageName = "";
	}

	/**
	 * Constructor.
	 */
	public CreateSqlParser(String dirName, String packageName) {
		super();
		this.dirName = dirName;
		this.packageName = packageName;
	}

	/**
	 * Run the major method.
	 * <p>
	 * 执行本方法，执行从单个文件解析SQL建表语句的全部流程。
	 * </p>
	 * 
	 * @param fileName
	 */
	public void runCreateSqlParser(String fileName) {
		String inputSql = this.readCreateSqlFile(fileName);
		this.createParser(inputSql);
		this.outputTableDefineFile();
	}

	/**
	 * Read the create SQL File, and return the SQL string.
	 * <p>
	 * 读取含创建表的SQL语句的文件，并返回SQL语句。
	 * </p>
	 * 
	 * @param fileName
	 * @return Create SQL String
	 */
	public String readCreateSqlFile(String fileName) {
		File f = new File(fileName);
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader fr = new BufferedReader(new InputStreamReader(
					new FileInputStream(f)));
			String str = fr.readLine();
			while (str != null) {
				if (!str.startsWith("--")) {
					sb.append(str);
				}
				str = fr.readLine();
			}
			fr.close();
			// System.out.println(sb.toString());
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Output the Table define file.
	 * <p>
	 * 输出数据库表的定义文件。
	 * </p>
	 */
	public void outputTableDefineFile() {
		String fileName = dirName + tableName + ".java";
		File f = new File(fileName);
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("package " + packageName + ";" + this.lineFeed);
			fw.write(this.lineFeed);
			fw.write("public class " + this.tableName + " {" + this.lineFeed);
			Set<String> names = this.createSqlMap.keySet();
			for (String name : names) {
				fw.write(" public static Field "
						+ name.substring(4, name.length()) + " = new Field("
						+ this.createSqlMap.get(name) + ", \"\");"
						+ this.lineFeed);
			}
			fw.write("}" + this.lineFeed);
			fw.flush();
			fw.close();
			System.out.println("CARA LOG: Create DB Define File successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parser the create SQL sentence.
	 * <p>
	 * 解析创建SQL语句。
	 * </p>
	 * 
	 * @param inputSql
	 */
	public void createParser(String inputSql) {
		String[] sqlSegment = inputSql.toLowerCase().trim().split("table");
		this.parserTableName(sqlSegment[1].trim());
		this.parserCreateSql(sqlSegment[1].trim());
	}

	/**
	 * Get the table name from create SQL.
	 * <P>
	 * 解析获得创建SQL语句的表名。
	 * </P>
	 * 
	 * @param sqlSegment
	 */
	private void parserTableName(String sqlSegment) {
		// String[] tableTmp = sqlSegment.split("(");
		int beginIndex = 0;
		int endIndex = sqlSegment.indexOf("(");
		// String tabelNameTmp = tableTmp[0].trim();
		String tabelNameTmp = sqlSegment.substring(beginIndex, endIndex);
		// The tableName is lower. Upper the first char.
		// tableName 是全小写的，将首个字符转换为大写。
		tableName = tabelNameTmp.substring(0, 1).toUpperCase()
				+ tabelNameTmp.substring(1, tabelNameTmp.length());
	}

	/**
	 * Parser the definition parts of create SQL.
	 * <p>
	 * 解析创建SQL的字段定义。
	 * </p>
	 * 
	 * @param sqlSegment
	 */
	private void parserCreateSql(String sqlSegment) {
		// Clear the Map
		createSqlMap.clear();
		int beginIndex = sqlSegment.indexOf("(");
		int endIndex = sqlSegment.lastIndexOf(")");
		// System.out.println(sqlSegment);
		String createSql = sqlSegment.substring(beginIndex + 1, endIndex);
		String[] createParts = createSql.trim().split(",");
		/*
		 * count is a flag to show the order of createSqlMap. count start at 1.
		 * count用于标识各个字段的位置顺序，依照于在创建SQL语句中的位置 在输出时。
		 */
		int count = 1;
		for (String createPart : createParts) {
			String[] tmp = createPart.trim().split(" +");
			String name = this.countToString(count)
					+ tmp[0].trim().toLowerCase();
			Integer length = parsetSqlPartLength(tmp[1].trim());
			createSqlMap.put(name, length);
			count++;
		}
	}

	/**
	 * Convert the value count from Integer to String, and format it like
	 * "00X_".
	 * <p>
	 * 转换计数变量count的类型，返回String类型，并左补0，右补1个'_'，共4位。
	 * </p>
	 * <p>
	 * 输入示例：31，输出示例："031_"。
	 * </p>
	 * 
	 * @param count
	 * @return
	 */
	private String countToString(int count) {
		String returnValue = "000";
		if (count >= 0 && count < 10) {
			returnValue = "00" + new Integer(count).toString();
		} else if (count >= 10 && count < 100) {
			returnValue = "0" + new Integer(count).toString();
		} else if (count >= 100 && count < 1000) {
			returnValue = new Integer(count).toString();
		} else {
			// may add new "else-if" in future.
			returnValue = "999";
		}
		return returnValue + "_";
	}

	/**
	 * Get the length.
	 * <p>
	 * 获得数据库表某字段定义的长度。
	 * </p>
	 * <p>
	 * 输入示例：VARCHAR(10)，输出示例：10。 输入示例：VARCHAR(10.2)，输出示例：12。
	 * </p>
	 * 
	 * @param lengthStr
	 * @return Integer
	 */
	private int parsetSqlPartLength(String lengthStr) {
		int len = 0;
		String[] tmps = lengthStr.split("\\(|\\)");
		String lengthTmp = tmps[1].trim();
		if (lengthTmp.indexOf(".") <= 0) {
			// Not include ",", contains "0"
			// 输入不包括 ','，直接得到值。
			len = Integer.parseInt(lengthTmp);
		} else {
			// Bad! not work well here
			// 输入包括 ','，如(10,2)，得到10+2。
			String[] tmp = lengthTmp.split("\\.");
			len = Integer.parseInt(tmp[0].trim())
					+ Integer.parseInt(tmp[1].trim());
		}
		return len;
	}

	/**
	 * Display create SQL Map.
	 * <p>
	 * 屏幕打印输出SQL Map。
	 * </p>
	 */
	public void showCreateSqlMap() {
		Set<String> names = this.createSqlMap.keySet();
		for (String name : names) {
			System.out.println("Create Sql Map \"" + name + "\"'s length: "
					+ this.createSqlMap.get(name));
		}
	}

	public String getTableName() {
		return tableName;
	}

	public Map<String, Integer> getCreateSqlMap() {
		return createSqlMap;
	}

	/** 
	 * The map is used for the table "name-length" definition's storage
	 * <p>
	 * 此Map用于记录数据库建表语句的值和长度的对应关系。
	 * </p>
	 * <p>
	 * 存储方式:<NAME,LENGTH>。
	 * </p>
	 */
	private Map<String, Integer> createSqlMap = new TreeMap<String, Integer>();

	/** The value is used for the name of table storage */
	private String tableName;

	/** The value is used for the name of directory storage */
	private String dirName = "";

	/** The value is used for the Line break value storage on Windows/DOS OS */
	private String lineFeed = "\r\n";

	/** The value is used for the name of package storage */
	private String packageName = "";
}
