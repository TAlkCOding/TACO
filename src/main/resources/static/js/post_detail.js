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
