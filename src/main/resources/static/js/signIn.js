const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

var isPass = false;

$('[name=email]').blur(function() {
	var email = $(this).val();
	
	$.ajax({
		url: 'idCheck',
		type: 'post',
		data: { 'email': email },
		success: function(res) {
			var msg = '';
			if(res == '1') {
				msg = '사용 가능한 이메일입니다.';
				isPass = true;
				$('#error_email').css("color", "blue");
			} else {
				msg = '중복 이메일입니다.';
				isPass = false;
				$('#error_email').css("color", "red");
			} 
			$('#error_email').text(msg);
		}
	});
	
});

$('#signUpBtn').click(function() {
	if(!isPass) {
		alert('이미 사용중인 이메일입니다.');
		return false;
	} else {
		alert('가입을 축하 드립니다.');
		return true;
	}
});

$("#signIn").click(function() {
	$("form").submit();
	return false;
});
