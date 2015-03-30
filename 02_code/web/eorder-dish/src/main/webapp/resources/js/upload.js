function add() {
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

// 上传
function uploadFile() {
	$("[name='uploadForm']").attr("action", "uploadImage.action");
	$("[name='uploadForm']").attr("method", "post");
	$("[name='uploadForm']").submit();
}

function closeWind(){
	// 取得用户输入的分类
	var newFileName = $("#newFileName").val();
    parent.window.returnValue = newFileName;
    window.close();
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

function update() {
	$("[name='updateForm']").attr("action", "update.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function updateBack() {
	$("[name='updateForm']").attr("action", "dish.action");
	$("[name='updateForm']").attr("method", "post");
	$("[name='updateForm']").submit();
}

function saveBack() {
	$("[name='saveForm']").attr("action", "dish.action");
	$("[name='saveForm']").attr("method", "post");
	$("[name='saveForm']").submit();
}