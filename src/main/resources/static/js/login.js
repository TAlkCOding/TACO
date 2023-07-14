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
      if (response === "no userData") {
        alert("Login failed");
        window.location.href = "/login";
      } else {
        alert("Login succeeded");
        var newHTML = `
          <div class="header">
            <div class="nav">
              <button type="button" class="button">
                <img class="logo" alt="logo" src="/img/logo.png" onclick="location.href='/'" />
              </button>
              <div class="topButton">
                <div class="writingWord">
                  <p>글쓰기</p>
                </div>
                <div class="profileBox">
                  <div class="picture"></div>
                </div>
              </div>
            </div>
          </div>
        `;
        $(".header").html(newHTML);
        // Redirect to the main page
        window.location.href = "/";
      }
    },
    error: function () {
      alert("Error occurred during login");
    },
  });
}

//로그인 버튼 클릭시 실행
$(document).ready(function () {
  $("#loginButton").click(function () {
    login();
  });
});
