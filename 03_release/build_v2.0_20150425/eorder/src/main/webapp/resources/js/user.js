function add() {
	$("[name='addForm']").attr("action", "add.action");
	$("[name='addForm']").attr("method", "post");
	$("[name='addForm']").submit();
}

function prePage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) - 1;
	$("#pageNow").val(pageNow);

	$("[name='pageForm']").attr("action", "user.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function nextPage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) + 1;
	$("#pageNow").val(pageNow);

	$("[name='pageForm']").attr("action", "user.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function load() {
	// 取得用户输入的页数
	var pageNow = $("#pageInput").val();
	// 如果没有输入，则不响应确认按钮事件
	if ('' != pageNow) {
		$("[name='pageForm']").attr("action", "user.action");
		$("[name='pageForm']").attr("method", "post");
		$("[name='pageForm']").submit();
	}
}

$(function() {
	// 移到右边
	$('#remove').click(function() {
		// 获取选中的选项，删除并追加给对方
		$('#myRolesSelect option:selected').appendTo('#leftRolesSelect');
	});
	// 移到左边
	$('#add').click(function() {
		$('#leftRolesSelect option:selected').appendTo('#myRolesSelect');
	});
	// 全部移到右边
	$('#remove_all').click(function() {
		// 获取全部的选项,删除并追加给对方
		$('#myRolesSelect option').appendTo('#leftRolesSelect');
	});
	// 全部移到左边
	$('#add_all').click(function() {
		$('#leftRolesSelect option').appendTo('#myRolesSelect');
	});
	// 双击选项
	$('#myRolesSelect').dblclick(function() { // 绑定双击事件
		// 获取全部的选项,删除并追加给对方
		$("option:selected", this).appendTo('#leftRolesSelect'); // 追加给对方
	});
	// 双击选项
	$('#leftRolesSelect').dblclick(function() {
		$("option:selected", this).appendTo('#myRolesSelect');
	});
});

function save() {
	var myList = new Array();

	$("#myRolesSelect").each(function() {
		$(this).children("option").each(function() {
			myList.push($(this).val());
		});
	});

	var leftList = new Array();
	$("#leftRolesSelect").each(function() {
		$(this).children("option").each(function() {
			leftList.push($(this).val());
		});
	});

	$("#myRolesArray").val(myList);
	$("#leftRolesArray").val(leftList);

	$("[name='saveForm']").attr("action", "save.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

function update() {
	var myList = new Array();

	$("#myRolesSelect").each(function() {
		$(this).children("option").each(function() {
			myList.push($(this).val());
		});
	});

	var leftList = new Array();
	$("#leftRolesSelect").each(function() {
		$(this).children("option").each(function() {
			leftList.push($(this).val());
		});
	});

	$("#myRolesArray").val(myList);
	$("#leftRolesArray").val(leftList);
	$("[name='updateForm']").attr("action", "update.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function updateBack() {
	$("[name='updateForm']").attr("action", "user.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function saveBack() {
	$("[name='saveForm']").attr("action", "user.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}