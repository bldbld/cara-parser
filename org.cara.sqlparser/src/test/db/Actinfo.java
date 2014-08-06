package test.db;

import org.cara.define.Field;

public class Actinfo {

	public static Field getId() {
		return id;
	}

	public static Field getName() {
		return name;
	}

	public static Field getEmail() {
		return email;
	}

	public static Field id = new Field(32, "账号");
	public static Field name = new Field(60, "名称");
	public static Field email = new Field(60, "Email");
}
