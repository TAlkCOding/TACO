//아이디 포거스 효과
$(".nameInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path1").css("stroke", "#4747d6");
});

$("input").on("blur", function () {
  $(this).css("border-color", "#b1b1b1");
  $(".path1").css("stroke", "#b1b1b1");
  $(".path2").css("stroke", "#b1b1b1");
});

//비번 포거스 효과
$(".phoneNumberInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path2").css("stroke", "#4747d6");
});

//"비밀번호 찾기" button
document.querySelector(".pw").addEventListener("click", () => {
  window.location.href = "/find/pw";
});
