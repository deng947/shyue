<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>人员表单</title>
</head>
<body>
<h1>人员表单</h1>

<form:form modelAttribute="person">
<fieldset>
		<div class="commonForm"><label>编号：</label><font>${person.id}</font>
		<div class="commonForm"><label>姓名：</label><form:input path="name" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>email：</label><form:input path="email" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>address：</label><form:input path="address" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>age：</label><form:input path="age" size="30" maxlength="30"/></div>
		<div class="commonForm"><label>checked：</label><form:checkbox path="checked"/></div>
<div class="formButton"><input type="submit" name="save" value="保存" /> 
<c:if test="${person.id>0}"><input type="submit" name="delete" value="删除" /> </c:if>
<input onClick="history.back();" type="button" value="返回 " />
</div>
</fieldset>
</form:form>