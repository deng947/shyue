<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>部门表单</title>
</head>
<body>
<h1>部门表单</h1>

<form:form modelAttribute="dept">
<fieldset>
		<div class="commonForm"><label>部门编号：</label><font>${dept.id}</font>
		<div class="commonForm"><label>部门名称：</label><form:input path="name" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>部门拼音：</label><form:input path="spell" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>测试：</label><form:input path="test" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>公司：</label><form:select path="org" items="${orgList}" itemValue="id" itemLabel="spell"/></div>
<div class="formButton"><input type="submit" value="保存" /> 
<input onClick="history.back();" type="button" value="返回 " />
</div>
</fieldset>
</form:form>