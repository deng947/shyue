package org.cb.zframe.auth.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.cb.zframe.auth.service.UserManager;
import org.cb.zframe.dao.QueryObject;
import org.cb.zframe.web.util.PageUtil;

/**
 * 赋权列表 入口请求处理部分
 * 
 * @author pesome
 * @date 12 22, 2008
 */
@Controller
public class PermissionController {
	@Autowired
	private UserManager mgr = null;

	public void setMgr(UserManager mgr) {
		this.mgr = mgr;
	}

	/**
	 * 赋权列表
	 * 
	 * @param model
	 *            ，request 通过获取request中的参数（deleteItems）来区分是入口请求还是删除请求
	 * @throws Exception
	 */
	@RequestMapping("/permissionList.do")
	public String viewPermissionList(HttpServletRequest request, Model model)
			throws Exception {
		PageUtil page = new PageUtil(mgr);
		String queryName = (String) request.getParameter("queryName");
		List<QueryObject> l = new ArrayList<QueryObject>();
		if (queryName != null && queryName.length() != 0) {
			QueryObject q = new QueryObject("realName", QueryObject.LIKE,
					queryName + "%", QueryObject.STR);
			l.add(q);
		}
		page.setPageObjectToModel(request, model, l);
		return "auth/permissionList";
	}

}
