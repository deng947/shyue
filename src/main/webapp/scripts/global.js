/*
 * 常用JS
 * pesome
 */

function $(id) {
	return document.getElementById(id);
}

function confirmDelete(obj) {   
    var msg = "你确定要删除" + obj + "吗?";
    ans = confirm(msg);
    if (ans) {
        return true;
    } else {
        return false;
    }
}