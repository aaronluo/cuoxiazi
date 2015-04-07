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

// 上传
function openUploadPage() {
	if (window.ActiveXObject) { // IE
		var returnValue = window.showModalDialog("../upload/upload.action",
				window, "dialogWidth:550px;status:no;dialogHeight:600px");
		if (returnValue != null) {
			setValue(returnValue);
		}
	} else { // 非IE
		var url = "../upload/upload.action"; // 转向网页的地址;
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

// 接收父页面上传回的值
function setValue(newFileName) {
	$("newFileName").val(newFileName);
	$("#category\\.picPath").val("/dish/" + newFileName);
	// 改变图片源，实时刷新图片
	$("#categoryPic").attr("src", "../resources/images/dish/" + newFileName);
}

function save() {
	$("[name='saveForm']").attr("action", "save.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}

function saveBack() {
	$("[name='saveForm']").attr("action", "list.action");
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