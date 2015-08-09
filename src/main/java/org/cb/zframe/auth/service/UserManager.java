package org.cb.zframe.auth.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import org.cb.zframe.auth.Dept;
import org.cb.zframe.auth.Org;
import org.cb.zframe.auth.Role;
import org.cb.zframe.auth.User;
import org.cb.zframe.service.GenericManager;
public interface UserManager extends GenericManager<User, Long> {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException;

	/**
	 * 保存用户并进行加密
	 * 
	 * @param user
	 * @param changePassword
	 *            是否更改密码
	 * @return
	 */
	public User saveUser(User user, boolean changePassword);

	public List<User> getUserByDeptAndRole(Dept dept, Role role);
	
	public List<User> getUserByRoleAndOrg(Role role, Org org);
	
	public List<User> getUserByRole(Role role);
	
	public User getUserByName(String name);
	
	public List<User> getUserByNameStart(String name);

}
