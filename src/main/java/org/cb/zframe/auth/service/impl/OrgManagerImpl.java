package org.cb.zframe.auth.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.cb.zframe.auth.Org;
import org.cb.zframe.auth.service.OrgManager;
import org.cb.zframe.dao.GenericDao;
import org.cb.zframe.service.impl.GenericManagerImpl;

/**
 * 部门Manager
 * 
 * @author pesome
 * @date Nov 14, 2008
 */
public class OrgManagerImpl extends GenericManagerImpl<Org, Long> implements
		OrgManager {

	private final Logger log = LoggerFactory.getLogger(getClass());
	public GenericDao<Org, Long> orgDao;

	public OrgManagerImpl(GenericDao<Org, Long> orgDao) {
		super(orgDao);
		this.orgDao = orgDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.contract.service.impl.OrgManager#getOrgByNameAndOrg(java.lang
	 * .String, com.ibm.contract.auth.Org)
	 */
	public Org getOrgByNameAndOrg(String name, Org org) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("name", name);
		queryParams.put("org", org);
		List<Org> l = orgDao.findByNamedQuery("org.findOrgByNameAndOrg",
				queryParams);
		return l.get(0);
	}

	public Org getOrgByBizId(long orgId) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("orgId", orgId);
		List<Org> l = orgDao.findByNamedQuery("org.findByOrgBizId", queryParams);
		if (l == null || l.size() == 0)
			return null;
		else
			return l.get(0);
	}

}
