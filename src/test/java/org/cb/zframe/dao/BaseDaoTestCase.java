package org.cb.zframe.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Base class for running DAO tests.
 * 
 * @author mraible
 */
public abstract class BaseDaoTestCase extends
		AbstractTransactionalDataSourceSpringContextTests {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Sets AutowireMode to AUTOWIRE_BY_NAME and configures all context files
	 * needed to tests DAOs.
	 * 
	 * @return String array of Spring context files.
	 */
	protected String[] getConfigLocations() {
		setAutowireMode(AUTOWIRE_BY_NAME);
		return new String[] { "/applicationContext-resources.xml",
				"classpath:/applicationContext-dao.xml",
				"/applicationContext-service.xml",
				"classpath*:/**/applicationContext.xml" };
	}

	/**
	 * Create a HibernateTemplate from the SessionFactory and call flush() and
	 * clear() on it. Designed to be used after "save" methods in tests:
	 * http://issues.appfuse.org/browse/APF-178.
	 */
	protected void flush() {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				(SessionFactory) applicationContext.getBean("sessionFactory"));
		hibernateTemplate.flush();
		hibernateTemplate.clear();
	}
}
