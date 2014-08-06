package test.db;

import org.cara.define.Field;

public class Address {

	public static Field getId() {
		return id;
	}

	public static void setId(Field id) {
		Address.id = id;
	}

	public static Field getAddress() {
		return address;
	}

	public static void setAddress(Field address) {
		Address.address = address;
	}

	public static Field id = new Field(32, "账号");
	public static Field address = new Field(60, "地址");
}
