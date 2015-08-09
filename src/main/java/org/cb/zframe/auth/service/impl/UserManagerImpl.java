package org.cb.zframe.auth.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import org.cb.zframe.auth.Dept;
import org.cb.zframe.auth.Org;
import org.cb.zframe.auth.Role;
import org.cb.zframe.auth.User;
import org.cb.zframe.auth.service.UserManager;
import org.cb.zframe.dao.GenericDao;
import org.cb.zframe.service.impl.GenericManagerImpl;

/**
 * 用户Manager，与Spring Security集成
 * 
 * @author pesome
 * @date Nov 14, 2008
 */
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements
		UserDetailsService, UserManager {

	private final Logger log = LoggerFactory.getLogger(getClass());
	public PasswordEncoder passwordEncoder;
	public GenericDao<User, Long> userDao;

	public UserManagerImpl(GenericDao<User, Long> userDao) {
		super(userDao);
		this.userDao = userDao;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public User getUserByName(String name) {
		List<User> l = getUserListByName(name);
		User user=l.get(0);
		//TODO 碰到LazyInitial 问题，还未解决，先显示获取
		user.getResources();
		return user;
	}

	public List<User> getUserByRole(Role role) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("role", role);
		List<User> l = userDao.findByNamedQuery("user.findUserByRole",
				queryParams);
		return l;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		if (log.isDebugEnabled()) {
			log.debug(username + " login");
		}
		List<User> l = getUserListByName(username);

		if (l != null && l.size() > 0) {
			User user = l.get(0);
			Collection<Role> roles = user.getRoles();
			GrantedAuthority[] grantedAuthArray = new GrantedAuthority[roles
					.size()];
			int i = 0;
			for (Role role : roles) {
				grantedAuthArray[i++] = new GrantedAuthorityImpl(role.getName());
			}

			return new org.springframework.security.userdetails.User(username,
					user.getPassword(), true, true, true, true,
					grantedAuthArray);
		} else {
			throw new UsernameNotFoundException(username + " is not found");
		}

	}

	private List<User> getUserListByName(String username) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("name", username);
		List<User> l = userDao.findByNamedQuery("user.findUserByUserName",
				queryParams);
		return l;
	}

	public List<User> getUserByNameStart(String name) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("name", name+"%");
		List<User> l = userDao.findByNamedQuery("user.findUserByRealNameStart",
				queryParams);
		return l;
	}

	/**
	 * 保存用户并进行加密
	 * 
	 * @param user
	 * @param changePassword
	 *            是否更改密码
	 * @return
	 */
	public User saveUser(User user, boolean changePassword) {
		//新用户或更改密码时重新加密
		 if (user.getId() == 0 || changePassword) {
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
					null));
		}
		return userDao.save(user);
	}

	public List<User> getUserByDeptAndRole(Dept dept, Role role) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("dept", dept);
		queryParams.put("role", role);
		List<User> l = userDao.findByNamedQuery("user.findUserByDeptAndRole",
				queryParams);
		return l;
	}

	public List<User> getUserByRoleAndOrg(Role role, Org org) {
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("role", role);
		queryParams.put("org", org);		
		List<User> l = userDao.findByNamedQuery("user.findUserByRoleAndOrg",queryParams);
		return l;
	}

}
