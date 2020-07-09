const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});
signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});


let emailVerified = true;
let pwdVerified = false;
let nickVerified = true;

let list = [];

$('#signUp__email').on("propertychange change keyup paste input",
    function () {
        let email = $(this).val();
        if (email !== "") {
            $.ajax({
                url: '/emailVerify',
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

$('#signUp__nickname').on("propertychange change keyup paste input",
    function () {
        let nickname = $(this).val();
        if (nickname !== "") {
            $.ajax({
                url: '/nicknameVerify',
                type: 'post',
                data: {'nickname': nickname},
                success: function (res) {
                    let msg = '';
                    if (res == 'isAvailable') {
                        msg = '사용 가능한 닉네임입니다.';
                        nickVerified = true;
                        $('#error_nickname').css("display","block");
                        $('#error_nickname').css("color", "blue");
                    } else {
                        msg = '닉네임이 이미 존재합니다.';
                        nickVerified = false;
                        $('#error_nickname').css("display","block");
                        $('#error_nickname').css("color", "red");
                    }
                    $('#error_nickname').text(msg);
                }
            })
        }else{
            nickVerified = false;
            $('#error_nickname').css("display","none");
        }
        ;
    });

$('#signUp__form').submit(function () {
    if(emailVerified && pwdVerified&&nickVerified) {
        $.ajax({
            url: '/signUp',
            type: 'post',
            data: $('#signUp__form').serialize(),
            success: function (res) {
                alert("가입을 축하드립니다! 지금 바로 로그인하세요");
                location = "/signIn";
            }})
        return false;
    }else{
        alert("입력사항을 확인바랍니다.");
        return false;
    }
});

$("#signIn__form").submit(function () {
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
            }
        }
    })
    return false;
});
