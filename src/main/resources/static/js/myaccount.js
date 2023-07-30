document
  .getElementById("openSelectButton")
  .addEventListener("click", function () {
    var selectBox = document.getElementById("mySelect");
    selectBox.classList.remove("hidden");
  });

document.body.addEventListener("click", function (event) {
  var selectBox = document.getElementById("mySelect");
  var openButton = document.getElementById("openSelectButton");

  if (event.target !== selectBox && event.target !== openButton) {
    selectBox.classList.add("hidden");
  }
});

// Add event listener 로그아웃 버튼 클릭
document.getElementById("selectLogout").addEventListener("click", function () {
  // isLoggedIn sessionStorage 제거
  sessionStorage.removeItem("isLoggedIn");

  // 로그아웃 후 메인 페이지 이동
  window.location.href = "/";
});

// 비밀번호 display
$(".eye").css("display", "none");

$(document).ready(() => {
  $(".eyeClose").click(() => {
    $(".eye").css("display", "");
    $(".eyeClose").css("display", "none");
    $("#inputNewpw").attr("type", "text");
  });
  $(".eye").click(() => {
    $(".eyeClose").css("display", "");
    $(".eye").css("display", "none");
    $("#inputNewpw").attr("type", "password");
  });
});

// 비밀번호 변경하기 input박스 나타나기
$(".newpwContainer").css("display", "none");

$(document).ready(() => {
  $(".changePw").click(() => {
    $(".newpwContainer").toggle();
  });
});

// ajax로 데이터 전송
$(document).ready(() => {
  //저장하기 버튼 클릭시
  $("#submitBtn").click(() => {
    const newPassword = $("#inputNewpw").val();

    // 비밀번호 변경 input 이 비어있을 경우
    if (newPassword.trim() === "") {
      alert("변경사항이 없습니다.");
      return;
    }

    // 새로운 비밀번호를 입력한 경우
    const confirmed = confirm("비밀번호를 변경하시겠습니까?");

    if (confirmed) {
      $.ajax({
        url: "/change/pw",
        type: "POST",
        data: { newPassword: newPassword },

        success: function (response) {
          alert("비밀번호를 변경하였습니다!");
        },
        error: function (xhr, status, error) {
          alert("에러가 발생했습니다.");
        },
      });
      // alert에서 취소 누르면 원상복귀
    }
  });

  // 회원탈퇴 버튼 클릭시 alert
  $("#withdrawBtn").click(() => {
    const confirmed = confirm("회원탈퇴를 하시겠습니까?");

    if (confirmed) {
      $.ajax({
        url: "/user/delete",
        type: "DELETE",
        success: function (data) {
          alert("회원탈퇴가 완료되었습니다.");
          sessionStorage.removeItem("isLoggedIn");

          window.location.href = "/";
        },
        error: function (error) {
          alert("회원탈퇴를 실패했습니다. 다시 시도해주세요.");
        },
      });
    }
    // alert에서 취소 누르면 원상복귀
  });
});
