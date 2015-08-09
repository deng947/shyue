package org.cb.zframe.freemarker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.cb.zframe.annotation.ClassView;

public class GearClass {
	private String name;
	private String pack;
	private String rootPack;// 项目的根package，不包含module
	private String module; // module
	private String spell;
	private String displayName; // 显示在页面上的标示，如“部门”
	private String displayField;
	private List<GearField> fields;
	private boolean hasPojoField = false; // 是否有对1关联其它pojo

	public GearClass(String className) {
		Class claz = null;
		try {
			claz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String pack = claz.getPackage().getName();
		int index = pack.lastIndexOf(".");
		this.rootPack = getRootPack(pack);
		this.module = pack.substring(index + 1);
		this.pack = pack;
		this.name = GearUtil.getNameByFullName(claz.getName());
		this.displayName = this.name; // 默认显示英文名
		this.displayField = "name"; // 默认显示"name"字段
		setClassView(claz);
		this.spell = GearUtil.getSpellByName(this.name);
		setFields(claz);
	}

	private void setFields(Class claz) {
		Field[] f = claz.getDeclaredFields();
		List<GearField> l = new ArrayList<GearField>();
		for (int i = 0; i < f.length; i++) {
			GearField field = new GearField(f[i]);
			l.add(field);
			if (GearField.POJO.equals(field.getType())) {
				hasPojoField = true;
			}
		}
		this.setFields(l);
	}

	private void setClassView(Class claz) {
		if (claz.isAnnotationPresent(ClassView.class)) {
			ClassView cv = (ClassView) claz.getAnnotation(ClassView.class);
			this.displayName = cv.displayName();
			this.displayField = cv.displayField();
		}
	}

	private String getRootPack(String pack) {
		return pack.substring(0, pack.lastIndexOf("."));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayField() {
		return displayField;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public List<GearField> getFields() {
		return fields;
	}

	public void setFields(List<GearField> fields) {
		this.fields = fields;
	}

	public void addField(GearField f) {
		if (this.fields == null) {
			fields = new ArrayList<GearField>();
		}
		fields.add(f);
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRootPack() {
		return rootPack;
	}

	public void setRootPack(String rootPack) {
		this.rootPack = rootPack;
	}

	public boolean isHasPojoField() {
		return hasPojoField;
	}

	public void setHasPojoField(boolean hasPojoField) {
		this.hasPojoField = hasPojoField;
	}
}
