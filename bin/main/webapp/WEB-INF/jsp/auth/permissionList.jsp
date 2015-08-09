<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<title>赋权列表</title>
</head>
<body>
<h1>权限管理&nbsp;→&nbsp;赋权管理 </h1>

<form name="pageForm" action="permissionList.do">
<fieldset> 
<div class="leftForm"> 
 <label>用户名称：</label><input name="queryName" type="text" size="15"
			maxlength="15" value="${queryName}">
</div>
<div class="rightForm">
<input name="query" type="submit" class="oabutton" value="查询" style="cursor:hand">
</div>
</fieldset>

<DIV id="displayTable"><display:table class="table"
	name="pageObject" requestURI="" id="permissionList" uid="items"
	export="false">
	<display:column style="width:10%;" property="id" sortable="true"
		title="序号" />
<display:column style="width:10%;" property="realName" sortable="true"
		title="姓名" />
	<display:column style="width:15%;" sortable="true" title="公司">
		${items.dept.name}
	</display:column>
	<display:column style="width:15%;" sortable="true" title="部门">
		${items.dept.org.name}
	</display:column>
	<display:column style="width:10%;" property="phone"
		sortable="true" title="电话" />
<display:column style="width:30%;" property="email"
		sortable="true" title="Email" />
	<display:column style="width:10%;" title="赋权">
		<A href="permissionForm.do?id=${items.id}"> <IMG
			src="images/edit.gif" border=0> </A>
	</display:column>
	<display:setProperty name="paging.banner.item_name" value="资源列表" />
	<display:setProperty name="paging.banner.items_name" value="资源列表" />
	<display:setProperty name="paging.banner.placement" value="bottom" />
</display:table> <input type="hidden" name="delCells" value="" /></DIV>
<div class="pageForm">每页显示<select name="pageSize"
	onChange="pageForm.submit();" style="margin-bottom: 0">
	<option <c:if test="${pageSize==10}">selected</c:if>>10</option>
	<option <c:if test="${pageSize==20}">selected</c:if>>20</option>
	<option <c:if test="${pageSize==50}">selected</c:if>>50</option>
</select>条 <input type="text" name="page" value="${page}" size="1" maxlength="5" />页
<input type="submit" name="go" value="go" />
</div>
</form>
</body>
