const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});
signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

let isPass = false;

// $('#signUp__email').on("input",function(){
// 	$('#error_email').empty();
// 	let email = $(this).val();
// 	if(email!==""){
// 		$.ajax({
// 			url: '/signUpVerify',
// 			type: 'post',
// 			data: { 'email': email },
// 			success: function(res) {
// 				if(res == 'isAvailable') {
// 					isPass = true;
// 				} else {
// 					isPass = false;
// 				}
// 			}
// 		})};
// })

$('#signUp__email').blur(function () {
	let email = $(this).val();
	if(email!==""){
	$.ajax({
		url: '/signUpVerify',
		type: 'post',
		data: { 'email': email },
		success: function(res) {
			let msg = '';
			if(res == 'isAvailable') {
				msg = '사용 가능한 이메일입니다';
				isPass = true;
				$('#error_email').css("color", "blue");
			} else {
				msg = '이메일이 이미 존재합니다. 다른 이메일을 입력해주세요.';
				isPass = false;
				$('#error_email').css("color", "red");
			}
			$('#error_email').text(msg);
		}
	})};
});



$('#signUp__btn').click(function() {
	if(!isPass) {
		if($("#signUp__email").val()===""){
			alert("입력 사항을 확인바랍니다.")
		}else{
			alert('이메일이 이미 존재합니다. 다른 이메일을 입력바랍니다.');
		}
		return false;
	} else {
		if(!(($("#signUp__name").val())&&($("#signUp__pwd").val()))){
			alert('입력 사항을 확인바랍니다.');
			return false;
		}else {
			$.ajax({
				url: 'signUp',
				type: 'post',
				data: $("#signUp__form").serialize(),
				success: function (res) {
					alert('가입을 축하 드립니다. 지금 바로 로그인하세요!');
					location = "/signIn";
				}
			})
			return true;
		}
	}
});

$("#signIn__btn").click(function() {
	if(!(($("#signIn__email").val())&&($("#signIn__pwd").val()))){
		alert('입력 사항을 확인바랍니다.');
		return false;
	}else{
		$.ajax({
			url: 'signIn',
			type: 'post',
			data: $("#signIn__form").serialize(),
			success: function(res) {
				if(res.verified==="correct"){
					alert(res.username+" 님 반갑습니다!");
					location = "/";
				} else{
					alert("이메일 / 비밀번호를 확인바랍니다.");
					return false;
				}
			}
		});
	}
});
