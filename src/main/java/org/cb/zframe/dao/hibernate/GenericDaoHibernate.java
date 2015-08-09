package org.cb.zframe.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.cb.zframe.dao.GenericDao;
import org.cb.zframe.dao.PaginatedListImpl;
import org.cb.zframe.dao.QueryObject;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id=&quot;fooDao&quot; class=&quot;com.ibm.contract.dao.hibernate.GenericDaoHibernate&quot;&gt;
 *          &lt;constructor-arg value=&quot;com.ibm.contract.model.Foo&quot;/&gt;
 *          &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LoggerFactory.getLogger(getClass())
	 * from Commons Logging
	 */
	private final Logger log = LoggerFactory.getLogger(getClass());
	public final Class<T> persistentClass;

	/**
	 * Constructor that takes in a class to see which type of entity to persist
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoHibernate(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return super.getHibernateTemplate().loadAll(this.persistentClass);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllDistinct() {
		Collection<T> result = new LinkedHashSet<T>(getAll());
		return new ArrayList<T>(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		T entity = (T) super.getHibernateTemplate().get(this.persistentClass,
				id);

		if (entity == null) {
			log.warn("Uh oh, '" + this.persistentClass + "' object with id '"
					+ id + "' not found...");
			throw new ObjectRetrievalFailureException(this.persistentClass, id);
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		T entity = (T) super.getHibernateTemplate().get(this.persistentClass,
				id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T save(T object) {
		return (T) super.getHibernateTemplate().merge(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(PK id) {
		super.getHibernateTemplate().delete(this.get(id));
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(PK[] ids, boolean isBatch) {
		if (isBatch) {
			StringBuffer sb = new StringBuffer();
			// 因为是batch delete 这里依赖于对象的主键属性，本框架所有对象的自动生成主键统一为"id"
			sb.append("delete from ").append(this.persistentClass.getName())
					.append(" where id in (");
			for (int i = 0; i < ids.length; i++) {
				sb.append(ids[i]).append(",");
			}
			sb.replace(sb.length() - 1, sb.length(), ")");
			super.getHibernateTemplate().bulkUpdate(sb.toString());
		} else {
			for (int i = 0; i < ids.length; i++) {
				getHibernateTemplate().delete(this.get(ids[i]));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		String[] params = new String[queryParams.size()];
		Object[] values = new Object[queryParams.size()];
		int index = 0;
		Iterator<String> i = queryParams.keySet().iterator();
		while (i.hasNext()) {
			String key = i.next();
			params[index] = key;
			values[index++] = queryParams.get(key);
		}
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				params, values);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNativeSQL(final String sql) {
		final Class<T> c = this.persistentClass;
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createSQLQuery(sql).addEntity(c).list();

					}
				});
	}

	public int getCount(final String sql) {
		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query q = session.createQuery(sql);
						return ((Long) (q.iterate().next())).intValue();
					}
				});
	}

	public List<T> getList(final String query, final int start, final int size) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query q = session.createQuery(query);
						q.setFirstResult(start);
						q.setMaxResults(size);

						return q.list();
					}
				});
	}

	public List<T> getPagedList(String query, int pageNum, int pageSize) {
		return getList(query, (pageNum - 1) * pageSize, pageSize);
	}

	public List<T> getPagedList(String query, int pageNum, int pageSize,
			String orderBy, String orderType) {
		query += " order by " + orderBy + " " + orderType;
		return getList(query, (pageNum - 1) * pageSize, pageSize);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBySQL(String sql) {
		return (List<T>) getHibernateTemplate().find(sql);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findByHQL(String hql) {
		return (List<Object[]>) getHibernateTemplate().find(hql);
	}

	/**
	 * {@inheritDoc}
	 */
	public PaginatedListImpl getPaginatedList(PaginatedListImpl page) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(c) from ").append(
				this.persistentClass.getName()).append(" c");
		appendQueryList(page, sb);
		int count = getCount(sb.toString());
		reCalcuPageNum(page, count);
		page.setFullListSize(count);

		sb = new StringBuffer();
		sb.append("select c from ").append(this.persistentClass.getName())
				.append(" c");
		appendQueryList(page, sb);
		String orderType = SortOrderEnum.DESCENDING.equals(page
				.getSortDirection()) ? "desc" : "asc";
		List l = getPagedList(sb.toString(), page.getPageNumber(), page
				.getObjectsPerPage(), "c." + page.getSortCriterion(), orderType);
		page.setList(l);
		return page;
	}

	private void appendQueryList(PaginatedListImpl page, StringBuffer sb) {
		int i = 1; // for collections elements alias such as (e1)
		if (page.getQueryList() != null) {
			for (QueryObject q : page.getQueryList()) {
				if (q.getCollectionName() != null) {
					sb.append(" join c.").append(q.getCollectionName()).append(
							" e").append(i++);
				}
			}
		}

		if (page.getQueryList() != null && page.getQueryList().size() > 0) {
			sb.append(getQueryString(page.getQueryList()));
		}
	}

	/**
	 * 根据总数和每页数量，重新计算pageNum
	 * 
	 * @param page
	 * @param count
	 */
	private void reCalcuPageNum(PaginatedListImpl page, int count) {
		int maxPageNum = count / page.getObjectsPerPage()
				+ (count % page.getObjectsPerPage() == 0 ? 0 : 1);
		if (maxPageNum < page.getPageNumber()) {
			page.setPageNumber(maxPageNum);
		}
	}

	public List<T> findByQueryList(List<QueryObject> l) {
		final String query = "from " + this.persistentClass.getName() + " c"
				+ getQueryString(l);

		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query q = session.createQuery(query);
						return q.list();
					}
				});
	}

	private StringBuffer getQueryString(List<QueryObject> l) {
		StringBuffer sb = new StringBuffer();

		int i = 0;
		int j = 1;
		for (QueryObject q : l) {
			if (i++ == 0) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}

			if (q.getCollectionName() != null) {
				sb.append("e").append(j++).append(".");
			} else {
				sb.append("c.");
			}
			sb.append(q.toString());
		}

		return sb;
	}
}
