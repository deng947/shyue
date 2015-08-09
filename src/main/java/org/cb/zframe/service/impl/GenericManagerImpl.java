package org.cb.zframe.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.cb.zframe.dao.GenericDao;
import org.cb.zframe.dao.PaginatedListImpl;
import org.cb.zframe.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;com.ibm.contract.service.impl.GenericManagerImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;com.ibm.contract.dao.hibernate.GenericDaoHibernate&quot;&gt;
 *                 &lt;constructor-arg value=&quot;com.ibm.contract.model.User&quot;/&gt;
 *                 &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
 * <p>
 * If you're using iBATIS instead of Hibernate, use:
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;com.ibm.contract.service.impl.GenericManagerImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;com.ibm.contract.dao.ibatis.GenericDaoiBatis&quot;&gt;
 *                 &lt;constructor-arg value=&quot;com.ibm.contract.model.User&quot;/&gt;
 *                 &lt;property name=&quot;dataSource&quot; ref=&quot;dataSource&quot;/&gt;
 *                 &lt;property name=&quot;sqlMapClient&quot; ref=&quot;sqlMapClient&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericManagerImpl<T, PK extends Serializable> implements
		GenericManager<T, PK> {
	/**
	 * Log variable for all child classes. Uses LoggerFactory.getLogger(getClass())
	 * from Commons Logging
	 */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * GenericDao instance, set by constructor of this class
	 */
	protected GenericDao<T, PK> genericDao;

	/**
	 * Public constructor for creating a new GenericManagerImpl.
	 * 
	 * @param genericDao
	 *            the GenericDao to use for persistence
	 */
	public GenericManagerImpl(final GenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
		log.info("GenericManger Inited");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> getAll() {
		return genericDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public T get(PK id) {
		return genericDao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists(PK id) {
		return genericDao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public T save(T object) {
		return genericDao.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(PK id) {
		genericDao.delete(id);
	}

	/**
	 * Generic method to batch delete object based on class and id[]
	 * 
	 * @param id
	 */
	public void delete(PK[] ids, boolean isBatch) {
		genericDao.delete(ids, isBatch);
	}

	/**
	 * {@inheritDoc}
	 */
	public PaginatedListImpl getPaginatedList(PaginatedListImpl page) {
		return genericDao.getPaginatedList(page);
	}
}
