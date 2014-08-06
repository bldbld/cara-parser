package org.cara2.support.input;

/**
 * Cara2 Project <br>
 * ���������FieldConfig�����ݡ�
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
				// �˴���δ������
			}

			fci.addField(fld);
		}
		return fci;
	}
	
	/**
	 * ��ʽ���ֶ�
	 * 
	 * @param abt
	 * @return
	 */
	public String getFldAbtValue(String abt) {
		return abt.substring(1, abt.lastIndexOf("\""));
	}
	
	public static void main (String [] args){
		String str= "<field ID=\"EJFNO\" name=\"EJF���\" length=\"7\" format_type=\"string\" format_attribute=\"left\" defaultValue=\"0000000\" /><field ID=\"CLSNO\" name=\"CLS���\" length=\"6\" format_type=\"string\" format_attribute=\"left\" defaultValue=\"000000\" />";
		FldCfgParser xfp = new FldCfgParser();
		FldCfgInput fci = xfp.parserStr(str);
		for (int i=0;i<fci.getFieldConfig().size();i++){
			System.out.println (fci.getFieldConfig().get(i).getId()+" "+fci.getFieldConfig().get(i).getName());
		}
	}
}
