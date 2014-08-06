package org.cara2.core;

import java.util.List;

import org.cara2.support.file.FileOutputer;
import org.cara2.support.input.Field;
import org.cara2.support.input.FldCfgInput;
import org.cara2.support.input.FldCfgParser;
import org.cara2.support.output.MsgCfg;
import org.cara2.support.output.PkgCfg;
import org.cara2.support.output.SqlCfg;

/**
 * Cara2 Project<br>
 * Core Process.
 * 
 * @author bkin
 */
public class CoreProcess {
	private FileOutputer fo;
	private boolean isSuccess = false;

	public void proceed(String inputValues, String txno, String name,
			boolean isSql, boolean isSend) {
		isSuccess = false;
		fo = new FileOutputer();
		// Set the file's name
		fo.init("Cara2File_" + txno);
		String str = "";

		// Parser inputed FieldConfig
		FldCfgParser fcp = new FldCfgParser();
		FldCfgInput fci = fcp.parserStr(inputValues);
		List<Field> flds = fci.getFieldConfig();

		// Generate the MessageConfig
		MsgCfg mc = new MsgCfg();
		mc.setTxno(txno);
		mc.setName(name);
		mc.setSql(isSql);
		mc.setSend(isSend);
		str = mc.geneMsgCfg(flds);
		fo.write(str);

		// Generate the PackageConfig
		PkgCfg pc = new PkgCfg();
		pc.setTxno(txno);
		pc.setName(name);
		pc.setSql(isSql);
		str = pc.genePkgCfg();
		fo.write(str);

		// Generate the SqlConfig if the TXN is a Querying-TXN
		if (isSql) {
			SqlCfg sc = new SqlCfg();
			sc.setTxno(txno);
			sc.setName(name);
			str = sc.geneSqlCfg(flds);
			fo.write(str);
		}

		fo.close();
		str = null;
		isSuccess = true;
	}
	
	/** Whether the Process is Success. */
	public boolean isSuccess() {
		return isSuccess;
	}
}
