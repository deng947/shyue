package org.cb.zframe.auth.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.cb.zframe.auth.Resource;
import org.cb.zframe.auth.Role;
import org.cb.zframe.auth.service.RoleManager;
import org.cb.zframe.service.GenericManager;

/**
 * 增加/删除/修改的roleController
 * 
 * @author cb 2008.11.22
 * 
 */
@Controller
@RequestMapping("/roleForm.do")
public class RoleForm {

	@Autowired
	private RoleManager mgr = null;

	public void setRoleManager(RoleManager mgr) {
		this.mgr = mgr;
	}

	@Autowired
	@Qualifier("resourceManager")
	private GenericManager<Resource, Long> resourceManager;

	public void setResourceManager(
			GenericManager<Resource, Long> resourceManager) {
		this.resourceManager = resourceManager;
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
		Role role;
		if (id == null || id == 0L) {
			role = new Role();
		} else {
			role = mgr.get(id);
		}

		StringBuffer sb = new StringBuffer();
		List<Resource> resourceList = resourceManager.getAll();
		for (Resource f : resourceList) {
			long pid = 0;
			if (f.getParent() != null) {
				pid = f.getParent().getId();
			}
			sb.append("d.add(").append(f.getId()).append(",").append(pid)
					.append(",'").append(f.getDescription()).append("'");
			if (role.getResources() != null && role.getResources().contains(f)) {
				sb.append(",true");
			}
			sb.append(");\n");
		}

		model.addAttribute("role", role);
		model.addAttribute("resourceTree", sb.toString());
		model.addAttribute(role);
		return "auth/roleForm";

	}

	/**
	 * 点击 新增/修改/删除 按钮后提交处理方法
	 * 
	 * @param role
	 * @param delete
	 *            delete按钮值
	 * @param result
	 * @param status
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("role") Role role,
			@RequestParam(value = "delete", required = false) String delete,
			HttpServletRequest request) {
		if (delete != null) {
			mgr.delete(role.getId());
			return "redirect:roleList.do";
		} else {
			// 这里必须是dtree.js中的checkbox name "checkId"
			String[] resourceIdsChecked = request.getParameterValues("checkId");
			if (resourceIdsChecked == null) {
				role.setResources(null);
			} else {
				int length = resourceIdsChecked.length;
				Collection<Resource> resourceCollection = new ArrayList<Resource>();
				for (int i = 0; i < length; i++) {
					Resource relatedResource = resourceManager.get(new Long(
							resourceIdsChecked[i]));
					resourceCollection.add(relatedResource);
				}
				role.setResources(resourceCollection);
			}
			role = mgr.save(role);
			return "redirect:roleList.do";
		}
	}
}
