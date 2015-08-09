package org.cb.zframe.auth.service;

import org.cb.zframe.auth.Org;
import org.cb.zframe.service.GenericManager;

public interface OrgManager extends GenericManager<Org, Long> {
	/**
	 * 业务id查找org
	 * @param orgId
	 * @return
	 */
	public Org getOrgByBizId(long orgId);

}