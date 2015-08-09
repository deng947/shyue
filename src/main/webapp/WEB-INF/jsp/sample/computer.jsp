<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>电脑查看</title>
</head>
<body>
<h1>电脑查看</h1>
<fieldset>
	<div class="commonForm"><label>编号：</label><font>${computer.id}</font></div>	
	<div class="commonForm"><label>名称：</label><font>${computer.name}</font></div>	
	<div class="commonForm"><label>人员：</label><font>${computer.person.name}</font></div>
<fieldset>
<div class="formButton"><a href=computerForm.do?id=${computer.id}>修改</a>
<a href="javascript:history.back();">返回列表 </a>
</div>
