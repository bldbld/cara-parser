package org.cara2.support.output;

import org.cara2.core.GlobalValue;

/**
 * Cara2 Project<br>
 * PackageConfig Support.
 * 
 * @author bkin
 */
public class PkgCfg {
	private String txno = "";
	private String name = "";
	private String clientMsgBody = "";
	private String serverMsgBody = "";
	private boolean isSql = false;

	public String genePkgCfg() {
		StringBuilder sb = new StringBuilder("<packagedMessages ID=\"");
		if (isSql) {
			sb.append("sql" + txno);
		} else {
			sb.append(txno);
		}
		sb.append("\" name=\"" + name + "\" ");
		sb.append("\" clientMessages=\"H0001#" + clientMsgBody + "\" ");
		sb.append("\" serverMessages=\"H0002#" + serverMsgBody + "\" ");
		sb.append(GlobalValue.getLineFeed());
		return sb.toString();
	}

	public boolean isSql() {
		return isSql;
	}

	public void setSql(boolean isSql) {
		this.isSql = isSql;
	}

	public String getClientMsgBody() {
		return clientMsgBody;
	}

	public void setClientMsgBody(String clientMsgBody) {
		this.clientMsgBody = clientMsgBody;
	}

	public String getServerMsgBody() {
		return serverMsgBody;
	}

	public void setServerMsgBody(String serverMsgBody) {
		this.serverMsgBody = serverMsgBody;
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
