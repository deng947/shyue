package org.cb.zframe.freemarker;

import java.lang.reflect.Field;

import org.cb.zframe.annotation.FieldView;

public class GearField {
	public static String STRING = "string";
	public static String INT = "int";
	public static String LONG = "long";
	public static String DOUBLE = "double";
	public static String BOOLEAN = "boolean";
	public static String FLOAT = "float";
	public static String POJO = "pojo"; // Many-to-one关联其它pojo
	public static String COLLECTION = "collection"; // Collection及List,Set都定义为此
	private String name;
	private String type; // 类型
	private String displayName; // 页面上的显示
	private GearClass pojo;
	private String upper; // 字段的首字母大写，用于拼set,get方法
	private int size = 30; // 页面上html input的size

	public GearField(Field field) {
		this.name = field.getName();
		this.upper = GearUtil.firstToUppercase(this.name);
		setFieldView(field);
		setType(field);
	}

	private void setFieldView(Field field) {
		this.displayName = field.getName();
		if (field.isAnnotationPresent(FieldView.class)) {
			FieldView fv = field.getAnnotation(FieldView.class);
			this.displayName = fv.displayName();
			this.size = fv.size();
		}
	}

	private void setType(Field field) {
		String type = field.getGenericType().toString();
		if ("class java.lang.String".equals(type)) {
			this.type = STRING;
		} else if ("int".equals(type) || "class java.lang.Integer".equals(type)) {
			this.type = INT;
		} else if ("float".equals(type) || "class java.lang.Float".equals(type)) {
			this.type = FLOAT;
		} else if ("double".equals(type)
				|| "class java.lang.Double".equals(type)) {
			this.type = DOUBLE;
		} else if ("boolean".equals(type)
				|| "class java.lang.Boolean".equals(type)) {
			this.type = BOOLEAN;
		} else if (type.startsWith("class ")) {// relationship to other pojo
			this.type = POJO;
			String fullName = type.substring(type.indexOf(" ") + 1);
			this.pojo=new GearClass(fullName);
		} else {
			this.type = type;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public GearClass getPojo() {
		return pojo;
	}

	public void setPojo(GearClass pojo) {
		this.pojo = pojo;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getUpper() {
		return upper;
	}

	public void setUpper(String upper) {
		this.upper = upper;
	}

}
