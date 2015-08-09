package org.cb.zframe.service;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public abstract class BaseManagerTestCase extends
		AbstractTransactionalDataSourceSpringContextTests {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected static ResourceBundle rb = null;

	protected String[] getConfigLocations() {
		setDefaultRollback(true);
		setAutowireMode(AUTOWIRE_BY_NAME);
		return new String[] { "/applicationContext-resources.xml",
				"classpath:/applicationContext-dao.xml",
				"/applicationContext-service.xml",
				"classpath*:/**/applicationContext.xml" };
	}
}