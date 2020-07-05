const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});
signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});


let emailVerified = false;
let pwdVerified = false;

$('#signUp__email').on("propertychange change keyup paste input",
    function () {
        let email = $(this).val();
        if (email !== "") {
            $.ajax({
                url: '/signUpVerify',
                type: 'post',
                data: {'email': email},
                success: function (res) {
                    let msg = '';
                    if (res == 'isAvailable') {
                        msg = '사용 가능한 이메일입니다';
                        emailVerified = true;
                        $('#error_email').css("display","block");
                        $('#error_email').css("color", "blue");
                    } else {
                        msg = '이메일이 이미 존재합니다.';
                        emailVerified = false;
                        $('#error_email').css("display","block");
                        $('#error_email').css("color", "red");
                    }
                    $('#error_email').text(msg);
                }
            })
        }else{
            emailVerified = false;
            $('#error_email').css("display","none");
        }
        ;
    });

$('#signUp__pwd2').on("propertychange change keyup paste input",
    function () {
        let msg = '';
        if($(this).val()!==""){
            if ($(this).val() === $('#signUp__pwd').val()) {
                msg = '입력한 비밀번호와 일치합니다.';
                pwdVerified = true;
                $('#error_pwd').css("display","block");
                $('#error_pwd').css("color", "blue");
            }else{
                msg = '입력한 비밀번호와 다릅니다.';
                pwdVerified = false;
                $('#error_pwd').css("display","block");
                $('#error_pwd').css("color", "red");
            }
            $('#error_pwd').text(msg);
        }else{
            pwdVerified = false;
            $('#error_pwd').css("display","none");
        }

    });

$('#signUp__btn').click(function () {
    if(emailVerified && pwdVerified&&$('#signUp__name').val()) {
        $.post("signUp", $("#signUp__form").serialize())
        ,function(res) {
            alert("가입을 축하 드립니다. 지금 바로 로그인하세요!" );
        }
    }else{
        alert("입력사항을 확인바랍니다.");
        return false;
    }
});

$("#signIn__btn").click(function () {
    if (!(($("#signIn__email").val()) && ($("#signIn__pwd").val()))) {
        alert('입력 사항을 확인바랍니다.');
        return false;
    } else {
        $.ajax({
            url: 'signIn',
            type: 'post',
            data: $("#signIn__form").serialize(),
            success: function (res) {
                if (res.verified === "correct") {
                    alert(res.username + " 님 반갑습니다!");
                    location = "/";
                } else {
                    alert("이메일 / 비밀번호를 확인바랍니다.");
                    return false;
                }
            }
        });
    }
});
