<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<script type="text/javascript">  
<!--  
$(function() { 
	$("#orgId").jsonSelect("userOrgSelectJson.do", ${orgId});
	$("#deptId").jsonSelect("userDeptSelectJson.do", ${deptId});
});
-->
</script>
</head>
<h1>权限管理&nbsp;→&nbsp;用户维护
</h1>
<form:form modelAttribute="user">
	<fieldset>
	<div class="leftForm"><label>登录名称：</label><form:input path="name" /></div>
	<div class="rightForm"><label>密码：</label><form:input
		path="password" /></div>
	<div class="leftForm"><label>用户姓名：</label><form:input
		path="realName" /></div>
	<div class="rightForm"><label>职位：</label><form:input
		path="position" /></div>
	<div class="leftForm"><label>电话号码：</label><form:input
		path="phone" /></div>
	<div class="rightForm"><label>Email：</label><form:input
		path="email" /></div>
	<div class="leftForm"><label>公司名称：</label><select name="orgName"
		id="orgId"></select></div>
	<div class="rightForm"><label>部门名称：</label><select
		name="deptName" id="deptId"></select></div>
	<div class="formButton"><input type="submit" value="保存" /> <INPUT
		onClick="history.back();" type="button" value="返回 " /></div>
	</fieldset>
</form:form>