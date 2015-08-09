package org.cb.zframe.freemarker;

import junit.framework.TestCase;

public class ReflectionHelperTest extends TestCase {
	public void testInitFromClass() {
		GearClass c = new GearClass("org.openfans.gear.sample.Person");
		assertEquals("org.openfans.gear.sample", c.getPack());
		assertEquals("org.openfans.gear", c.getRootPack());
		assertEquals("sample", c.getModule());
		assertEquals("Person", c.getName());
		assertEquals("person", c.getSpell());
//		assertEquals(5, c.getFields().size());
		for (GearField f : c.getFields()) {
			System.out.println("name:"+f.getName()+" type:"+f.getType());
		}
	}

	/*public void testReflection() {
		System.out.println(User.class.getPackage().getName());
		GearClass c = new GearClass(User.class);
		String name = User.class.getName();
		name = name.substring(name.lastIndexOf(".") + 1);
		c.setName(name);
		c
				.setSpell(name.substring(0, 1).toLowerCase().concat(
						name.substring(1)));
		System.out.println("name:" + c.getName() + ",spell:" + c.getSpell());
		Field[] f = User.class.getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			GearField field = new GearField(f[i]);
			field.setName(f[i].getName());
			field.setType(f[i].getGenericType().toString());
			for (int j = 0; j < f[i].getAnnotations().length; j++) {
				String d = getAnnotationValue(f[i].getAnnotations()[j],
						"columnDefinition");
				if (d.length() > 1)
					field.setDisplayName(d);
			}
		}

		for (int i = 0; i < f.length; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < f[i].getAnnotations().length; j++) {
				sb.append(f[i].getAnnotations()[j]);
				System.out.println("annotationName:"
						+ getAnnotationName(f[i].getAnnotations()[j]));
				System.out.println("length:"
						+ getAnnotationValue(f[i].getAnnotations()[j],
								"columnDefinition"));
			}
			System.out.println("field:" + f[i].getName() + " ,type:"
					+ f[i].getGenericType() + " ,annotation:" + sb.toString());

		}
	}

	private String getAnnotationName(Annotation annotation) {
		return annotation.annotationType().getName();
	}

	private String getAnnotationValue(Annotation annotation, String key) {
		String s = annotation.toString();
		s = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
		String[] keyValuePairs = s.split(",");
		for (int i = 0; i < keyValuePairs.length; i++) {
			String[] keyValuePair = keyValuePairs[i].split("=");
			if (keyValuePair.length > 1 && key.equals(keyValuePair[0].trim())) {
				return keyValuePair[1];
			}
		}
		return "";
	}*/
}
