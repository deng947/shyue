<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>电脑表单</title>
</head>
<body>
<h1>电脑表单</h1>

<form:form modelAttribute="computer">
<fieldset>
		<div class="commonForm"><label>编号：</label><font>${computer.id}</font>
		<div class="commonForm"><label>名称：</label><form:input path="name" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>人员：</label><form:select path="person" items="${personList}" itemValue="id" itemLabel="name"/></div>
<div class="formButton"><input type="submit" name="save" value="保存" /> 
<c:if test="${computer.id>0}"><input type="submit" name="delete" value="删除" /> </c:if>
<input onClick="history.back();" type="button" value="返回 " />
</div>
</fieldset>
</form:form>