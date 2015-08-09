/**
 * Select filled By Json(jquery plugin)
 * by pesome
 */

jQuery.fn.jsonSelect = function(url, value) {
	var id = this.attr("id");
	$.getJSON(url, {}, function(json) {
		$.each(json.data, function(i) {
			// this.append()不行
				if (value == json.data[i].value) {
					$("<option value=" + json.data[i].value + " selected>"
									+ json.data[i].name + "</option>")
							.appendTo("#" + id);
				} else {
					$("<option value=" + json.data[i].value + ">"
							+ json.data[i].name + "</option>")
					.appendTo("#" + id);
				}
			});
	});
	return this;
}
