function prePage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) - 1;
	$("#pageNow").val(pageNow);
	var cashierId = $("#cashierId").val();
	$("#cashierId").val(cashierId);

	$("[name='pageForm']").attr("action", "list.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function nextPage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) + 1;
	$("#pageNow").val(pageNow);
	var cashierId = $("#cashierId").val();
	$("#cashierId").val(cashierId);

	$("[name='pageForm']").attr("action", "list.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

// 查询
function query() {
	$("[name='queryForm']").attr("action", "list.action");
	$("[name='queryForm']").attr("method", "post");
	$("[name='queryForm']").submit();
}

// 重置查询条件
function resetQuery() {
	$("#order\\.orderSeq").val("");
	$("#order\\.cellphone").val("");
	$("#order\\.cashierId").val("");
	$("#order\\.serventId").val("");
	$("#order\\.createAtMin").val("");
	$("#order\\.createAtMax").val("");
	$("#order\\.totalPriceMin").val("");
	$("#order\\.totalPriceMax").val("");
	$("#order\\.status").val("");
}

$(function() {
	var cashierId = $("#order\\.cashierId").val();
	$("#cashierSelect").each(function() {
		$(this).children("option").each(function() {
			if (cashierId == $(this).val()) {
				$(this).attr("selected", true);
				return;
			}
		});
	});

	// 下拉选单值变事件
	$('#cashierSelect').change(function() {
		var cashierId = $(this).children('option:selected').val();
		$("#order\\.cashierId").val(cashierId);
	});

	var serventId = $("#order\\.serventId").val();
	$("#serventSelect").each(function() {
		$(this).children("option").each(function() {
			if (serventId == $(this).val()) {
				$(this).attr("selected", true);
				return;
			}
		});
	});

	// 下拉选单值变事件
	$('#serventSelect').change(function() {
		var serventId = $(this).children('option:selected').val();
		$("#order\\.serventId").val(serventId);
	});

	var status = $("#order\\.status").val();
	$("#statusSelect").each(function() {
		$(this).children("option").each(function() {
			if (status == $(this).val()) {
				$(this).attr("selected", true);
				return;
			}
		});
	});

	// 下拉选单值变事件
	$('#statusSelect').change(function() {
		var status = $(this).children('option:selected').val();
		$("#order\\.status").val(status);
	});

});