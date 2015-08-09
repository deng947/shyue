package org.cb.zframe.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAll();

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(PK id);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	T save(T object);

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to remove
	 */
	void delete(PK id);
	
	/**
	 * Generic method to batch delete object based on class and id[]
	 * @param id
	 * @param isBatch boolean use hql to delete, can only use if no ORM relationship
	 */
	void delete(PK[] ids,boolean isBatch);

	/**
	 * Gets all records without duplicates.
	 * <p>
	 * Note that if you use this method, it is imperative that your model
	 * classes correctly implement the hashcode/equals methods
	 * </p>
	 * 
	 * @return List of populated objects
	 */
	List<T> getAllDistinct();

	/**
	 * Find a list of records by using a named query
	 * 
	 * @param queryName
	 *            query name of the named query
	 * @param queryParams
	 *            a map of the query names and the values
	 * @return a list of the records found
	 */
	List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

	/**
	 * Find a list of records by using native query
	 * 
	 * @param sql
	 * @return
	 */
	List<T> findByNativeSQL(final String sql);

	/**
	 * Find a list of records by using HQL
	 * 
	 * @param sql
	 * @return
	 */
	List<T> findBySQL(final String sql);

	List<Object[]> findByHQL(final String hql);

	/**
	 * 获得Page对象，目前是displaytag分页对象，可方便切换为其它对象
	 * 
	 * @param page
	 *            page对象，先注入当前页和每页记录数
	 * @return
	 */
	PaginatedListImpl getPaginatedList(PaginatedListImpl page);

	/**
	 * 获得分页对象列表，针对非displaytag分页
	 * 
	 * @param query
	 *            hql
	 * @param pageNum
	 *            当前页码，1开头
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public List<T> getPagedList(String query, int pageNum, int pageSize);

	/**
	 * 获得分页对象列表，针对非displaytag分页
	 * 
	 * @param query
	 *            hql
	 * @param pageNum
	 *            当前页码，1开头
	 * @param pageSize
	 *            每页记录数
	 * @param orderBy
	 *            排序字段名
	 * @param orderType
	 *            排序类型asc,desc
	 * @return
	 */
	public List<T> getPagedList(String query, int pageNum, int pageSize,
			String orderBy, String orderType);

	public List<T> findByQueryList(List<QueryObject> l);
	
}