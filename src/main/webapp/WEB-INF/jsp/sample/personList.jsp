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
			$("#personForm").attr("action","deletePersonList.do");
			$("#personForm").submit();
		}
	});
});
</script>
<title>人员列表</title>
</head>
<body>
<h1>人员列表</h1>
<form name="personForm" id="personForm" action="personList.do">
<div id="displayTable"><display:table class="table"
	name="pageObject" requestURI="" id="personList" uid="items"
	export="false">
	<display:column style="width:5%;" headerClass="dataHead"
		title="<input type='checkbox' name='checkall' id='checkall' value='all'/>">
		<input type="checkbox" name="item" value="${items.id}" />
	</display:column>
			<display:column style="width:10%;" property="id" url="person.do?id=${items.id}" sortable="true" title="编号"/>
			<display:column style="width:10%;" property="name" sortable="true" title="姓名" />
			<display:column style="width:10%;" property="email" sortable="true" title="email" />
			<display:column style="width:10%;" property="address" sortable="true" title="address" />
			<display:column style="width:10%;" property="age" sortable="true" title="age" />
			<display:column style="width:10%;" property="checked" sortable="true" title="checked" />
	<display:column style="width:5%;" title="编辑">
		<a href="personForm.do?id=${items.id}"><img src="images/edit.gif"></a>
	</display:column>
	<display:setProperty name="paging.banner.placement" value="bottom" />
	</display:table>
</div>
<div class="pageForm">每页显示<select name="pageSize"
	onChange="personForm.submit();" style="margin-bottom: 0">
	<option <c:if test="${pageSize==10}">selected</c:if>>10</option>
	<option <c:if test="${pageSize==20}">selected</c:if>>20</option>
	<option <c:if test="${pageSize==50}">selected</c:if>>50</option>
</select>条  <input type="text" name="page" value="${page}" size="1" maxlength="5" />页
<input type="submit" name="go" value="go" /></div>
<div id="displayButton">
<input id="deleteBtn" type="button" value="删除"/> 
<input onClick="window.location.href='personForm.do'" type="button" value="新增"/>
</div>
</form>
</body>