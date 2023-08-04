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