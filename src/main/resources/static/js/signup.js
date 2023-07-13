//이름 포거스 효과
$(".nameInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
});

//닉네임 포거스 효과
$(".nickNameInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
});

//아이디 포거스 효과
$(".idInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path1").css("stroke", "#4747d6");
});

//비번 포거스 효과
$(".passwordInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path2").css("stroke", "#4747d6");
});

//비번 체크 포거스 효과
$(".passwordCheckInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path3").css("stroke", "#4747d6");
});

//휴대폰 번호 포거스 효과
$(".phoneNumberInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path4").css("stroke", "#4747d6");
});

$("input").on("blur", function () {
  $(this).css("border-color", "#b1b1b1");
  $(".path1").css("stroke", "#b1b1b1");
  $(".path2").css("stroke", "#b1b1b1");
  $(".path3").css("stroke", "#b1b1b1");
  $(".path4").css("stroke", "#b1b1b1");
});



// 비밀번호 display
$(".eye").css("display", "none");

$(document).ready(() => {
  $(".eyeClose").click(() => {
    $(".eye").css("display", "");
    $(".eyeClose").css("display", "none");
    $(".passwordInput").attr("type", "text");
    $(".passwordCheckInput").attr("type", "text");
  });
  $(".eye").click(() => {
    $(".eyeClose").css("display", "");
    $(".eye").css("display", "none");
    $(".passwordInput").attr("type", "password");
    $(".passwordCheckInput").attr("type", "password");
  });
});

$(".condition").css("display", "none");
$(".condition1").css("display", "none");
$(".condition2").css("display", "none");

// 이름 닉네임 중복
$(document).ready(() => {
  $("button").click((event) => {
    var nameVal = $(".nameInput").val();
    var nickNameVal = $(".nickNameInput").val();
    if (nameVal === nickNameVal) {
      $(".condition").css("display", "");
      event.preventDefault();
    }
    if (nameVal !== nickNameVal) {
      $(".condition").css("display", "none");
      event.preventDefault();
    }
  });
});


// 아이디 중복검사
$('.idInput').on("propertychange change keyup paste input", function(){
  // console.log("keyup 테스트");
  var userId = $(".idInput").val();
  var data = {userId : userId}

  $.ajax({
    type: "post",
    url: "/check/id",
    data : data,
    success : function(result){
      //사용 가능
      if(result === 0){
        $(".condition1").css("display", "flex");
        $(".id_input_re_1").css("display","inline-block");
				$(".id_input_re_2").css("display", "none");
        console.log("사용 가능");
      }
      // 아이디 중복
      if(result === 1){
        $(".condition1").css("display", "flex");
        $(".id_input_re_1").css("display","none");
				$(".id_input_re_2").css("display", "inline-block");
        console.log("아이디 중복!");
      }
    }
  });
});

// 비밀번호 체크 다를 때
$(document).ready(() => {
  $("button").click((event) => {
    var password = $(".passwordInput").val();
    var passwordCheck = $(".passwordCheckInput").val();

    if(password !== passwordCheck) {
      $(".condition2").css("display", "");
      event.preventDefault();
    }
    if(password === passwordCheck) {
      $(".condition2").css("display", "none");
    }
  })
})


// 회원가입
$(document).ready(() => {
  $(".join_button").click((event) => {
    var nameVal = $(".nameInput").val();
    var nickNameVal = $(".nickNameInput").val();
    var password = $(".passwordInput").val();
    var passwordCheck = $(".passwordCheckInput").val();

    var userId = $(".idInput").val();
    var data = {
      userId : $(".idInput").val(),
      userNickName : $(".nickNameInput").val()
    };

    $.ajax({
      type: "post",
      url: "/check",
      data : data,
      success : function(result){
        if(nameVal !== nickNameVal){
          if(password === passwordCheck){
            if(result === 3){
              alert("회원가입되었습니다.");
              document.getElementById("signup").submit();
            }
            else if(result === 2){
              alert("닉네임이 중복됩니다.");
            }
          }
        }
      }
    })
  })
})

// joinform_check 함수로 유효성 검사
// function joinform_check() {
//   //변수에 담아주기
//   var name = document.getElementsByClassName("nameInput")[0];
//   var nickName = document.getElementsByClassName("nickNameInput")[0];
//   var id = document.getElementsByClassName("idInput")[0];
//   var pwd = document.getElementsByClassName("passwordInput")[0];
//   var repwd = document.getElementsByClassName("passwordCheckInput")[0];
//   var mobile = document.getElementsByClassName("phoneNumberInput")[0];

//   if (name.value == "") {
//     alert("이름을 입력하세요.");
//     name.focus();
//     return false;
//   }

//   if (nickName.value == "") {
//     alert("닉네임을 입력하세요.");
//     name.focus();
//     return false;
//   }

//   if (id.value == "") {
//     //해당 입력값이 없을 경우 같은말: if(!uid.value)
//     alert("아이디를 입력하세요.");
//     id.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
//     return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
//   }

//   if (pwd.value == "") {
//     alert("비밀번호를 입력하세요.");
//     pwd.focus();
//     return false;
//   }

//   //비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
//   var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

//   if (!pwdCheck.test(pwd.value)) {
//     alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
//     pwd.focus();
//     return false;
//   }

//   if (repwd.value !== pwd.value) {
//     alert("비밀번호가 일치하지 않습니다..");
//     repwd.focus();
//     return false;
//   }

//   var phoneNumber = /^[0-9]+/g; //숫자만 입력하는 정규식

//   if (!phoneNumber.test(mobile.value)) {
//     alert("전화번호는 숫자만 입력할 수 있습니다.");
//     mobile.focus();
//     return false;
//   }

//   //입력 값 전송
//   document.join_form.submit(); //유효성 검사의 포인트
// }
