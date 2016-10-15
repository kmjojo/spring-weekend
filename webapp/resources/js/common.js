/**
 * 
 */
function callAjax(ajaxObj) {
	$.ajax(ajaxObj).fail(function(jqXHR) {

		if (jqXHR.status == 1500) {

			var error = JSON.parse(jqXHR.responseText);
			alert(error.errorMsg);
		} else {
			alert("오류~~");
		}

	});
}