<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="scripts/jquery.pstrength-min.1.2.js">
<script type="text/javascript">
$(function() {
$('.password').pstrength(6);
});
</script>
<table width=100% cellpadding="1" cellspacing="2">
	<tr>
		<td width="81%" align=left><IMG src="images/Forum_nav.gif">&nbsp;用户自助</td>
		<td width="19%" align=right></td>
	</tr>
	<tr>
		<td colspan="2" height="1" background="images/i_line_1.gif"></td>
	</tr>
</table>
<form action="userServiceSave.do">
<input type="hidden" name="userId" id="userId"
		value="${cUser.id}" />
	<table class="DoubleColorTable">
        <tr>
			<td width="10%">电话号码</td>
			<td width="40%"><input type="text" value="${cUser.phone}"  name="phone"/></td>
			<td width="10%">手机号码</td>
			<td width="40%"><input type="text" value="${cUser.mobile}" name="mobile"/></td>
		</tr>
		<tr>
			<td width="10%">电话号码</td>
			<td width="40%"><input type="text" value="${cUser.phone}"  name="phone"/></td>
			<td width="10%">Email</td>
			<td width="40%"><input type="text" value="${cUser.email}" name="email"/></td>
		</tr>
		<tr>
			<td width="10%">新密码</td>
			<td width="40%"><input type="password" class="password"  value="" name="mPassword"/></td>
			<td width="10%">重复密码</td>
			<td width="40%"><input type="password" class="password"  value="" name="rePassword"/></td>
		</tr>
	</table>
	<div>
	<TABLE width=100% border="0" cellspacing="0" cellpadding="5"
		align="center">
		<TBODY>
			<TR>
				<TD width=75% align="right">
				<p class="submit"><input class="oabutton" type="submit"
					value="保存" /> <INPUT class="oabutton" onClick="history.back();"
					type="button" value="返回 " /></p>
				&nbsp; &nbsp;</TD>
				<TD height="30" width=25% align="left"></TD>
			</TR>
		</TBODY>
	</TABLE>
	</div>
</form>