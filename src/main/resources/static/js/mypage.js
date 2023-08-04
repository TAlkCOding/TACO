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

//프로필 등록 수정
function handleProfileImageChange(input) {
  var file = input.files[0];

  var profileImg = document.getElementById("profileImg");

  if (file) {
    profileImg.src = URL.createObjectURL(file);
  } else {
    profileImg.src = "/img/default_profile.png";
  }
}

document
  .getElementById("chooseFile")
  .addEventListener("change", function (event) {
    handleProfileImageChange(event.target);
  });
