/*
 * CARA Project!
 */
package org.cara.core;
 
import java.util.HashMap;
import java.util.Map;

/**
 * Query SQL Parser. This is a simple edition of Query SQL Parser.
 * 
 * @author GUSSET
 * @version 0.1, 10/28/2010
 */
public class QuerySqlParser {
	
	public static void main (String [] args){
		String sql = "select id from user where a= s";
		QuerySqlParser sp = new QuerySqlParser ();
		sp.parseSql(sql);
	}

	/**
	 * Parse the SQL sentence. Input format "select ... from ... where ...".
	 * Must include the "where ...".
	 * <p>
	 * 解析查询SQL语句。并将结果中对应数据更新到select，from数组中。
	 * </p>
	 * <p>
	 * 解析结果格式：select user.id from user user where ...。
	 * </p>
	 */
	public void parseSql(String inputSql) {
		try {
			String[] recvSqls = inputSql.toLowerCase().split(
					"select|from|where");
			if (recvSqls.length <= 3) {
				return;
			}
			String[] selects = recvSqls[1].split(",");
			String[] froms = recvSqls[2].trim().split(",");
			String[] tmp2 = froms[0].trim().split(" ");
			if (tmp2.length == 1) {
				/* Single-table querying */
				// 由于是单表查询，from数组只需要1个位置。
				from = new String[1];
				if (selects[0].trim().indexOf(".") >= 0) {
					// 输入的SQL为如下形式：
					// select user.id from user.
					for (int i = 0; i < selects.length; i++) {
						selects[i] = selects[i].trim();
					}
					select = selects;
					
					return;
				}else {
					// 输入的SQL为如下形式：
					// select id from user.
					from[0] = recvSqls[2].trim();
					for (int i = 0; i < selects.length; i++) {
						selects[i] = from[0] + "." + selects[i].trim();
					}
					select = selects;
					return;
				}
			} else if (tmp2[0].equals(tmp2[1])) {
				/* Formatted SQL, do nothing. */
				// 输入的SQL为如下形式：
				// select user.id from user (as) user.
				return;
			} else {
				/* Unformatted SQL. */
				// 输入的SQL为如下形式：
				// select a.id from user a.
				for (int i = 0; i < froms.length; i++) {
					String[] fromTmp = froms[i].trim().split(" ");
					froms[i] = fromTmp[0].trim();
					tableMap.put(fromTmp[1].trim(), fromTmp[0].trim());
				}
				from = froms;
				for (int i = 0; i < selects.length; i++) {
					String[] selectTmp = selects[i].trim().split("\\.");
					selects[i] = tableMap.get(selectTmp[0]) + "."
							+ selectTmp[1];
				}
				select = selects;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// Runtime Exception: ArrayIndexOutOfBoundsException.
			// Reason: Incorrect database querying SQL
			e.printStackTrace();
		}
	}

	/**
	 * Return the Array that storage "select" clause.
	 * <p>
	 * 返回select数组。
	 * </p>
	 */
	public String[] getSelect() {
		return select;
	}

	/**
	 * Return the Array that storage "from" clause.
	 * <p>
	 * 返回from数组。
	 * </p>
	 */
	public String[] getFrom() {
		return from;
	}

	/**
	 * The HashMap is used for Field storage
	 * <p>
	 * 记录"from"字句中，表名和别名的映射关系。
	 * </p>
	 * <p>
	 * 记录方式："<别名,表名>"。
	 * </p>
	 */
	private Map<String, String> tableMap = new HashMap<String, String>();

	/**
	 * The array is used for "select" clause storage
	 * <p>
	 * 存储解析后的"select"子句中的字段。
	 * </p>
	 * <p>
	 * 记录方式："表名.字段名"。
	 * </p>
	 */
	private String[] select;

	/**
	 * The array is used for "from" clause storage
	 * <p>
	 * 存储解析后的"from"子句中的字段。
	 * </p>
	 * <p>
	 * 记录方式："表名"。
	 * </p>
	 */
	private String[] from;
}