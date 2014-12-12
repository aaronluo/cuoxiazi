/*
 * line color change
 */
$(document).ready(function(){
	$(".dataTab tr").mouseover(function(){
	$(this).addClass("over");}).mouseout(function(){
	$(this).removeClass("over");});
	$(".dataTab tr:even").addClass("alt");
	$(".buttonLeft").each(function(i){
		var id = $(this).attr("id");
		$(this).html("<span class='buttonspan1' style='background-image:url(\"../resources/image/button_left.gif\");'></span>" +
				"<span class='buttonspan2' style='background-image:url(\"../resources/image/button_center.gif\");'>" +
				$("#" + id).attr("name") +
				"</span>" +
				"<span class='buttonspan1' style='background-image:url(\"../resources/image/button_right.gif\");'>");
	});
	$(".buttonRight").each(function(i){
		var id = $(this).attr("id");
		$(this).html("<span class='buttonspan1' style='background-image:url(\"../resources/image/button_right.gif\");float: right;'></span>" +
				"<span class='buttonspan2' style='background-image:url(\"../resources/image/button_center.gif\");float: right;'>" +
				$("#" + id).attr("name") +
				"</span>" +
				"<span class='buttonspan1' style='background-image:url(\"../resources/image/button_left.gif\");float: right;'>");
	});
});
/*
 * delete the left blank characters of a string 
 */
function ltrim(str) {
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(0)) != -1) {
        var j=0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
           j++;
        }
        s = s.substring(j, i);
    }
    return s;
}

/*
 * delete the right blank characters of a string 
 */
function rtrim(str) {
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
            i--;
        }
        s = s.substring(0, i+1);
    }
    return s;
}

/*
 * delete the left and right blank characters of a string 
 */
function trim(str) {
    return rtrim(ltrim(str));
}

/*
 * validate a string whether a email or not 
 */
function isEmail(str){
    var reg = /^([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-]+){0,1}@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    return reg.test(str);
}