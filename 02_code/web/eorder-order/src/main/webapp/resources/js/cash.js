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

//弹出打印窗口
function popupPrintWindow() {
	// alert("orderId: " + orderId);
	// print(orderId.value)
	
	// var orderId2 = $("order\\.id").val();
	// alert("orderId: " + orderId + "; orderId2: " + orderId2);
	// alert("orderId: " + orderId);
	if (window.ActiveXObject) { // IE
		var orderId = $("#orderId").val();
		//alert("orderId: " + orderId);
		var dialogLocation = calcShowModalDialogLocation(550, 600);
		var result = window.showModalDialog("print.action?orderId=" + orderId,
				window, dialogLocation);

		// var returnValue = window.showModalDialog("../upload/upload.action",
		// window, "dialogWidth:550px;status:no;dialogHeight:600px");
		//if (returnValue != null) {
			// setValue(returnValue);
		//}
	} else { // 非IE
		
		var orderId = $("#orderId").val();
		//alert("orderId: " + orderId);
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
	//alert("orderId: " + orderId);
	document.location = "print.action?orderId=" + orderId
}

$(function() {
	$("#btn_print").click(function() {
		popupPrintWindow();
		//popupPrintWindow(orderId.value);
	});

	$("#btnPrint").click(function() {
		//alert("btnPrint click");
		$("#printContent").printArea();
	});

});