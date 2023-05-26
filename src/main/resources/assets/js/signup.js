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

$(".condition").css("display", "none");

$(document).ready(() => {
  $("button").click((event) => {
    var nameVal = $(".nameInput").val();
    var nickNameVal = $(".nickNameInput").val();
    if (nameVal === nickNameVal) {
      $(".condition").css("display", "");
      event.preventDefault();
      console.log(nameVal);
      console.log(nickNameVal);
    }
  });
});

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
