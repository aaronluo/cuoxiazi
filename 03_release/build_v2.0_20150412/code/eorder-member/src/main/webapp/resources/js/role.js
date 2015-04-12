function add() {
	$("[name='addForm']").attr("action", "add.action");
	$("[name='addForm']").attr("method", "post");
	$("[name='addForm']").submit();
}

function prePage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) - 1;
	$("#pageNow").val(pageNow);

	$("[name='pageForm']").attr("action", "role.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function nextPage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) + 1;
	$("#pageNow").val(pageNow);

	$("[name='pageForm']").attr("action", "role.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function load() {
	// 取得用户输入的页数
	var pageNow = $("#pageInput").val();
	// 如果没有输入，则不响应确认按钮事件
	if ('' != pageNow) {
		$("[name='pageForm']").attr("action", "role.action");
		$("[name='pageForm']").attr("method", "post");
		$("[name='pageForm']").submit();
	}
}

$(function() {
	// 移到右边
	$('#remove').click(
			function() {
				// 获取选中的选项，删除并追加给对方
				$('#myFunctionsSelect option:selected').appendTo(
						'#leftFunctionsSelect');
			});
	// 移到左边
	$('#add').click(
			function() {
				$('#leftFunctionsSelect option:selected').appendTo(
						'#myFunctionsSelect');
			});
	// 全部移到右边
	$('#remove_all').click(function() {
		// 获取全部的选项,删除并追加给对方
		$('#myFunctionsSelect option').appendTo('#leftFunctionsSelect');
	});
	// 全部移到左边
	$('#add_all').click(function() {
		$('#leftFunctionsSelect option').appendTo('#myFunctionsSelect');
	});
	// 双击选项
	$('#myFunctionsSelect').dblclick(function() { // 绑定双击事件
		// 获取全部的选项,删除并追加给对方
		$("option:selected", this).appendTo('#leftFunctionsSelect'); // 追加给对方
	});
	// 双击选项
	$('#leftFunctionsSelect').dblclick(function() {
		$("option:selected", this).appendTo('#myFunctionsSelect');
	});
});

function save() {
	var myList = new Array();

	$("#myFunctionsSelect").each(function() {
		$(this).children("option").each(function() {
			myList.push($(this).val());
		});
	});

	var leftList = new Array();
	$("#leftFunctionsSelect").each(function() {
		$(this).children("option").each(function() {
			leftList.push($(this).val());
		});
	});

	$("#myFunctionsArray").val(myList);
	$("#leftFunctionsArray").val(leftList);

	$("[name='saveForm']").attr("action", "save.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

function update() {
	var myList = new Array();

	$("#myFunctionsSelect").each(function() {
		$(this).children("option").each(function() {
			myList.push($(this).val());
		});
	});

	var leftList = new Array();
	$("#leftFunctionsSelect").each(function() {
		$(this).children("option").each(function() {
			leftList.push($(this).val());
		});
	});

	$("#myFunctionsArray").val(myList);
	$("#leftFunctionsArray").val(leftList);
	$("[name='updateForm']").attr("action", "update.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function updateBack() {
	$("[name='updateForm']").attr("action", "role.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function saveBack() {
	$("[name='saveForm']").attr("action", "role.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}