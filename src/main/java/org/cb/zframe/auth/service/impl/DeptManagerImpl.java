package org.cb.zframe.auth.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.cb.zframe.auth.Dept;
import org.cb.zframe.auth.Org;
import org.cb.zframe.auth.service.DeptManager;
import org.cb.zframe.dao.GenericDao;
import org.cb.zframe.service.impl.GenericManagerImpl;

/**
 * 部门Manager
 * 
 * @author pesome
 * @date Nov 14, 2008
 */
public class DeptManagerImpl extends GenericManagerImpl<Dept, Long> implements DeptManager {

	private final Logger log = LoggerFactory.getLogger(getClass());
	public GenericDao<Dept, Long> deptDao;

	public DeptManagerImpl(GenericDao<Dept, Long> deptDao) {
		super(deptDao);
		this.deptDao = deptDao;
	}

	/* (non-Javadoc)
	 * @see com.ibm.contract.service.impl.DeptManager#getDeptByNameAndOrg(java.lang.String, com.ibm.contract.auth.Org)
	 */
	public Dept getDeptByNameAndOrg(String name,Org org) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("name", name);
		queryParams.put("org", org);
		List<Dept> l = deptDao.findByNamedQuery("dept.findDeptByNameAndOrg", queryParams);
		return l.get(0);
	}

	/* (non-Javadoc)
	 * @see com.ibm.contract.service.impl.DeptManager#getDeptListByOrg(com.ibm.contract.auth.Org)
	 */
	public List<Dept> getDeptListByOrg(Org org) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("org", org);
		List<Dept> l = deptDao.findByNamedQuery("dept.findDeptByOrg", queryParams);
		return l;
	}
}
