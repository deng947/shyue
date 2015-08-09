<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp" %>
<head>
<script type="text/javascript">  
$(function() { 
	$("#orgId").jsonSelect("userOrgSelectJson.do", '${orgId}');
	$("#deptId").jsonSelect("userDeptSelectJson.do", '${deptId}');
	$("#roleId").jsonSelect("userRoleSelectJson.do", '${roleId}');
	
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
			$("#userForm").submit();
		}
	});
		});
</script>
<title>用户列表</title>
</head>
<body>
<h1>权限管理&nbsp;→&nbsp;用户管理</h1>

<form name="form" id="userForm" action="userList.do">
<fieldset>
<div class="leftForm"><label>公司名称：</label><select name="orgName"
	id="orgId"></select></div>
<div class="rightForm"><label>部门名称：</label><select name="deptName"
	id="deptId"></select></div>
<div class="leftForm"><label>角色名称：</label><select name="roleName"
	id="roleId"></select></div>
<div class="rightForm"><input name="query" type="submit" value="查询" style="cursor: hand"></div>
</fieldset>

<DIV id="displayTable"><display:table class="table"
	name="pageObject" requestURI="" id="userList" uid="items"
	export="false">
	<display:column style="width:5%;" headerClass="dataHead"
		title="<input type='checkbox' name='checkall' id='checkall' value='all'/>">
		<input type="checkbox" name="item" value="${items.id}" />
	</display:column>
	<display:column style="width:10%;" property="realName" sortable="true"
		title="姓名" />
	<display:column style="width:15%;" property="dept.name" sortable="true" title="公司">
	</display:column>
	<display:column style="width:15%;" property="dept.org.name" sortable="true" title="部门">
	</display:column>
	<display:column style="width:15%;" property="phone" sortable="true"
		title="电话" />
	<display:column style="width:30%;" property="email" sortable="true"
		title="Email" />
	<display:column style="width:10%;" title="编辑">
		<A href="userForm.do?id=${items.id}"> <IMG src="images/edit.gif"> </A>
	</display:column>
	<display:setProperty name="paging.banner.item_name" value="用户列表" />
	<display:setProperty name="paging.banner.items_name" value="用户列表" />
	<display:setProperty name="paging.banner.placement" value="bottom" />
</display:table></DIV>
<div class="pageForm">每页显示<select name="pageSize"
	onChange="pageForm.submit();" style="margin-bottom: 0">
	<option <c:if test="${pageSize==10}">selected</c:if>>10</option>
	<option <c:if test="${pageSize==20}">selected</c:if>>20</option>
	<option <c:if test="${pageSize==50}">selected</c:if>>50</option>
</select>条 <input type="text" name="page" value="${page}" size="1" maxlength="5" />页
<input type="submit" name="go" value="go" /></div>
<div id="displayButton">
<INPUT id="deleteBtn" type="button" value="删除"> <INPUT
	onClick="window.location.href='userForm.do'" type="button" value="新增">
&nbsp;</div>
</form>
</body>