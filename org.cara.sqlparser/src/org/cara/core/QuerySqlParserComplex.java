/*
 * CARA Project!
 */
package org.cara.core;

/** 
 * Complex Edition of Query SQL Parser. NO USED NOW.
 * 
 * @author GUSSET
 * @version 0.1, 10/28/2010
 */
public class QuerySqlParserComplex {

	public static void main(String[] args) {
		QuerySqlParserComplex test = new QuerySqlParserComplex();
		String testSql = "select a.id,b.id from act a, bct b where a.id = b.id";
		// String testSql2 =
		// "select status, result, opp_actno_r, opp_name_r, opp_acttype_r, opp_open_brno,opp_brno_r, opp_brname_r, opp_brno_type FROM smpaccinq where id='2010102500002999'"
		// ;
		test.parseSql(testSql);
		// test.generateSqlCode(testSql);

	}

	/**
	 * Format the SQL, and generate the Java code.
	 */
	public String generateSqlCode(String inputSql) {
		try {
			String sql = this.parseSql(inputSql);
			String[] recvSqls = sql.split("select|from|where");
			// select
			recvSqls[1] = recvSqls[1].trim().replaceAll(",", "+\"' , '\"+");
			// from
			recvSqls[2] = recvSqls[2].trim().replaceAll(",", "+\"' , '\"+");
			// where
			String[] wheres = recvSqls[3].trim().split(" ");
			for (int i = 0; i < wheres.length; i++) {
				System.out.println(wheres[i]);
				if (wheres[i].indexOf(".") < 0 && wheres[i] != null
						&& !wheres[i].equals("")) {
					wheres[i] = "+\"' " + wheres[i] + " '\"+";
				}
			}
			// 
			StringBuilder ans = new StringBuilder();
			ans.append("\"select '\"+" + recvSqls[1]);
			ans.append("+\"' from '\"+" + recvSqls[2] + "+\"' where '\"+");
			for (int i = 0; i < wheres.length; i++) {
				ans.append(wheres[i]);
			}
			System.out.println(ans.toString());
			return ans.toString();
		} catch (ArrayIndexOutOfBoundsException e) {
			// Runtime Exception: ArrayIndexOutOfBoundsException.
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Parse the SQL sentence. Input format��"select ... from ... where ...".
	 * Must include the "where ...". Now it do not support Single-table
	 * querying.
	 */
	public String parseSql(String inputSql) {
		try {
			String[] recvSqls = inputSql.split("select|from|where");
			if (recvSqls.length <= 3) {
				return null;
			}
			String[] from = recvSqls[2].trim().split(",");
			String[] tmp2 = from[0].trim().split(" ");
			if (tmp2[0].length() == 1) {
				/* Single-table querying, do nothing. */
				return null;
			} else if (tmp2[0].equals(tmp2[1])) {
				/* Formatted SQL, do nothing. */
			} else {
				/* Unformatted SQL. */
				for (int i = 0; i < from.length; i++) {
					String[] fromTmp = from[i].trim().split(" ");
					from[i] = fromTmp[0].trim();
				}
				String[] selects = recvSqls[1].split(",");
				for (int i = 0; i < selects.length; i++) {

				}
				for (int i = 0; i < from.length; i++) {
					String[] fromTmp = from[i].trim().split(" ");
					recvSqls[1] = recvSqls[1].replaceAll(fromTmp[1] + ".",
							fromTmp[0] + ".");
					recvSqls[3] = recvSqls[3].replaceAll(fromTmp[1] + ".", " "
							+ fromTmp[0] + ".");
					from[i] = fromTmp[0].trim();
				}
			}
			// Splice the SQL.
			StringBuilder ans = new StringBuilder();
			ans.append("select " + recvSqls[1] + " from ");
			for (int i = 0; i < from.length; i++) {
				if (i != from.length - 1) {
					ans.append(from[i] + ", ");
				} else {
					ans.append(from[i] + " ");
				}
			}
			ans.append("where " + recvSqls[3]);
			System.out.println(ans.toString());
			return ans.toString();
		} catch (ArrayIndexOutOfBoundsException e) {
			// Runtime Exception: ArrayIndexOutOfBoundsException.
			e.printStackTrace();
			return null;
		}
	}

}
