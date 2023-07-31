// newHeader.html js 기능
document.body.addEventListener("click", function (event) {
  var selectBox = document.getElementById("mySelect");
  var openButton = document.getElementById("openSelectButton");

  if (
    event.target.id === "openSelectButton" ||
    event.target === selectBox ||
    selectBox.contains(event.target)
  ) {
    selectBox.classList.toggle("hidden");
  } else {
    selectBox.classList.add("hidden");
  }
});

$(document).ready(() => {
  var index_button = $("input[name=index_button]").val();
  if (index_button == 1) {
    console.log(index_button);
    $(".deleteEdit").css("display", "flex");
  }
});

/*
$(document).ready(function () {
  var isLoggedIn = sessionStorage.getItem("isLoggedIn");
  updateHeader(isLoggedIn);
});
*/

//댓글 빈칸일시 작동X
$(document).ready(() => {
  $(".writingButton").click(() => {
    const commentContent = $("#commentTextarea").val().trim();

    if (commentContent == "") {
      alert("댓글을 작성해주세요.");
      return;
    }
  });
});
