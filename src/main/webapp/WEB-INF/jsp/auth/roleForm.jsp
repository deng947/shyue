<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" src="scripts/dtree.js"></script>
<h1>权限管理&nbsp;→&nbsp;角色管理</h1>

<form:form modelAttribute="role">
	<fieldset>
	<div class="leftForm"><label>角色名称：</label><form:input path="name"
		size="30" maxlength="30" /></div>
	<div class="rightForm"><label>角色描述：</label><form:input
		path="description" size="30" maxlength="30" /></div>
	<div class="leftForm"><label>资源项列表：</label>
	<p><a href="javascript: d.openAll();">全部展开</a> | <a
		href="javascript: d.closeAll();">全部收缩</a></p>
	<div class="dtree">
	<script language="javascript">
d = new dTree('d','.','role'); //这里需使用上面的form id
d.add('0','-1','资源');
${resourceTree}
document.write(d);
</script></div></div>

	<div class="formButton"><input type="submit" value="保存" /> <INPUT
		onClick="history.back();" type="button" value="返回 " /></div>
	</fieldset>
</form:form>
