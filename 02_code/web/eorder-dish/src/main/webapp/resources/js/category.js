function add() {
	$("[name='addForm']").attr("action", "add.action");
	$("[name='addForm']").attr("method", "post");
	$("[name='addForm']").submit();
}

function prePage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) - 1;
	$("#pageNow").val(pageNow);

	$("[name='pageForm']").attr("action", "category.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function nextPage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) + 1;
	$("#pageNow").val(pageNow);

	$("[name='pageForm']").attr("action", "category.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function load() {
	// 取得用户输入的页数
	var pageNow = $("#pageInput").val();
	// 如果没有输入，则不响应确认按钮事件
	if ('' != pageNow) {
		$("[name='pageForm']").attr("action", "category.action");
		$("[name='pageForm']").attr("method", "post");
		$("[name='pageForm']").submit();
	}
}

function save() {
	$("[name='saveForm']").attr("action", "save.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

function update() {
	$("[name='updateForm']").attr("action", "update.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function updateBack() {
	$("[name='updateForm']").attr("action", "category.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function saveBack() {
	$("[name='saveForm']").attr("action", "category.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

// 回车提交表单（登录）
$(function() {
	var categoryParent = $("#categoryParent").val();
	$("#categorySelect").each(function() {
		$(this).children("option").each(function() {
			if (categoryParent == $(this).val()) {
				$(this).attr("selected", true);
				return;
			}
		});
	});

	// 移到左边
	$('#categorySelect').change(function() {
		var categoryParent = $(this).children('option:selected').val();
		$("#categoryParent").val(categoryParent);
	});
});