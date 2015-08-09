<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/header.jsp"%>

<h2>${person.id}</h2>
<p>姓名：${person.name}
<p><a href="personForm.do">新增</a>&nbsp;<a
	href="personForm.do?id=${person.id}">修改</a>

<p><a href="personList.do">列表</a>
	