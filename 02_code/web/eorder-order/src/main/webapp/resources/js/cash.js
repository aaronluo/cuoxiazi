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