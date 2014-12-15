var ListUtil = new Object();
ListUtil.move = function(oListboxFrom, oListboxTo, iIndex) {
	// alert('move: ' + iIndex);
	var oOption = oListboxFrom.options[iIndex];
	// alert("oListboxFrom.length: " + oListboxFrom.options.length + ";
	// oListboxTo.length: " + oListboxTo.options.length);
	if (null != oOption) {
		// alert('appendChild: ' + oOption);
		oListboxTo.appendChild(oOption);
	}

	//refresh(oListbox1);
	//refresh(oListbox2);
}

function moveOneToLeft(oListbox1, oListbox2) {
	for (var i = 0; i < oListbox2.options.length; i++) {
		if (oListbox2.options[i].selected) {
			// alert("i: " + i);
			ListUtil.move(oListbox2, oListbox1, i);
		}
	}
}

function moveOneToRight(oListbox1, oListbox2) {
	for (var i = 0; i < oListbox2.options.length; i++) {
		if (oListbox1.options[i].selected) {
			//alert("i: " + i);
			ListUtil.move(oListbox1, oListbox2, i);
		}
	}
}

ListUtil.moveAll = function(oListboxFrom, oListboxTo) {
	// var oOption = '';
	// alert('length: ' + oListboxFrom.options.length);
	var len = oListboxFrom.options.length;
	for (var x = 0; x < len; x++) {
		var ind = len - x - 1;
		// alert("index: " + ind);
		var oOption = oListboxFrom.options[ind];
		// alert(x + " : "+oOption.firstChild.nodeValue);
		oListboxTo.appendChild(oOption);
	}

	//refresh(oListbox1);
	//refresh(oListbox2);
}

function moveAllToLeft(oListbox1, oListbox2) {
	ListUtil.moveAll(oListbox2, oListbox1);
}

function moveAllToRight(oListbox1, oListbox2) {
	ListUtil.moveAll(oListbox1, oListbox2);
}

ListUtil.refresh = function(oListboxFrom) {
	// var oOption = '';
	// alert('length: ' + oListboxFrom.options.length);
	var len1 = oListboxFrom.options.length;
	for (var x = 0; x < len1; x++) {
		var ind = len - x - 1;
		//alert("index: " + ind);
		var oOption = oListboxFrom.options[ind];
		// alert(x + " : "+oOption.firstChild.nodeValue);
		// oListboxTo.appendChild(oOption);
		oOption.selected = false;
		if (0 == ind) {
			oOption.seleted = true;
		}
	}
}