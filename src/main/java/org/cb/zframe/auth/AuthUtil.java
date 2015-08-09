package org.cb.zframe.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import org.cb.zframe.auth.service.UserManager;

/**
 * 获取当前登录对象的util类
 * @author pesome
 * @date 2009-2-10
 */
public class AuthUtil {
	private UserManager userManager;

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public Map<String, User> userMap = new HashMap<String, User>();

	private String getCurrentUserName() {
		final String user = ((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
		return user;
	}

	public User getCurrentUser() {
		String name = getCurrentUserName();
		// User user = userMap.get(name);
		// if (user == null) {
		User user = userManager.getUserByName(name);
		// userMap.put(name, user);
		// }
		return user;
	}

	public void refresh() {
		userMap.clear();
	}
}
