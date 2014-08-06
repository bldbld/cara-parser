package org.cara2.support.output;

import java.util.List;

import org.cara2.core.GlobalValue;
import org.cara2.support.input.Field;

/**
 * Cara2 Project<br>
 * SqlConfig Support.
 * 
 * @author bkin
 */
public class SqlCfg {
	private String txno = "";
	private String name = "";

	public String geneSqlCfg(List<Field> flds) {
		String lineFeed = GlobalValue.getLineFeed();

		StringBuilder sb = new StringBuilder("	<sql>" + lineFeed);
		sb.append("		<id>" + txno + "</id>" + lineFeed);
		sb.append("		<name>" + name + "</name>" + lineFeed);
		sb.append("		<strSql><![CDATA[^${ibps_sql_id}");
		for (int i = 0; i < flds.size(); i++) {
			sb.append("^${" + flds.get(i).getId() + "}");
		}
		sb.append("^]]></strSql>" + lineFeed);
		sb.append("	</sql>");

		return sb.toString();
	}

	public String getTxno() {
		return txno;
	}

	public void setTxno(String txno) {
		this.txno = txno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
