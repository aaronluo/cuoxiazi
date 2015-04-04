// 上传
function uploadFile() {
	$("[name='uploadForm']").attr("action", "uploadImage.action");
	$("[name='uploadForm']").attr("method", "post");
	$("[name='uploadForm']").submit();
}

function closeWind(){
	// 取得用户输入的分类
	var newFileName = $("#newFileName").val();
    
    if(window.ActiveXObject){ //IE  
    	parent.window.returnValue = newFileName;
        window.close();
    }else{ //非IE  
        if(window.opener) {  
            window.opener.setValue(newFileName) ;  
        }  
        window.close();  
    }  
}