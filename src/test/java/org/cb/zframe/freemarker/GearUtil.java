package org.cb.zframe.freemarker;

public class GearUtil {
	public static String getSpellByName(String name) {
		return name.substring(0, 1).toLowerCase().concat(
				name.substring(1));
	}
	
	public static String getPackByFullName(String className){
		return className.substring(0, className.lastIndexOf("."));
	}
	
	public static String getNameByFullName(String className){
		return className.substring(className.lastIndexOf(".")+1);
	}
	
	public static String firstToUppercase(String name){
		return name.substring(0, 1).toUpperCase().concat(
				name.substring(1));
	}
}
