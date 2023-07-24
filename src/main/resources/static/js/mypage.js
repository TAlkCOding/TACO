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

  //이미지 source 가져오기
  newImage.src = URL.createObjectURL(file);

  newImage.style.width = "100%";
  newImage.style.height = "100%";
  newImage.style.borderRadius = "20px";
  newImage.style.objectFit = "cover";

  container.appendChild(newImage);

  newImage.style.visibility = "visible";
}
