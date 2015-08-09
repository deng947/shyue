<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>公司查看</title>
</head>
<body>
<h1>公司查看</h1>
<fieldset>
	<div class="commonForm"><label>编号：</label><font>${org.id}</font></div>
	<div class="commonForm"><label>公司名称：</label><font>${org.name}</font></div>
	<div class="commonForm"><label>公司拼音：</label><font>${org.spell}</font></div>
	<div class="commonForm"><label>orgId：</label><font>${org.orgId}</font></div>
<fieldset>
<div class="formButton"><a href=orgForm.do?id=${org.id}>修改</a>
<a href="javascript:history.back();">返回列表 </a>
</div>
