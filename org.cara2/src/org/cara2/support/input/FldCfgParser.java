package org.cara2.support.input;

/**
 * Cara2 Project <br>
 * 解析输入的FieldConfig的数据。
 * 
 * @author bkin
 */
public class FldCfgParser {
	
	private String inputStr;

	public FldCfgInput parserStr(String str) {
		inputStr = str;
		FldCfgInput fci = new FldCfgInput();

		String[] inputFields = inputStr.split("<field");
		for (int i = 0; i < inputFields.length; i++) {
			if (inputFields[i].equals("") || inputFields[i] == null) {
				continue;
			}
			
			Field fld = new Field();
			String[] inputAbt = inputFields[i].split("=");

			if (inputAbt[0].trim().equals("ID")) {
				fld.setId(this.getFldAbtValue(inputAbt[1]));
				fld.setName(this.getFldAbtValue(inputAbt[2]));
				fld.setLength(this.getFldAbtValue(inputAbt[3]));
				fld.setFormatType(this.getFldAbtValue(inputAbt[4]));
				fld.setFormatAttribute(this.getFldAbtValue(inputAbt[5]));
				fld.setDefaultValue(this.getFldAbtValue(inputAbt[6]));
			} else {
				// 此处暂未作处理
			}

			fci.addField(fld);
		}
		return fci;
	}
	
	/**
	 * 格式化字段
	 * 
	 * @param abt
	 * @return
	 */
	public String getFldAbtValue(String abt) {
		return abt.substring(1, abt.lastIndexOf("\""));
	}
	
	public static void main (String [] args){
		String str= "<field ID=\"EJFNO\" name=\"EJF序号\" length=\"7\" format_type=\"string\" format_attribute=\"left\" defaultValue=\"0000000\" /><field ID=\"CLSNO\" name=\"CLS序号\" length=\"6\" format_type=\"string\" format_attribute=\"left\" defaultValue=\"000000\" />";
		FldCfgParser xfp = new FldCfgParser();
		FldCfgInput fci = xfp.parserStr(str);
		for (int i=0;i<fci.getFieldConfig().size();i++){
			System.out.println (fci.getFieldConfig().get(i).getId()+" "+fci.getFieldConfig().get(i).getName());
		}
	}
}
