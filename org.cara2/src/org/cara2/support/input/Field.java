package org.cara2.support.input;

/**
 * Cara2 Project<br>
 * 定义fieldsConfig.xml中每个Field的属性。
 * 
 * @author bkin
 */
public class Field {

	private String id;
	private String name;
	private String length;
	private String formatType;
	private String formatAttribute;
	private String defaultValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public String getFormatAttribute() {
		return formatAttribute;
	}

	public void setFormatAttribute(String formatAttribute) {
		this.formatAttribute = formatAttribute;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
