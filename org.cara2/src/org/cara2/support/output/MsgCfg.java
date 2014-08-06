package org.cara2.support.output;

import java.util.List;

import org.cara2.core.GlobalValue;
import org.cara2.support.input.Field;

/**
 * Cara2 Project<br>
 * MessageConfig Support.
 * 
 * @author bkin
 */
public class MsgCfg {

	private String txno = "";
	private String name = "";
	private boolean isSql = false;
	private boolean isSend = false;

	public String geneMsgCfg(List<Field> flds) {

		String lineFeed = GlobalValue.getLineFeed();

		StringBuilder sb = new StringBuilder("	<message>" + lineFeed);
		sb.append("		<id>" + txno + "</id>" + lineFeed);
		sb.append("		<name>" + name + "</name>" + lineFeed);
		sb.append("		<fieldsList> ");

		if (isSql) {
			// 是9999查询
			if (isSend) {
				// 上送报文
				sb.append("sqlResend,");
			} else {
				// 下回报文
				sb.append("errcode,fieldsCount,sqlResend,ListRecordCount,");
			}
		}
		// 非9999查询暂未做处理

		sb.append(flds.get(0).getId());
		for (int i = 1; i < flds.size(); i++) {
			sb.append("," + flds.get(i).getId());
		}
		sb.append(lineFeed + "		<defaultValue> </defaultValue>" + lineFeed);
		sb.append("	</message>" + lineFeed);

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

	public boolean isSql() {
		return isSql;
	}

	public void setSql(boolean isSql) {
		this.isSql = isSql;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

}
