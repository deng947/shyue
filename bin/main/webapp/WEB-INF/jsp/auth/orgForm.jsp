<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>公司表单</title>
</head>
<body>
<h1>公司表单</h1>

<form:form modelAttribute="org">
<fieldset>
		<div class="commonForm"><label>编号：</label><font>${org.id}</font>
		<div class="commonForm"><label>公司名称：</label><form:input path="name" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>公司拼音：</label><form:input path="spell" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>orgId：</label><form:input path="orgId" size="30" maxlength="30"/></div>
<div class="formButton"><input type="submit" value="保存" /> 
<input onClick="history.back();" type="button" value="返回 " />
</div>
</fieldset>
</form:form>