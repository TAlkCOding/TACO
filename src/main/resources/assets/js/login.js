//아이디 포거스 효과
$(".idInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path1").css("stroke", "#4747d6");
});

$("input").on("blur", function () {
  $(this).css("border-color", "#b1b1b1");
  $(".path1").css("stroke", "#b1b1b1");
  $(".path2").css("stroke", "#b1b1b1");
});

//비번 포거스 효과
$(".passwordInput").on("focus", function () {
  $(this).css("border-color", "#4747d6");
  $(".path2").css("stroke", "#4747d6");
});

$(".eye").css("display", "none");

$(document).ready(() => {
  $(".eyeClose").click(() => {
    $(".eye").css("display", "");
    $(".eyeClose").css("display", "none");
    $(".passwordInput").attr("type", "text");
  });
  $(".eye").click(() => {
    $(".eyeClose").css("display", "");
    $(".eye").css("display", "none");
    $(".passwordInput").attr("type", "password");
  });
});

//입력받은 정보를 데이터베이스에 저장 및 보안을 위한 패스워드 암호화
router.post("/", async function (req, res, next) {
  var id = req.body.id;
  var password = req.body.password;

  var query = "select salt, password from member where userid'" + id + "';";
  console.log(query);
  connection.query(query, function (err, rows) {
    if (err) throw err;
    else {
      if (rows.length == 0) {
        //아이디가 존재하지 않는 경우
        console.log("아이디 틀림");
        res.redirect("/login");
      } else {
        var salt = rows[0].salt;
        var password = rows[0].password;
        const hashPassword = crypto
          .createHash("sha512")
          .update(password + salt)
          .digest("hex");
        if (password === hashPassword) {
          //로그인 성공
          console.log("로그인 성공");
          res.cookie("user", id, {
            expires: new Data(Data.now() + 900000),
            httpOnly: true,
          });
          res.redirect("/");
        } else {
          //로그인 실패 (아이디는 존재하지만 비밀번호가 다름)
          console.log("로그인 실패 비밀번호 틀림.");
          res.redirect("../templates /login");
        }
      }
    }
  });
});
