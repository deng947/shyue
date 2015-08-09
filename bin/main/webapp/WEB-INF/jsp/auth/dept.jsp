<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>部门查看</title>
</head>
<body>
<h1>部门查看</h1>
<fieldset>
	<div class="commonForm"><label>部门编号：</label><font>${dept.id}</font></div>
	<div class="commonForm"><label>部门名称：</label><font>${dept.name}</font></div>
	<div class="commonForm"><label>部门拼音：</label><font>${dept.spell}</font></div>
	<div class="commonForm"><label>测试：</label><font>${dept.test}</font></div>
	<div class="commonForm"><label>公司：</label><font>${dept.org.spell}</font></div>
<fieldset>
<div class="formButton"><a href=deptForm.do?id=${dept.id}>修改</a>
<a href="javascript:history.back();">返回列表 </a>
</div>
