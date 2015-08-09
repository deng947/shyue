package org.cb.zframe.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassView {
	/**
	 * 页面上显示的中文Label，如"部门"
	 * 
	 * @return
	 */
	String displayName() default "";

	/**
	 * 被其它对象关联时在页面展示的可识别字段<br>
	 * 例如Dept关联Org,Org上指定了显示name，则在Dept页面对应的Org会显示org.name
	 * 
	 * @return
	 */
	String displayField() default "name";
}
