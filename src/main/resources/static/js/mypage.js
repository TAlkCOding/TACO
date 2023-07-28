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

//이미지 화면 출력
function loadFile(input) {
  var file = input.files[0]; //선택된 파일 가져오기

  var container = document.getElementById("profileImg");
  var existingImage = container.querySelector(".img");
  if (existingImage) {
    container.removeChild(existingImage);
  }

  var newImage = document.createElement("img");
  newImage.setAttribute("class", "img");

  if (file) {
    // Image source from the uploaded file
    newImage.src = URL.createObjectURL(file);
  } else {
    // If no file is selected, use the default image
    newImage.src = "/img/default_profile.png";
  }

  newImage.style.width = "100%";
  newImage.style.height = "100%";
  newImage.style.borderRadius = "20px";
  newImage.style.objectFit = "cover";

  container.appendChild(newImage);

  newImage.style.visibility = "visible";
}

// Add event listener 로그아웃 버튼 클릭
document.getElementById("selectLogout").addEventListener("click", function () {
  // isLoggedIn sessionStorage 제거
  sessionStorage.removeItem("isLoggedIn");

  // 로그아웃 후 메인 페이지 이동
  window.location.href = "/";
});
