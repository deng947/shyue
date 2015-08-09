package org.cb.zframe.dao;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 * Displaytag数据库分页使用的对象<br>
 * 默认显示第一页，每页显示10条，按Id倒排�?br>
 * 
 * @author pesome
 * @date Nov 13, 2008
 * @param <T>
 */
public class PaginatedListImpl implements PaginatedList {
	private List list;
	private int pageNumber = 1;
	private int objectsPerPage = 10;
	private int fullListSize = 0;
	private String sortCriterion = "id";
	private SortOrderEnum sortDirection = SortOrderEnum.DESCENDING;
	private String searchId;
	private List<QueryObject> queryList;

	public List<QueryObject> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<QueryObject> queryList) {
		this.queryList = queryList;
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public int getFullListSize() {
		return fullListSize;
	}

	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

}
