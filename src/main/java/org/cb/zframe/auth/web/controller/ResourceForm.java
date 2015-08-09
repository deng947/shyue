package org.cb.zframe.auth.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cb.zframe.auth.Resource;
import org.cb.zframe.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 增加/删除/修改的resourceController
 * 
 * @author cb 2008.11.21
 * 
 */
@Controller
@RequestMapping("/resourceForm.do")
public class ResourceForm {
	private final Log log = LogFactory.getLog(getClass());

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
	public String setupForm(
			@RequestParam(value = "id", required = false) Long id,
			ModelMap model) {
		Resource resource;
		if (id == null || id == 0L) {
			resource = new Resource();
		} else {
			resource = resourceManager.get(id);
		}
		model.addAttribute(resource);
		return "auth/resourceForm";

	}

	/**
	 * 点击 新增/修改/删除 按钮后提交处理方法
	 * 
	 * @param resource
	 * @param delete
	 *            delete按钮值
	 * @param result
	 * @param status
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("resource") Resource resource,
			@RequestParam(value = "delete", required = false) String delete,
			HttpServletRequest request) {
		if (delete != null) {
			resourceManager.delete(resource.getId());
			return "redirect:resourceList.do";
		} else {
			resource = resourceManager.save(resource);
			return "redirect:resourceList.do";
		}
	}
}
