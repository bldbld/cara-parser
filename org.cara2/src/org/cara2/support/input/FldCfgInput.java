package org.cara2.support.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Cara2 Project <br>
 * ���������FieldsConfig����
 * 
 * @author bkin
 */
public class FldCfgInput {
	private List<Field> fieldsConfig;

	public FldCfgInput() {
		fieldsConfig = new ArrayList<Field>();
	}

	public void addField(Field e) {
		fieldsConfig.add(e);
	}

	public List<Field> getFieldConfig() {
		return this.fieldsConfig;
	}
}
