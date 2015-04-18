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

function calcShowModalDialogLocation(dialogWidth, dialogHeight) {
	var iWidth = dialogWidth;
	var iHeight = dialogHeight;
	var iTop = (window.screen.availHeight - 20 - iHeight) / 2;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	return 'dialogWidth:' + iWidth + 'px;dialogHeight:' + iHeight
			+ 'px;dialogTop: ' + iTop + 'px; dialogLeft: ' + iLeft
			+ 'px;center:yes;scroll:no;status:no;resizable:0;location:no';
}

// 弹出打印窗口
function popupPrintWindow() {
	// alert("orderId: " + orderId);
	// print(orderId.value)

	// var orderId2 = $("order\\.id").val();
	// alert("orderId: " + orderId + "; orderId2: " + orderId2);
	// alert("orderId: " + orderId);
	if (window.ActiveXObject) { // IE
		var orderId = $("#orderId").val();
		// alert("orderId: " + orderId);
		var dialogLocation = calcShowModalDialogLocation(550, 600);
		var result = window.showModalDialog("print.action?orderId=" + orderId,
				window, dialogLocation);

		// var returnValue = window.showModalDialog("../upload/upload.action",
		// window, "dialogWidth:550px;status:no;dialogHeight:600px");
		// if (returnValue != null) {
		// setValue(returnValue);
		// }
	} else { // 非IE

		var orderId = $("#orderId").val();
		// alert("orderId: " + orderId);
		var url = "print.action?orderId=" + orderId; // 转向网页的地址;
		var name; // 网页名称，可为空;
		var iWidth = 600; // 弹出窗口的宽度;
		var iHeight = 500; // 弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
		window
				.open(
						url,
						'newwindow',
						'height='
								+ iHeight
								+ ',,innerHeight='
								+ iHeight
								+ ',width='
								+ iWidth
								+ ',innerWidth='
								+ iWidth
								+ ',top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	}
}

function print(orderId) {
	// alert("orderId: " + orderId);
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
	// alert("size: " + size);
	windowHeight = $(window).height();
	// alert("#1 windowHeight: " + windowHeight);
	// windowHeight = windowHeight + size*100;
	// alert("#2 windowHeight: " + windowHeight);
	windowWidth = $(window).width();
	//alert("windowWidth: " + windowWidth);
	popHeight = $(".pop_print_window").height();
	//alert("#1 popHeight: " + popHeight);
	if(popHeight > 350) {
		popHeight = 350;
	}
	
	if(size < 5) {
		popHeight = popHeight + size * 6;
	} else {
		popHeight = popHeight + 5 * 6;
	}
	//alert("#2 popHeight: " + popHeight);
	popWidth = $(".pop_print_window").width();
	//alert("popWidth: " + popWidth);
	// pop_print_windowid
	$("#pop_print_windowid").css({
		height : popHeight
	});// 设置的动态高度
	
	popContentHeight = $(".pop_print_content").height();
	//alert("#1 popContentHeight: " + popContentHeight);
	popContentHeight = popHeight-25;
	//alert("#2 popContentHeight: " + popContentHeight);
	//$("#pop_print_contentid").css({
	//	height : popContentHeight
	//});// 设置的动态高度
	//alert("OK");
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
	// alert("windowHeight: " + windowHeight + "; windowWidth:" + windowWidth
	// + "; popHeight: " + popHeight + "; popWidth:"
	// + popWidth + ": popY: " + popY
	// + "; popX:" + popX);
	// 设定窗口的位置
	// $("#popwindowid").css("top", popY).css("left", popX).slideToggle("slow");
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
		// popupPrintWindow(orderId.value);
	});

	$("#btnPrint").click(function() {
		// alert("btnPrint click");
		$("#printContent").printArea();
	});

});