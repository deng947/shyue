<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>人员查看</title>
</head>
<body>
<h1>人员查看</h1>
<fieldset>
	<div class="commonForm"><label>编号：</label><font>${person.id}</font></div>	
	<div class="commonForm"><label>姓名：</label><font>${person.name}</font></div>	
	<div class="commonForm"><label>email：</label><font>${person.email}</font></div>	
	<div class="commonForm"><label>address：</label><font>${person.address}</font></div>	
	<div class="commonForm"><label>age：</label><font>${person.age}</font></div>	
	<div class="commonForm"><label>checked：</label><font>${person.checked}</font></div>	
<fieldset>
<div class="formButton"><a href=personForm.do?id=${person.id}>修改</a>
<a href="javascript:history.back();">返回列表 </a>
</div>
