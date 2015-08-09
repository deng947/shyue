package org.cb.zframe.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.cb.zframe.auth.AuthUtil;
import org.cb.zframe.auth.Resource;
import org.cb.zframe.auth.User;
import org.cb.zframe.auth.service.InitManager;

/**
 * index 左边的controller
 * 
 * @author pesome
 * @date Nov 8, 2008
 */
@Controller
public class IndexController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuthUtil authUtil;

	@Autowired
	private InitManager initManager;

	public void setAuthUtil(AuthUtil authUtil) {
		this.authUtil = authUtil;
	}

	public void setInitManager(InitManager initManager) {
		this.initManager = initManager;
	}

	/**
	 * 首页，取登录用户的权限，并设置到model中
	 * 
	 * @param id
	 * @param model
	 */
	@RequestMapping("/index.do")
	public void viewIndex(Model model) {
		User user = authUtil.getCurrentUser();
		for (Resource resource : user.getResources()) {
			model.addAttribute(resource.getCode(), true);
		}
	}
	
	@RequestMapping("/leftMenu.do")
	public void viewLeftMenu(Model model) {
		User user = authUtil.getCurrentUser();
		for (Resource resource : user.getResources()) {
			model.addAttribute(resource.getCode(), true);
		}
	}

	@RequestMapping("/refreshUser.do")
	public void refreshUser() {
		authUtil.refresh();
	}

	@RequestMapping("/initAuth.do")
	public void initAuth() {
		try {
			initManager.initAuth("Auth.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
