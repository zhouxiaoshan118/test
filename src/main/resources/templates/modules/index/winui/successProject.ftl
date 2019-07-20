<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>首页</title>
	<!-- <link rel="stylesheet" type="text/css" href="css/default.css"> -->
	<link rel="stylesheet" href="./css/common.css">
	<link rel="stylesheet" href="./css/index.css">
	<script src="./js/jquery-1.11.3.min.js"></script>
	<style>
	
	</style>
</head>
<body>
	<div class="container container-bg">
		<div class="header">
			<div class="header-title">
				<img src="./img/title2.png" alt="" style="    margin-top: 75px;">
			</div>
		</div>
		<div class="content-main">
			<div class="wrap">
				<div id="showcase" class="noselect">
			      <img class="cloud9-item" onclick="test(this)" data-href="1" src="./img/firefox.png" alt="Firefox">
			      <img class="cloud9-item" onclick="test(this)" data-href="2" src="./img/wyzo.png" alt="Wyzo">
			      <img class="cloud9-item" onclick="test(this)" data-href="3" src="./img/opera.png" alt="Opera">
			      <img class="cloud9-item" onclick="test(this)" data-href="4" src="./img/chrome.png" alt="Chrome">
			      <img class="cloud9-item" onclick="test(this)" data-href="5" src="./img/iexplore.png" alt="Internet Explorer">
			      <img class="cloud9-item" onclick="test(this)" data-href="6" src="./img/safari.png" alt="Safari">
			    </div>
			</div>
		</div>
	</div>
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/jquery.reflection.js"></script>
  	<script src="js/jquery.cloud9carousel.js"></script>
  	<script>
  		$(function(){
  			var showcase = $("#showcase"), title = $('#item-title')
	      	showcase.Cloud9Carousel( {
		        yOrigin: 42,
		        yRadius: 48,
		        mirror: {
		          gap: 12,
		          height: 0.2
		        },
		        buttonLeft: $("#nav > .left"),
		        buttonRight: $("#nav > .right"),
		        autoPlay: 1,
		        bringToFront: true,
		        autoPlayDelay: 1000,
		        onRendered: rendered,
		        onLoaded: function() {
		          showcase.css( 'visibility', 'visible' )
		          showcase.css( 'display', 'none' )
		          showcase.fadeIn( 800 )
	        	}
		 	});
		 	function rendered( carousel ) {
		        title.text( carousel.nearestItem().element.alt );
		        // Fade in based on proximity of the item
		        var c = Math.cos((carousel.floatIndex() % 1) * 2 * Math.PI)
		        title.css('opacity', 0.5 + (0.5 * c))
		    }

  		});
  		$(".container").css("min-height",$(window).height());
  		function test(obj){
	    	console.log($(obj).data("data-href"))
	    }
  	</script>
</body>
</html>