package org.cb.zframe.auth.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.cb.zframe.auth.Role;
import org.cb.zframe.auth.service.RoleManager;
import org.cb.zframe.dao.GenericDao;
import org.cb.zframe.service.impl.GenericManagerImpl;

/**
 * RoleManager
 * 
 * @author chenb
 * @date Dem 1, 2008
 */
public class RoleManagerImpl extends GenericManagerImpl<Role, Long> implements
		RoleManager {

	private final Logger log = LoggerFactory.getLogger(getClass());
	public GenericDao<Role, Long> roleDao;

	public RoleManagerImpl(GenericDao<Role, Long> roleDao) {
		super(roleDao);
		this.roleDao = roleDao;
	}

	public Role getByRoleName(String name) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("name", name);
		List<Role> l = roleDao.findByNamedQuery("role.findByRoleName", queryParams);
		return l.get(0);
	}

}
