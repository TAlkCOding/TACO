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
