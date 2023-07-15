//아이디 포거스 효과
$(".idInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path1").css("stroke", "#4747d6");
});

$("input").on("blur", function () {
  $(this).css("border-color", "#b1b1b1");
  $(".path1").css("stroke", "#b1b1b1");
  $(".path2").css("stroke", "#b1b1b1");
});

//비번 포거스 효과
$(".passwordInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path2").css("stroke", "#4747d6");
});

$(".eye").css("display", "none");

$(document).ready(() => {
  $(".eyeClose").click(() => {
    $(".eye").css("display", "");
    $(".eyeClose").css("display", "none");
    $(".passwordInput").attr("type", "text");
  });
  $(".eye").click(() => {
    $(".eyeClose").css("display", "");
    $(".eye").css("display", "none");
    $(".passwordInput").attr("type", "password");
  });
});

//로그인 버튼 클릭시 실행
$(document).ready(function () {
  $("#loginButton").click(function () {
    login();
  });
});

//아이디 비밀번호 확인 및 알람
function login() {
  var idInput = document.querySelector(".idInput").value;
  var passwordInput = document.querySelector(".passwordInput").value;

  $.ajax({
    type: "POST",
    url: "/login",
    data: {
      userId: idInput,
      userPassword: passwordInput,
    },
    success: function (response) {
      alert("로그인 되었습니다.");
      sessionStorage.setItem("isLoggedIn", "true"); //main에서 header 바꾸기

      window.location.href = "/"; // Redirect to the main page
    },
    error: function () {
      alert("존재하지 않는 정보입니다.");
      window.location.href = "/login";
    },
  });
}
