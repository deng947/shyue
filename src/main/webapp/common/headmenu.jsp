<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css" media="screen">
ul#navlist
{
padding: 0;
margin: 0;
list-style-type: none;
float: left;
width: 100%;
color: #fff;
background-color: #036;
}

ul#navlist li { display: inline; }

ul#navlist li a
{
float: left;
width: 5em;
color: #fff;
background-color: #036;
padding: 0.2em 1em;
text-decoration: none;
border-right: 1px solid #fff;
}

ul#navlist li a:hover
{
background-color: #369;
color: #fff;
}
</style>

<ul id="navlist">
<li><a href="index.do">首页</a></li>
<li><a href="#">权限管理</a></li>
<li><a href="#">个人工作台</a></li>
</ul>