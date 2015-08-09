package org.cb.zframe.auth.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cb.zframe.auth.service.RoleManager;
import org.cb.zframe.dao.QueryObject;
import org.cb.zframe.web.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 角色管理入口请求处理部分
 * 
 * @author cb
 * @date 12- 21-2008
 */
@Controller
public class RoleController {
	private final Log log = LogFactory.getLog(getClass());
	@Autowired
	private RoleManager mgr = null;

	public void setMgr(RoleManager mgr) {
		this.mgr = mgr;
	}

	/**
	 * 角色列表
	 * 
	 * @param model
	 *            ，request 通过获取request中的参数（deleteItems）来区分是入口请求还是删除请求
	 * @throws Exception
	 */
	@RequestMapping("/roleList.do")
	public String viewroleList(HttpServletRequest request, Model model)
			throws Exception {
		// 处理参数部分 是否删除
		String deleteid = (String) request.getParameter("deleteItems");
		if (deleteid != null && deleteid.length() != 0)
		// 执行删除
		{
			if (deleteid.indexOf(";") > 0) {
				String deleteID[] = deleteid.trim().split(";");
				for (int i = 0; i < deleteID.length; i++) {
					long deleteitemid = Long.parseLong(deleteID[i]);
					mgr.delete(deleteitemid);
				}
			}
			return "redirect:roleList.do";
		} else {
			PageUtil page = new PageUtil(mgr);
			String queryName = (String) request.getParameter("queryName");
			List<QueryObject> l = new ArrayList<QueryObject>();
			if (queryName != null && queryName.length() != 0) {
				QueryObject q = new QueryObject("name", QueryObject.LIKE, "%"
						+ queryName.toUpperCase() + "%", QueryObject.STR);
				l.add(q);
			}
			page.setPageObjectToModel(request, model, l);
			return "auth/roleList";
		}
	}

}
