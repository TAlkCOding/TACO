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

//이름, 번호 확인 & alert 기능
const form = document.querySelector("form");

// submit 기능
form.addEventListener("submit", (event) => {
  event.preventDefault(); // Prevent form submission from refreshing the page

  // input 값
  const name = document.querySelector(".nameInput").value;
  const phoneNumber = document.querySelector(".phoneNumberInput").value;

  const matchedID = checkMatchingID(name, phoneNumber);

  // 일치 or  불일치 확인
  if (matchedID) {
    window.location.href = "/foundid" + matchedID;
  } else {
    alert("일치하는 아이디가 없습니다. 정보를 다시 확인해주세요.");
  }

  form.reset();
});

//수정 필요
function checkMatchingID(name, phoneNumber) {
  const users = [
    { name: "John Doe", phone: "1234567890", id: "john123" },
    { name: "Jane Smith", phone: "9876543210", id: "jane456" },
  ];

  const matchedUser = users.find(
    (user) => user.name === name && user.phone === phoneNumber
  );

  return matchedUser ? matchedUser.id : null;
}

//"비밀번호 찾기" button
document.querySelector(".pw").addEventListener("click", () => {
  window.location.href = "/findpw";
});
