package org.cb.zframe.auth.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.cb.zframe.auth.User;
import org.cb.zframe.dao.QueryObject;
import org.cb.zframe.service.GenericManager;
import org.cb.zframe.web.util.PageUtil;

/**
 * Handle View (ViewObject ViewList QueryList BatchDelete)<br>
 * Freemarker AutoGenerated
 * 
 * @date 2009-02-17 17:30:32 
 * 
 */
@Controller
public class UserController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("userManager")
	private GenericManager<User, Long> userManager;

	public void setUserManager(GenericManager<User, Long> userManager) {
		this.userManager = userManager;
	}
	
	/**
	 * ViewObjectById
	 * 
	 * @param id
	 * @param model
	 */
	@RequestMapping("/user.do")
	public String viewUser(@RequestParam("id") Long id, Model model) {
		User user = userManager.get(id);
		model.addAttribute(user);
		return "auth/user"; 
	}
	
	/**
	 * ListView 
	 * 
	 * @param request 
	 * @param model 
	 */
	@RequestMapping("/userList.do")
	public String viewUserList(HttpServletRequest request, Model model)
			throws Exception {	
		PageUtil page = new PageUtil(userManager);
		//String queryName = (String) request.getParameter("queryName");
		List<QueryObject> l = new ArrayList<QueryObject>();
			
		/* TODO add your Query here*/
		/*if (queryName != null && queryName.length() != 0) {
			QueryObject q = new QueryObject("description", QueryObject.LIKE,
					queryName + "%", QueryObject.STR);
			l.add(q);
		}*/
		page.setPageObjectToModel(request, model, l);
		return "auth/userList";
	}
	
	/**
	 * Batch delete items
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUserList.do")
	public String deleteUserList(HttpServletRequest request, Model model)
			throws Exception {
		String[] items = request.getParameterValues("item");
		if (items != null && items.length > 0) {
			Long[] ids = new Long[items.length];
			for (int i = 0; i < items.length; i++) {
				ids[i] = Long.valueOf(items[i]);
			}
			userManager.delete(ids, false);		
		}
		//to viewList
		return viewUserList(request, model);
	}	
}