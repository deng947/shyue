package org.cb.zframe.auth.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.cb.zframe.auth.Role;
import org.cb.zframe.auth.User;
import org.cb.zframe.auth.service.RoleManager;
import org.cb.zframe.auth.service.UserManager;

/**
 * 增加/删除/修改的permissionForm
 * 
 * @author cb 2008.11.22
 * 
 */
@Controller
@RequestMapping("/permissionForm.do")
public class PermissionForm {

	@Autowired
	private UserManager umgr = null;

	public void setUmgr(UserManager umgr) {
		this.umgr = umgr;
	}

	@Autowired
	private RoleManager rolemgr = null;

	public void setRoleManager(RoleManager rolemgr) {
		this.rolemgr = rolemgr;
	}

	/**
	 * 进入 新增/修改 的页面
	 * 
	 * @param id
	 * @param model
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForms(
			@RequestParam(value = "id", required = false) Long id,
			ModelMap model) {
		User user = umgr.get(id);
		List<Role> roleList = rolemgr.getAll();
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		return "auth/permissionForm";

	}

	/**
	 * 点击 修改按钮后提交处理方法
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("user") User user,
			HttpServletRequest request) {
		user=umgr.get(user.getId());
		String[] roleListIdsChecked = request.getParameterValues("roleListIds");
		if (roleListIdsChecked == null) {
			user.setRoles(null);
		} else {
			int length = roleListIdsChecked.length;
			Collection<Role> roleCollection = new ArrayList<Role>();
			for (int i = 0; i < length; i++) {
				Role relatedRole = rolemgr.get(new Long(roleListIdsChecked[i]));
				roleCollection.add(relatedRole);
			}
			user.setRoles(roleCollection);
		}
		umgr.save(user);
		return "redirect:permissionList.do";
	}
}
