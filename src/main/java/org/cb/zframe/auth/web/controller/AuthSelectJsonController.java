package org.cb.zframe.auth.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.cb.zframe.auth.Dept;
import org.cb.zframe.auth.Org;
import org.cb.zframe.auth.Role;
import org.cb.zframe.auth.service.DeptManager;
import org.cb.zframe.service.GenericManager;

/**
 * 
 * @author pesome
 * @date 2009-2-10
 */
@Controller
public class AuthSelectJsonController {
	@Autowired
	@Qualifier("deptManager")
	private DeptManager deptMgr = null;

	@Autowired
	@Qualifier("orgManager")
	private GenericManager<Org, Long> orgManager = null;

	@Autowired
	@Qualifier("roleManager")
	private GenericManager<Role, Long> roleMgr = null;

	public void setDeptMgr(DeptManager deptMgr) {
		this.deptMgr = deptMgr;
	}

	public void setOrgManager(GenericManager<Org, Long> orgManager) {
		this.orgManager = orgManager;
	}

	public void setRoleMgr(GenericManager<Role, Long> roleMgr) {
		this.roleMgr = roleMgr;
	}

	@RequestMapping("/userOrgSelectJson.do")
	public String userOrgSelectJson(HttpServletResponse response)
			throws IOException {
		List<Org> l = this.orgManager.getAll();
		StringBuffer sb = new StringBuffer();
		sb.append("{data:[");
		int i = 0;
		for (Org c : l) {
			if (i > 0) {
				sb.append(",");
			}

			sb.append("{value:'").append(c.getId()).append("',name:'").append(
					c.getName()).append("'}");
			i++;
		}
		sb.append("]}");
		setResponseTypeAndHeader(response);
		response.getWriter().write(sb.toString());
		return null;
	}

	@RequestMapping("/userDeptSelectJson.do")
	public String userDeptSelectJson(HttpServletResponse response)
			throws IOException {
		List<Dept> l = this.deptMgr.getAll();
		StringBuffer sb = new StringBuffer();
		sb.append("{data:[");
		int i = 0;
		for (Dept c : l) {
			if (i > 0) {
				sb.append(",");
			}

			sb.append("{value:'").append(c.getId()).append("',name:'").append(
					c.getName()).append("'}");
			i++;
		}
		sb.append("]}");
		setResponseTypeAndHeader(response);
		response.getWriter().write(sb.toString());
		return null;
	}

	@RequestMapping("/userRoleSelectJson.do")
	public String userRoleSelectJson(HttpServletResponse response)
			throws IOException {
		List<Role> l = this.roleMgr.getAll();
		StringBuffer sb = new StringBuffer();
		sb.append("{data:[");
		int i = 0;
		for (Role c : l) {
			if (i > 0) {
				sb.append(",");
			}

			sb.append("{value:'").append(c.getId()).append("',name:'").append(
					c.getName()).append("'}");
			i++;
		}
		sb.append("]}");
		setResponseTypeAndHeader(response);
		response.getWriter().write(sb.toString());
		return null;
	}

	/**
	 * @param id
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/getDeptByOrg.do")
	public String contractCategorySelectJson(
			@RequestParam(value = "id") Long id, HttpServletResponse response)
			throws IOException {
		// 根据ID取得ORG
		if (id > 0) {
			Org orgQuery = orgManager.get(id);
			// 根据Org取得DeptList
			List<Dept> deptList = deptMgr.getDeptListByOrg(orgQuery);
			StringBuffer sb = new StringBuffer();
			sb.append("{data:[");
			int i = 0;
			for (Dept dept : deptList) {
				if (i > 0) {
					sb.append(",");
				}

				sb.append("{value:'").append(dept.getId()).append("',name:'")
						.append(dept.getName()).append("'}");
				i++;
			}
			sb.append("]}");
			setResponseTypeAndHeader(response);
			response.getWriter().write(sb.toString());
		}
		return null;
	}

	private void setResponseTypeAndHeader(HttpServletResponse response) {
		response.setContentType("text/xml; charset=UTF-8");
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control",
				"no-store, max-age=0, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers.
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
	}
}
