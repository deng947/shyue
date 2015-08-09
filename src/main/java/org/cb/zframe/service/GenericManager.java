package org.cb.zframe.service;

import java.io.Serializable;
import java.util.List;

import org.cb.zframe.dao.PaginatedListImpl;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericManager<T, PK extends Serializable> {

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
	 *            the identifier (primary key) of the object to get
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the updated object
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
	 * 获得Page对象，目前是displaytag分页对象，可方便切换为其它对象
	 * 
	 * @param page
	 *            page对象，先注入当前页和每页记录数
	 * @return
	 */
	PaginatedListImpl getPaginatedList(PaginatedListImpl page);

}
