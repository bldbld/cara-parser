/*
 * CARA Project!
 */
package org.cara.core;
 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.cara.define.Field;

/**
 * Parser the SQL and generate the Fields.
 * <p>
 * This class imports the "org.cara.template.Field", but change of "Field" won't
 * affect this class.
 * </p>
 * <p>
 * 虽然FieldParser导入了非CARA核心包中的"org.cara.template.Field"，但是Field的更改不会影响本类。
 * </p>
 * <p>
 * 使用FieldParser，需注意要将存放数据库表字段定义的文件的包导入到本工程。
 * </p>
 * 
 * @author GUSSET
 * @version 0.1, 10/30/2010
 * @see org.cara.define.Field
 * @see org.cara.extra.ExtraFileWriter
 */
public class FieldParser {

	public static void main(String[] args) {
		QuerySqlParser sp = new QuerySqlParser();
		String inputSql = "select a.id, a.name, b.address from actinfo a, address b where a.id = b.id";
		sp.parseSql(inputSql);
		FieldParser fp = new FieldParser();
		fp.generateFields(sp.getSelect());
		Field[] f = fp.getFields();
		for (int i = 0; i < f.length; i++) {
			System.out.println(f[i].label + ":" + f[i].length);
		}
	}

	/**
	 * Generate fields.
	 * <p>
	 * 通过由Query SQL Parser解析得到select数组生成Field对象数组。
	 * </p>
	 * 
	 * @param select
	 */
	public void generateFields(String[] select) {
		fields = new Field[select.length];
		for (int i = 0; i < select.length; i++) {
			fields[i] = this.getField(select[i]);
		}
	}

	/**
	 * Generate Field by input SQL segment.
	 * <p>
	 * 通过输入查询字段得到Field对象。
	 * </p>
	 * <p>
	 * 输入示例：user.id。输出示例：User.id(User类的成员id(Field类))。
	 * </p>
	 * 
	 * @param sqlPart
	 */
	private Field getField(String sqlPart) {
		Field field = null;
		String[] className = sqlPart.split("\\.");
		if (className.length != 2) {
			// Incorrect Input
			return null;
		}
		try {
			/* Use Java Reflection to generate Field. */
			String classNameTmp = className[0].substring(0, 1).toUpperCase()
					+ className[0].substring(1, className[0].length())
							.toLowerCase();
			String methodNameTmp = className[1].substring(0, 1).toUpperCase()
					+ className[1].substring(1, className[1].length())
							.toLowerCase();
			Class ctmp = Class.forName(packageName + "." + classNameTmp);
			Object otmp = ctmp.newInstance();
			Method mtmp = ctmp.getMethod("get" + methodNameTmp, null);
			field = (Field) mtmp.invoke(otmp, null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return field;
	}

	/**
	 * 存放得到的Field对象数组。
	 * 
	 * @return
	 */
	public Field[] getFields() {
		return fields;
	}

	/**
	 * 得到存放数据库表定义的package名。
	 * 
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * 设置存放数据库表定义的package名。
	 * 
	 * @param packageName
	 */
	public void setPackageName(String pName) {
		packageName = pName;
	}

	/**
	 * The array is used for Field storage
	 * <p>
	 * 存放得到的Field对象数组。
	 * </p>
	 */
	private Field[] fields;

	/**
	 * The value is used for package's name storage.
	 * <p>
	 * 存放数据库表定义的package名。
	 * </p>
	 */
	private static String packageName = CaraManager.getValue("ImportName");
}
