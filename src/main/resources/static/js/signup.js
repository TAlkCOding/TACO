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

// $(".condition").css("display", "none");
// $(".condition1").css("display", "none");
// $(".condition2").css("display", "none");

$(document).ready(() => {
  $("button").click((event) => {
    var nameVal = $(".nameInput").val();
    var nickNameVal = $(".nickNameInput").val();
    var password = $(".passwordInput").val();
    var passwordCheck = $(".passwordCheckInput").val();
    if (nameVal === nickNameVal) {
      $(".condition").css("display", "");
      event.preventDefault();
      console.log(nameVal);
      console.log(nickNameVal);
    }
    if(password !== passwordCheck) {
      $(".condition1").css("display", "flex");
      event.preventDefault();
      console.log(password);
      console.log(passwordCheck);
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


/*사용자 정보 유효성 검사*/
/*
function save_joindata() {
  let name = $(".nameInput").val();
  let nickName = $(".nickNameInput").val();
  let id = $(".idInput").val();
  let password = $(".passwordInput").val();
  let passwordCheck = $(".passwordCheckInput").val();
  let cellphone = $(".phoneNumberInput").val();

  if (id.length < 4 || id.length >= 20) alert("ID가 양식에 적합하지 않습니다.");
  else if (password.length < 4 || password.length >= 20)
    alert("Password가 양식에 적합하지 않습니다.");
  else if (password !== passwordCheck) alert("비밀번호가 일치하지 않습니다.");
  else {
    $.ajax({
      type: "POST",
      url: "/signup",
      data: {
        name_give: name,
        nickName_give: nickName,
        id_give: id,
        password_give: password,
        cellphone_give: cellphone,
      },
      success: function (data) {
        if (data === "중복ID") {
          alert("이미 존재하는 ID입니다.");
        } else if (data === "성공") {
          alert("정상적으로 회원가입 되었습니다.");
        }
      },
    });
  }
}

//입력받은 정보를 데이터베이스에 저장 및 보안을 위한 패스워드 암호화
router.post("/", async function (req, res, next) {
  var id = req.body.id;
  var password = req.body.password;

  const hashPassword = crypto
    .createHash("sha512")
    .update(password + salt)
    .digest("hex");
  var query = "SELECT userid FROM member where userid='" + id + "';"; //중복 처리하기위한 쿼리
  connection.query(query, function (err, rows) {
    if (rows.length == 0) {
      //sql 제대로 연결되고 중복이 없는 경우
      var sql = {
        userid: id,
        password: hashPassword,
        salt: salt,
      };
      // create query
      var query = connection.query(
        "insert into member set ?",
        sql,
        function (err, rows) {
          if (err) throw err;
          else {
            res.send("성공");
          }
        }
      );
    } else {
      //이미 있음.
      res.send("중복ID");
    }
  });
});
*/

//joinform_check 함수로 유효성 검사
// function joinform_check() {
//   //변수에 담아주기
//   var name = document.getElementsByClassName("nameInput")[0];
//   var nickName = document.getElementsByClassName("nickNameInput")[0];
//   var id = document.getElementsByClassName("idInput")[0];
//   var pwd = document.getElementsByClassName("passwordInput")[0];
//   var repwd = document.getElementsByClassName("passwordCheckInput")[0];
//   var mobile = document.getElementsByClassName("phoneNumberInput")[0];

//   if (name.value == "") {
//     alert("이름을 입력하세요.");
//     name.focus();
//     return false;
//   }

//   if (nickName.value == "") {
//     alert("닉네임을 입력하세요.");
//     name.focus();
//     return false;
//   }

//   if (id.value == "") {
//     //해당 입력값이 없을 경우 같은말: if(!uid.value)
//     alert("아이디를 입력하세요.");
//     id.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
//     return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
//   }

//   if (pwd.value == "") {
//     alert("비밀번호를 입력하세요.");
//     pwd.focus();
//     return false;
//   }

//   //비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
//   var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

//   if (!pwdCheck.test(pwd.value)) {
//     alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
//     pwd.focus();
//     return false;
//   }

//   if (repwd.value !== pwd.value) {
//     alert("비밀번호가 일치하지 않습니다..");
//     repwd.focus();
//     return false;
//   }

//   var phoneNumber = /^[0-9]+/g; //숫자만 입력하는 정규식

//   if (!phoneNumber.test(mobile.value)) {
//     alert("전화번호는 숫자만 입력할 수 있습니다.");
//     mobile.focus();
//     return false;
//   }

//   //입력 값 전송
//   document.join_form.submit(); //유효성 검사의 포인트
// }

// //아이디 중복체크 팝업창(현재 공백문서)
// function id_check() {
//   //window.open("팝업될 문서 경로", "팝업될 문서 이름", "옵션");
//   window.open("", "", "width=600, height=200, left=200, top=100");
// }
