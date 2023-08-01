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

document.addEventListener("DOMContentLoaded", function () {
  // Addeventlisteners "수정" 버튼
  function handleEditClick(event) {
    const commentBox = event.target.closest(".commentBox");
    const commentText = commentBox.querySelector(".comment");
    const commentContent = commentText.innerText;

    const textarea = document.createElement("textarea");
    textarea.classList.add("editTextarea");
    textarea.name = "editedComment";
    textarea.value = commentContent;

    const saveButton = document.createElement("button");
    saveButton.classList.add("saveC");
    saveButton.innerText = "저장";

    const editDeleteContainer = commentBox.querySelector(".deleteEditC");
    editDeleteContainer.innerHTML = "";
    commentText.innerHTML = "";
    commentText.appendChild(textarea);
    editDeleteContainer.appendChild(saveButton);

    //기능 삭제
    event.target.removeEventListener("click", handleEditClick);

    saveButton.addEventListener("click", function () {
      const editedComment = textarea.value;

      commentText.innerText = editedComment;
      editDeleteContainer.innerHTML = "";

      const editButton = document.createElement("button");
      editButton.classList.add("editC");
      editButton.innerText = "수정";

      const deleteButton = document.createElement("button");
      deleteButton.classList.add("deleteC");
      deleteButton.innerText = "삭제";

      editDeleteContainer.appendChild(editButton);
      editDeleteContainer.appendChild(deleteButton);

      //다시 적용
      editButton.addEventListener("click", handleEditClick);
      deleteButton.addEventListener("click", handleDeleteClick);
    });
  }

  const editButtons = document.querySelectorAll(".editC");
  editButtons.forEach((button) => {
    button.addEventListener("click", handleEditClick);
  });

  const deleteButtons = document.querySelectorAll(".deleteC");
  deleteButtons.forEach((button) => {
    button.addEventListener("click", handleDeleteClick);
  });
});


