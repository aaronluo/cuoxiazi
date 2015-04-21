function prePage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) - 1;
	$("#pageNow").val(pageNow);

	$("[name='pageForm']").attr("action", "list.action");
	$("[name='pageForm']").attr("method", "post");
	$("[name='pageForm']").submit();
}

function nextPage() {
	var pageNow = $("#pageNow").val();
	pageNow = parseInt(pageNow) + 1;
	$("#pageNow").val(pageNow);

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

function discount() {

	// 取得用户输入的页数
	var cellphone = $("#order\\.member\\.cellphone").val();

	var discount = $("#order\\.member\\.memberShip\\.level\\.discount").val();
	if (null != discount && 0 != discount && '' != discount) {
		return;
	}
	$("[name='orderForm']").attr("action", "discount.action");
	$("[name='orderForm']").attr("method", "post");
	$("[name='orderForm']").submit();
}

function save() {
	$("[name='saveForm']").attr("action", "save.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

function print(orderId) {
	document.location = "print.action?orderId=" + orderId
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
	var size = $("#order\\.orderItems\\.size").val();
	windowHeight = $(window).height();
	windowWidth = $(window).width();
	popHeight = $(".pop_print_window").height();
	if (popHeight > 350) {
		popHeight = 350;
	}

	if (size < 5) {
		popHeight = popHeight + size * 6;
	} else {
		popHeight = popHeight + 5 * 6;
	}
	popWidth = $(".pop_print_window").width();
	$("#pop_print_windowid").css({
		height : popHeight
	});// 设置的动态高度

	popContentHeight = $(".pop_print_content").height();
	popContentHeight = popHeight - 25;
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
	$("#pop_print_windowid").css("top", 100).css("left", 100).slideToggle(
			"slow");
	closeWindow();
}

$(function() {
	$("#btn_center").click(function() {
		popCenterWindow();
	});

	$("#btn_print").click(function() {
		popupPrintWindow();
	});

	$("#btnPrint").click(function() {
		$("#printContent").printArea();
	});

});