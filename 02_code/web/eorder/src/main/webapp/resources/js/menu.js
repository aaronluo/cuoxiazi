$(function() {
	// 导航切换
	$(".menuson li").click(function() {
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});

	$('.title').click(function() {
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if ($ul.is(':visible')) {
			$(this).next('ul').slideUp();
		} else {
			$(this).next('ul').slideDown();
		}
	});

})

// 进入页面后自动打开菜单，选中子菜单项
window.onload = function() {// 页面所有元素加载完毕
	// 1、先通过隐藏变量找到当前的功能
	var currentFunction = $("#currentFunctionDesc").val();
	// 2、通过隐藏变量找到菜单头的div
	var menuTitleObj = $("#menuTitle" + currentFunction).parent("div");
	// 3、展开菜单
	var $ul = $(menuTitleObj).next('ul');
	$('dd').find('ul').slideUp();
	if ($ul.is(':visible')) {
		$(menuTitleObj).next('ul').slideUp();
	} else {
		$(menuTitleObj).next('ul').slideDown();
	}
	// 4、通过隐藏变量找到子菜单li
	var menuTextObj = $("#myText" + currentFunction).parent("li")
	// 5、给对象加上选中的样式
	menuTextObj.addClass('active');

	// 1、先通过隐藏变量找到当前的功能
	var currentToolbar = $("#currentToolbar").val();
	// alert('currentToolbar: ' + currentToolbar);
	// 2、通过隐藏变量找到菜单头的div
	var myTextObj = $("#myText" + currentToolbar).parent("li");
	// 3、给对象加上选中的样式
	menuTextObj.addClass('selected');
}