function jumpDemo(username) {
	var url = "http://dev01.teemlink.com:8080/obpm/portal/login/login.action?domainName=" + encodeURIComponent("我的公司") + "&username=" + username + "&password=123456";
	
	window.open(url);
}

 document.getElementById("XZTY").onclick = function() {
				document.getElementById("PopupLayer01").classList.add("active");
				document.getElementById("PopupLayer02").classList.remove("active");
				document.getElementById("PopupLayer03").classList.remove("active");
				document.getElementById("PopupLayer04").classList.remove("active");
				
			}

			document.getElementById("CWTY").onclick = function() {
				document.getElementById("PopupLayer02").classList.add("active");
				document.getElementById("PopupLayer01").classList.remove("active");
				document.getElementById("PopupLayer03").classList.remove("active");
				document.getElementById("PopupLayer04").classList.remove("active");
				
			}
			
			document.getElementById("GCTY").onclick = function() {
				document.getElementById("PopupLayer03").classList.add("active");
				document.getElementById("PopupLayer01").classList.remove("active");
				document.getElementById("PopupLayer02").classList.remove("active");
				document.getElementById("PopupLayer04").classList.remove("active");
			}
			
			document.getElementById("HTTY").onclick = function() {
				document.getElementById("PopupLayer04").classList.add("active");
				document.getElementById("PopupLayer01").classList.remove("active");
				document.getElementById("PopupLayer02").classList.remove("active");
				document.getElementById("PopupLayer03").classList.remove("active");
			}
			
			document.getElementById("close01").onclick = function() {
				document.getElementById("PopupLayer01").classList.remove("active");
			}
			
			document.getElementById("close02").onclick = function() {
				document.getElementById("PopupLayer02").classList.remove("active");
			}
			
			document.getElementById("close03").onclick = function() {
				document.getElementById("PopupLayer03").classList.remove("active");
			}
				
			document.getElementById("close04").onclick = function() {
				document.getElementById("PopupLayer04").classList.remove("active");
			}

			//表单校验

			function CheckForm01() {
				var company = /^[\u4e00-\u9fa5]{0,1}$/;
				var names = /[a-zA-Z\u4E00-\u9FA5]+$/; //中文+英文名字验证
				var tels = /(^([0-9]{3,4}-)?[0-9]{7,8}$)|(^((\(\d{3}\))|(\d{3}\-))?(1[34578]\d{9})$)/; //手机+固话验证

				var gs = company.test(document.form1.UNIT.value);
				var yh = names.test(document.form1.LINKMAN.value);
				var dh = tels.test(document.form1.TEL.value);

				if(gs && yh && dh) {
					alert("亲，请填写单位名称 ");
					return(false);
				} else if(!yh) {
					alert("亲，请填写您的名字 ");
					return(false);
				} else if(!dh) {
					alert("亲，请填写联系电话 ");
					return(false);
				} else {
					document.getElementById("PopupLayer").classList.remove("active");
				}
			}
			
			function CheckForm02() {
				var company = /^[\u4e00-\u9fa5]{0,1}$/;
				var names = /[a-zA-Z\u4E00-\u9FA5]+$/; //中文+英文名字验证
				var tels = /(^([0-9]{3,4}-)?[0-9]{7,8}$)|(^((\(\d{3}\))|(\d{3}\-))?(1[34578]\d{9})$)/; //手机+固话验证

				var gs = company.test(document.form2.UNIT.value);
				var yh = names.test(document.form2.LINKMAN.value);
				var dh = tels.test(document.form2.TEL.value);

				if(gs && yh && dh) {
					alert("亲，请填写单位名称 ");
					return(false);
				} else if(!yh) {
					alert("亲，请填写您的名字 ");
					return(false);
				} else if(!dh) {
					alert("亲，请填写联系电话 ");
					return(false);
				} else {
					document.getElementById("PopupLayer2").classList.remove("active");
				}
			}
			
			function CheckForm03() {
				var company = /^[\u4e00-\u9fa5]{0,1}$/;
				var names = /[a-zA-Z\u4E00-\u9FA5]+$/; //中文+英文名字验证
				var tels = /(^([0-9]{3,4}-)?[0-9]{7,8}$)|(^((\(\d{3}\))|(\d{3}\-))?(1[34578]\d{9})$)/; //手机+固话验证

				var gs = company.test(document.form3.UNIT.value);

				var yh = names.test(document.form3.LINKMAN.value);

				var dh = tels.test(document.form3.TEL.value);

				if(gs && yh && dh) {
					alert("亲，请填写单位名称 ");
					return(false);
				} else if(!yh) {
					alert("亲，请填写您的名字 ");
					return(false);
				} else if(!dh) {
					alert("亲，请填写联系电话 ");
					return(false);
				} else {
					document.getElementById("PopupLayer3").classList.remove("active");
				}

			}
			
			function CheckForm04() {
				var company = /^[\u4e00-\u9fa5]{0,1}$/;
				var names = /[a-zA-Z\u4E00-\u9FA5]+$/; //中文+英文名字验证
				var tels = /(^([0-9]{3,4}-)?[0-9]{7,8}$)|(^((\(\d{3}\))|(\d{3}\-))?(1[34578]\d{9})$)/; //手机+固话验证

				var gs = company.test(document.form4.UNIT.value);

				var yh = names.test(document.form4.LINKMAN.value);

				var dh = tels.test(document.form4.TEL.value);

				if(gs && yh && dh) {
					alert("亲，请填写单位名称 ");
					return(false);
				} else if(!yh) {
					alert("亲，请填写您的名字 ");
					return(false);
				} else if(!dh) {
					alert("亲，请填写联系电话 ");
					return(false);
				} else {
					document.getElementById("PopupLayer3").classList.remove("active");
				}

			}