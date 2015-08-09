package org.cb.zframe.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface FieldView {
	/**
	 * 该字段显示的中文label，如"姓名"
	 * 
	 * @return
	 */
	String displayName() default "";

	/**
	 * 在表单中的size
	 * 
	 * @return
	 */
	int size() default 30;
}
