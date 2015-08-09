<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<script type="text/javascript">  
$(function() { 
	$("#checkall").click( function() {
		if($("#checkall").attr("checked")==undefined){
			$("[name='item']").attr("checked", false);
		}else{
			$("[name='item']").attr("checked", true);
		}			
		});

	$("#deleteBtn").click( function() {
		if (!confirm("确定要删除吗?")) {
			return false;
		}else{
			$("#deptForm").attr("action","deleteDeptList.do");
			$("#deptForm").submit();
		}
	});
});
</script>
<title>部门列表</title>
</head>
<body>
<h1>部门列表</h1>
<form name="deptForm" id="deptForm" action="deptList.do">
<div id="displayTable"><display:table class="table"
	name="pageObject" requestURI="" id="deptList" uid="items"
	export="false">
	<display:column style="width:5%;" headerClass="dataHead"
		title="<input type='checkbox' name='checkall' id='checkall' value='all'/>">
		<input type="checkbox" name="item" value="${items.id}" />
	</display:column>
			<display:column style="width:10%;" property="id" url="dept.do?id=${items.id}" sortable="true" title="部门编号"/>
			<display:column style="width:10%;" property="name" sortable="true" title="部门名称" />
			<display:column style="width:10%;" property="spell" sortable="true" title="部门拼音" />
			<display:column style="width:10%;" property="test" sortable="true" title="测试" />
			<display:column style="width:10%;" property="org.spell" sortable="true" title="公司" />
	<display:column style="width:5%;" title="编辑">
		<a href="deptForm.do?id=${items.id}"><img src="images/edit.gif"></a>
	</display:column>
	<display:setProperty name="paging.banner.placement" value="bottom" />
	</display:table>
</div>
<div class="pageForm">每页显示<select name="pageSize"
	onChange="deptForm.submit();" style="margin-bottom: 0">
	<option <c:if test="${pageSize==10}">selected</c:if>>10</option>
	<option <c:if test="${pageSize==20}">selected</c:if>>20</option>
	<option <c:if test="${pageSize==50}">selected</c:if>>50</option>
</select>条  <input type="text" name="page" value="${page}" size="1" maxlength="5" />页
<input type="submit" name="go" value="go" /></div>
<div id="displayButton">
<input id="deleteBtn" type="button" value="删除"/> 
<input onClick="window.location.href='deptForm.do'" type="button" value="新增"/>
</div>
</form>
</body>