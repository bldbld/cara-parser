/*
 * CARA Project!
 */
package org.cara.define;

/**
 * Definition of Field.
 * 
 * @author GUSSET
 * @version 0.1, 10/28/2010
 * @see org.cara.core.FieldParser
 * @see org.cara.define.FieldLegthOutput
 * @see org.cara.extra.ExtraFileWriter
 */
public class Field {

	/**
	 * The constructor using all fields.
	 */
	public Field(int length, String label) {
		super();
		this.length = length;
		this.label = label;
	}

	/** This value is used for Field's length storage */
	public int length;

	/** This value is used for Field's label storage */
	public String label;
}
