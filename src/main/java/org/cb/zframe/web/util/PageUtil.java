package org.cb.zframe.web.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cb.zframe.dao.PaginatedListImpl;
import org.cb.zframe.dao.QueryObject;
import org.cb.zframe.service.GenericManager;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.ui.Model;


/**
 * 分页util for displaytag
 * 
 * @author pesome
 * @date Nov 13, 2008
 */
public class PageUtil {
	private GenericManager mgr = null;

	public PageUtil(GenericManager mgr) {
		this.mgr = mgr;
	}

	private void resetPageObjectFromRequest(PaginatedListImpl page,
			HttpServletRequest request) {
		String pageNum = request.getParameter("page");
		if (pageNum != null && pageNum.length() > 0) {
			page.setPageNumber(Integer.parseInt(pageNum));
		}

		String pageSize = request.getParameter("pageSize");
		if (pageSize != null && pageSize.length() > 0) {
			page.setObjectsPerPage(Integer.parseInt(pageSize));
		}
		String dir = request.getParameter("dir");
		if (dir != null && dir.length() > 0) {
			if ("asc".equals(dir))
				page.setSortDirection(SortOrderEnum.ASCENDING);
			else if ("desc".equals(dir))
				page.setSortDirection(SortOrderEnum.DESCENDING);
		}
		String sort = request.getParameter("sort");
		if (sort != null && sort.length() > 0) {
			page.setSortCriterion(sort);
		}
	}

	private void setModelWithPageObject(Model model, PaginatedListImpl page) {
		model.addAttribute("size", 0); // displaytag need it, but can set any
		model.addAttribute("pageSize", page.getObjectsPerPage());
		model.addAttribute("page", page.getPageNumber());
	}

	public void setPageObjectToModel(HttpServletRequest request, Model model)
			throws Exception {
		PaginatedListImpl page = new PaginatedListImpl();
		resetPageObjectFromRequest(page, request);
		model.addAttribute("pageObject", mgr.getPaginatedList(page)); // 所有分页对象都是用pageObject
		setModelWithPageObject(model, page);
	}

	public void setPageObjectToModel(HttpServletRequest request, Model model,
			List<QueryObject> l) throws Exception {
		PaginatedListImpl page = new PaginatedListImpl();
		page.setQueryList(l);
		resetPageObjectFromRequest(page, request);
		model.addAttribute("pageObject", mgr.getPaginatedList(page)); // 所有分页对象都是用pageObject
		setModelWithPageObject(model, page);
	}
	
	public void setPageObjectToModel(HttpServletRequest request, Model model,
			List<QueryObject> l,int pageSize) throws Exception {
		PaginatedListImpl page = new PaginatedListImpl();
		page.setObjectsPerPage(pageSize);
		page.setQueryList(l);
		resetPageObjectFromRequest(page, request);
		model.addAttribute("pageObject", mgr.getPaginatedList(page)); // 所有分页对象都是用pageObject
		setModelWithPageObject(model, page);
	}
}
