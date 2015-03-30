// 回车提交表单（登录）
$(function() {
	// 顶部导航切换
	$(".nav li a").click(function() {
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})

	// 进入页面后自动打开菜单，选中子菜单项
	var currentToolbar = $("#currentToolbar").val();
	$(".nav li").each(function() {
		$(this).children("a").each(function() {
			if (currentToolbar == $(this).attr("title")) {
				$(this).addClass("selected");
				return;
			}
		});
	});
});