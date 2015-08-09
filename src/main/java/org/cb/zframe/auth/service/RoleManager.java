package org.cb.zframe.auth.service;

import org.cb.zframe.auth.Role;
import org.cb.zframe.service.GenericManager;

public interface RoleManager extends GenericManager<Role, Long> {

	public Role getByRoleName(String name);

}