package org.cb.zframe.auth.service;

import java.util.List;

import org.cb.zframe.auth.Dept;
import org.cb.zframe.auth.Org;
import org.cb.zframe.service.GenericManager;

/**
 * 部门Manager，扩展GenericManager并提供自己的业务方法
 * @author pesome
 * @date 2009-2-10
 */
public interface DeptManager extends GenericManager<Dept, Long> {

	public Dept getDeptByNameAndOrg(String name, Org org);

	public List<Dept> getDeptListByOrg(Org org);

}