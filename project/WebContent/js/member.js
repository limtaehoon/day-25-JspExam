var exp = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
$(document).ready(function(){
	$("#send").click(function(){
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		//아이디
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		//비번
		if($("#pwd").val()==""){
			alert("비밀번호입력");
			$("#pwd").focus();
			return false;
		}
		if($("#pwd").val()!=$("#pwd_check").val()){
			alert("비밀번호가 다릅니다");
			$("#pwd_check").focus();
			return false;
		}
		//이메일
		if($("#email").val()==""){
			alert("이메일을 입력하세요");
			$("#email").focus();
			return false;
		}
		//전화번호
		if(!$("#phone").val().match(exp)){
			alert("전화번호 입력양식이 아닙니다.");
			$("#phone").focus();
			return false;
		}
		$("#frm").submit();
	}) 
		
	//id 
	$("#idcheckBtn").click(function(){
		window.open("idCheck.me","","width=800 ,hight=500");
	});
	//id 중복
	$("#useBtn").click(function(){
	if($("#userid").val()==""){
		alert("아이디입력");
		$("#userid").focuw();
		return false;
	}
	$.ajax({
		type :"post",
		url	 :"idCheck.me",
		data :{"userid":$("#userid").val()},
		success : function(val){
			if(val.trim()=="yes"){
				alert("사용가능")
				$(opener.document).find("#userid").val($("#userid").val());
				$(opener.document).find("#uid").val($("#userid").val());
				self.close();
			}else if(val.trim()=="no"){
				alert("사용불가능");
				$("#userid").val("")
			}
		},
		error : function(e){
			alert("error : " + e)
		}
	});//ajax
})//idCheckBtn

}) 
function del(userid){
	if(confirm("정말삭제할까요")){
		
	
	$.getJSON("userDelete.me?userid="+userid,function(data){
		//alert(data.root.length);
		var htmlStr = "";
		$.each(data.root,function(ket,val){
			htmlStr+="<tr>";
			htmlStr+="<td>"+val.name+"</td>";
			htmlStr+="<td>"+val.userid+"</td>";
			htmlStr+="<td>"+val.phone+"</td>";
			htmlStr+="<td>"+val.email+"</td>";
			htmlStr+="<td>"+val.mode+"</td>";
			if(val.mode=='일반회원'){
				htmlStr+="<td onclick=del('"+val.userid+"')>삭제</td>";
			}else{
				htmlStr+="<td>Admin</td>";
			}
			
			htmlStr+="<tr>";
		})
		$("table tbody").html(htmlStr);
		$("#cntSpan").text(data.rootCount.count)
	})
	}
}

