window.attachEvent("onload", function(){
	var element = document.createElement("link");
	element.setAttribute("rel", "stylesheet");
	element.setAttribute("type", "text/css");
	element.setAttribute("href", "/index_lte_ie8.css");
	
	var header = document.getElementsByTagName("header")[0];
	var p = header.getElementsByTagName("p")[0];
	
	var timerId = setInterval(function(){
		var color = p.currentStyle.color;
		
		if(color === "white"){
			clearInterval(timerId);
			document.getElementsByTagName("head")[0].appendChild(element);
		}
	}, 100);
});