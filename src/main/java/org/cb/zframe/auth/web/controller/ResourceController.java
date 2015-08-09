package org.cb.zframe.auth.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cb.zframe.auth.Resource;
import org.cb.zframe.dao.QueryObject;
import org.cb.zframe.service.GenericManager;
import org.cb.zframe.web.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 资源入口请求处理部分
 * 
 * @author cb
 * @date Nov 21, 2008
 */
@Controller
public class ResourceController {
	private final Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("resourceManager")
	private GenericManager<Resource, Long> resourceManager;

	public void setresourceManager(
			GenericManager<Resource, Long> resourceManager) {
		this.resourceManager = resourceManager;
	}

	/**
	 * 资源
	 * 
	 * @param model
	 *            ，request 通过获取request中的参数（deleteItems）来区分是入口请求还是删除请求
	 * @throws Exception
	 */
	@RequestMapping("/resourceList.do")
	public String viewresourceList(HttpServletRequest request, Model model)
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
					resourceManager.delete(deleteitemid);
				}
			}
			return "redirect:resourceList.do";
		} else {
			PageUtil page = new PageUtil(resourceManager);
			String queryName = (String) request.getParameter("queryName");
			List<QueryObject> l = new ArrayList<QueryObject>();
			if (queryName != null && queryName.length() != 0) {
				QueryObject q = new QueryObject("description", QueryObject.LIKE,
						queryName + "%", QueryObject.STR);
				l.add(q);
			}
			page.setPageObjectToModel(request, model, l);
			return "auth/resourceList";
		}
	}
}
