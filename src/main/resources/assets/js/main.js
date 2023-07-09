function categoryChange1(localParent) {
  var a = [
    "강남구",
    "강동구",
    "강북구",
    "강서구",
    "관악구",
    "광진구",
    "구로구",
    "금천구",
    "노원구",
    "도봉구",
    "동대문구",
    "동작구",
    "마포구",
    "서대문구",
    "서초구",
    "성동구",
    "성북구",
    "송파구",
    "양천구",
    "영등포구",
    "용산구",
    "은평구",
    "종로구",
    "중구",
    "중랑구",
  ];
  var b = ["광산구", "남구", "동구", "북구", "서구"];
  var c = [
    "남구",
    "공주시",
    "보령시",
    "서산시",
    "아산시",
    "천안시",
    "금산군",
    "논산군",
    "당진군",
    "부여군",
    "서천군",
    "연기군",
    "예산군",
    "청양군",
    "태안군",
    "홍성군",
  ];
  var target = document.getElementById("localChildOne");
  var removeAll = document.getElementById("localChildTwo");

  if (localParent.value == "a") var localChildOne = a;
  else if (localParent.value == "b") var localChildOne = b;
  else if (localParent.value == "c") var localChildOne = c;

  target.options.length = 1;
  removeAll.options.length = 1;

  for (x in localChildOne) {
    var opt = document.createElement("option");
    opt.value = localChildOne[x];
    opt.innerHTML = localChildOne[x];
    target.appendChild(opt);
  }
}

function categoryChange2(localChildOne) {
  if (localParent.value == "b") {
    var 광산구 = ["여기는", "저도잘", "뭐가", "있지?"];
    var 남구 = ["진월동", "주월동", "효덕동", "등등?", "ㅋㅋ"];
    var 서구 = ["지수", "제니", "로제", "리사"];
    var 동구 = ["LE", "하니", "정화", "혜린", "솔지"];
    var 북구 = ["여기도", "몰라여"];
  } else if (localParent.value == "c") {
    var 남구 = ["빅토리아", "엠버", "루나", "크리스탈"];
    var 공주시 = ["LE", "하니", "정화", "혜린", "솔지"];
  }
  var target = document.getElementById("localChildTwo");

  if (localChildOne.value == "남구") var localChildTwo = 남구;
  else if (localChildOne.value == "북구") var localChildTwo = 북구;
  else if (localChildOne.value == "서구") var localChildTwo = 서구;
  else if (localChildOne.value == "동구") var localChildTwo = 동구;
  else if (localChildOne.value == "광산구") var localChildTwo = 광산구;

  target.options.length = 1;

  for (x in localChildTwo) {
    var opt = document.createElement("option");
    opt.value = localChildTwo[x];
    opt.innerHTML = localChildTwo[x];
    target.appendChild(opt);
  }
}

/*자동 슬라이드*/
function move() {
  var curIndex = 0;
  let one = document.getElementById("newsCircle1");
  let two = document.getElementById("newsCircle2");
  let three = document.getElementById("newsCircle3");

  document.querySelector("#newsCircle2").addEventListener("click", function () {
    document.querySelector(".newsContainer").style.transform =
      "translate(-100vw)";
    return (curIndex = 0);
  });

  document.querySelector("#newsCircle3").addEventListener("click", function () {
    document.querySelector(".newsContainer").style.transform =
      "translate(-200vw)";
    return (curIndex = 1);
  });

  document.querySelector("#newsCircle1").addEventListener("click", function () {
    document.querySelector(".newsContainer").style.transform = "translate(0vw)";
    return (curIndex = -1);
  });

  setInterval(function () {
    document.querySelector(".newsContainer").style.transition = "0.2s";
    document.querySelector(".newsContainer").style.transform =
      "translate3d(-" + 100 * (curIndex + 1) + "vw, 0px, 0px)";

    curIndex++;

    if (curIndex === 0) {
      one.className = "click";
    } else {
      one.className = "nonClick";
    }

    if (curIndex === 1) {
      two.className = "click";
    } else {
      two.className = "nonClick";
    }

    if (curIndex === 2) {
      three.className = "click";
    } else {
      three.className = "nonClick";
    }

    if (curIndex === 2) {
      curIndex = -1;
    }
  }, 3000);
}

document.addEventListener("DOMContentLoaded", function () {
  move();
});

/* 
let interval = move();
/*버튼 색 변경
const nonClick = document.querySelectorAll(".nonClick");

function handleClick(event) {
  clearInterval(interval);
  // div에서 모든 "click" 클래스 제거
  nonClick.forEach((e) => {
    e.classList.remove("click");
  });
  // 클릭한 div만 "click"클래스 추가
  event.target.classList.add("click");
}

nonClick.forEach((e) => {
  e.addEventListener("click", handleClick);
  move();
});
*/
