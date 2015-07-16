
function add() {
	// 取得用户输入的分类
	var categoryId = $("#categoryId").val();

	// 如果没有输入，则不响应确认按钮事件
	if (null == categoryId || '' == categoryId) {
		$("#categoryId").val("1");
	}

	$("[name='addForm']").attr("action", "add.action");
	$("[name='addForm']").attr("method", "post");
	$("[name='addForm']").submit();
}

function prePage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) - 1;
	$("#pageNow").val(pageNow);
	var categoryId = $("#categoryId").val();
	$("#categoryId").val(categoryId);

	$("[name='pageForm']").attr("action", "list.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function nextPage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) + 1;
	$("#pageNow").val(pageNow);
	var categoryId = $("#categoryId").val();
	$("#categoryId").val(categoryId);

	$("[name='pageForm']").attr("action", "list.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function load() {
	// 取得用户输入的页数
	var pageNow = $("#pageInput").val();
	// 如果没有输入，则不响应确认按钮事件
	if ('' != pageNow) {
		$("[name='pageForm']").attr("action", "list.action");
		$("[name='pageForm']").attr("method", "post");
		$("[name='pageForm']").submit();
	}
}

// 查询
function query() {
	// 取得用户输入的分类
	var categoryId = $("#categoryId").val();

	// 如果没有输入，则不响应确认按钮事件
	if (null == categoryId || '' == categoryId) {
		$("#categoryId").val("1");
	}

	$("[name='queryForm']").attr("action", "list.action");
	$("[name='queryForm']").attr("method", "post");
	$("[name='queryForm']").submit();
}


function save() {
	// 取得用户输入的分类
	var categoryId = $("#categoryId").val();

	// 如果没有输入，则不响应确认按钮事件
	if (null == categoryId || '' == categoryId) {
		$("#categoryId").val("1");
	}
	$("[name='saveForm']").attr("action", "save.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

function remove() {
	// 取得用户输入的分类
	var categoryId = $("#categoryId").val();

	// 如果没有输入，则不响应确认按钮事件
	if (null == categoryId || '' == categoryId) {
		$("#categoryId").val("1");
	}

	$("[name='saveForm']").attr("action", "remove.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

function update() {
	$("[name='updateForm']").attr("action", "update.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function updateBack() {
	$("[name='updateForm']").attr("action", "list.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function saveBack() {
	$("[name='saveForm']").attr("action", "list.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

// 获取窗口的高度
var windowHeight;
// 获取窗口的宽度
var windowWidth;
// 获取弹窗的宽度
var popWidth;
// 获取弹窗高度
var popHeight;
function init() {
	windowHeight = $(window).height();
	windowWidth = $(window).width();
	popHeight = $(".popwindow").height();
	popWidth = $(".popwindow").width();
}
// 关闭窗口的方法
function closeWindow() {
	$(".poptitle img").click(function() {
		$(this).parent().parent().hide("slow");
	});
}
// 定义弹出居中窗口的方法
function popCenterWindow() {
	init();
	// 计算弹出窗口的左上角Y的偏移量
	var popY = (windowHeight - popHeight) / 2;
	var popX = (windowWidth - popWidth) / 2;
	// 设定窗口的位置
	$("#popwindowid").css("top", popY).css("left", popX).slideToggle("slow");
	closeWindow();
}

$(function() {
	var categoryId = $("#categoryId").val();
	$("#dishSelect").each(function() {
		$(this).children("option").each(function() {
			if (categoryId == $(this).val()) {
				$(this).attr("selected", true);
				return;
			}
		});
	});

	// 下拉选单值变事件
	$('#dishSelect').change(function() {
		var categoryId = $(this).children('option:selected').val();
		$("#categoryId").val(categoryId);
	});

	$("#btn_center").click(function() {
		popCenterWindow();
	});

});